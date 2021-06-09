# Temu.in Machine Learning Models Documentations

This is the repository for all the machine learning models used in Temu.in. There are two models currently in use; community recommendation and thread recommendation. All the files referred to in this README can be found in the folder [Machine Learning](https://github.com/AjiSiwi/arunika-temuin/tree/master/Machine%20Learning). Check the folders first if deemed necessary.

## Community Recommendation
The purpose of this model is to predict a community most suitable to a person according to the person's answers to the [RIASEC](https://openpsychometrics.org/tests/RIASEC/) and [TIPI](http://gosling.psy.utexas.edu/scales-weve-developed/ten-item-personality-measure-tipi/) online personality tests. RIASEC itself has 48 questions while TIPI has 10, which means there are 58 answers we can use as possible features. During our model development, we used a dataset provided by [openpsychometrics.org](https://openpsychometrics.org/_rawdata/) which has more than 140,000 data.

### Installation
Before proceeding to run the codes related to our community recommendation project, please make sure that you have this Python packages installed in your environment:
* numpy == 1.20.2
* pandas == 1.1.2
* tensorflow == 2.3.1
* scikit_learn == 0.24.2
* openpyxl == 3.0.7
* gensim == 4.0.1

Or you can simply run this line in your command prompt:
```
pip install -r community_recommendation_requirements.txt
```
Or this line if you use Google Colaboratory/Jupyter Notebook instead:
```
!pip install -r community_recommendation_requirements.txt
```
Before running the lines mentioned above please make sure that the file community_recommendation_requirements.txt is in the current working directory.

### RIASEC Dataset
This dataset consists of 58 answers of RIASEC and TIPI online personality tests, 16 VCL answers, education, urban, gender, engnat, age, hand, religion, orientation, race, voted, married, familysize, uniqueNetworkLocation, country, source, and major. Aside from the 58 RIASEC and TIPI answers which will be used as features and major that will be used as labels, all the data can be dropped from the dataset. To clean the dataset and run some analysis on the data, simply run the file [RIASEC_Preprocessing.ipynb](https://github.com/AjiSiwi/arunika-temuin/blob/master/Machine%20Learning/RIASEC_Preprocessing.ipynb). 

### Model Development
Since the dataset at hand is more of structured data type, we specifically Deep Neural Network (DNN) to try classifying the data. To improve the model's current performance, we try to apply feature selection and hyperparameter tuning to the model. You can simply run [RIASEC_Feature_Selection.ipynb](https://github.com/AjiSiwi/arunika-temuin/blob/master/Machine%20Learning/RIASEC_Feature_Selection.ipynb) and [Community_Classification_Tuning_Model.ipynb](https://github.com/AjiSiwi/arunika-temuin/blob/master/Machine%20Learning/Community_Classification_Tuning_Model.ipynb).

#### Feature Selection
In our experiments, the dataset is generally divided into 75:25 train-test ratio, with the exception of 10-fold cross validation in which 80:20 ratio was used instead. The feature selection process involves the calculation of feature importances by using [Random Forest Classification](https://builtin.com/data-science/random-forest-algorithm), followed by performing Sequential Backward Selection (SBS) manually based on the calculated importances. Firstly, starts with dividing the dataset.
```
from sklearn.model_selection import train_test_split

data_train, data_test, label_train, label_test = train_test_split(data, labels, test_size = 0.25)
```
* data: a numpy array of the features that will be used
* labels: a numpy array of the labels
* test_size: the size of the test set desired. Change the value if necessary.

For more information about the parameters of the Scikit-Learn's train_test_split, please refer to the official documentation [here](https://scikit-learn.org/stable/modules/generated/sklearn.model_selection.train_test_split.html).

Once the train and test set are obtained, feature importances calculation can be done by fitting the Random Forest Classifier to the data.
```
from sklearn.ensemble import RandomForestClassifier

classifier = RandomForestClassifier(n_estimators = 1500, random_state = 42, n_jobs = -1)
classifier.fit(data_train, label_train)
```
The documentation for Scikit-Learn's Random Forest Classifier can be found [here](https://scikit-learn.org/stable/modules/generated/sklearn.ensemble.RandomForestClassifier.html?highlight=randomforestclassifier#sklearn.ensemble.RandomForestClassifier).

Before we can perform the SBS, **sorting the features based on the calculated importances is necessary**. To sort them, simply run this code:
```
feat_labels = dataset.columns[:-1]
importances = classifier.feature_importances_
indices = np.argsort(importances)[::-1]
```
Once the features have been sorted, SBS can be performed by running this code:
```
threshold = 40
total_features = len(feat_labels) - 1
for num_features in range(total_features, threshold, -1):
    new_dataset = dataset.drop(columns = feat_labels[indices[num_features:]])
    new_data = new_dataset[feat_labels[indices[:num_features]]].values
    new_labels = new_dataset['major'].values
    new_labels = new_labels.reshape((1, new_labels.shape[0]))
    new_labels = transformer.fit_transform(new_labels.T)
    for train_index, test_index in k_fold.split(new_data):
        data_train, data_test = new_data[train_index], new_data[test_index]
        label_train, label_test = labels[train_index], labels[test_index]
        model = create_model(input_shape = (num_features, ))
        history = model.fit(data_train, label_train, epochs = 50, batch_size = 32)
        acc, prec, rec = predict_assess(model, data_test, label_test)
        training_report(acc, prec, rec, threshold)
        add_to_history(training_history, threshold, acc, prec, rec)
```
The threshold of 40 features are used because there is no point in eliminating more than 18 features, since based on our experience doing so will only decrease the model's performance and will be a waste of resource instead. 5-fold cross validation is used to check the model's performance's credibility and ensure that all trials are exposed to the same data spread.

#### Hyperparameter Tuning
We used grid search for tuning the hyperparameters. The values used in the search are as written below.
* Kernel Initializer: Glorot Uniform, Glorot Normal (More information about Glorot initializer: https://proceedings.mlr.press/v9/glorot10a/glorot10a.pdf)
* Dropout Rate: 0.1, 0.2, 0.3, 0.4, 0.5
* Batch Size: 16, 32, 64, 128

In our experiment, we run the search manually. To do so, simply run this code:
```
train_acc = []
combinations = []
initializers = [tf.keras.initializers.GlorotUniform(), tf.keras.initializers.GlorotNormal()]
dropout_rates = [value / 10 for value in range(1, 6)]
batch_sizes = [16, 32, 64, 128]
model_checkpoint = tf.keras.callbacks.ModelCheckpoint(filepath = save_model_path + '\rec_model.h5', save_best_only = True,
                                                      monitor = 'val_accuracy', verbose = 1)
for initializer in initializers:
    for rate in dropout_rates:
        for size in batch_sizes:
            combinations.append((initializers.index(initializer) + 1, rate, size))
            model = create_model(input_shape = (n_features, ), dropout_rate = rate, kernel_init = initializer)
            history = model.fit(data_train, label_train, batch_size = size, epochs = 80, 
                                validation_data = (data_test, label_test), callbacks = [model_checkpoint])
            acc, prec, rec = predict_assess(model, data_test, label_test)
            training_report(acc, prec, rec)
            train_acc.append(sum(history.history['accuracy']) / len(history.history['accuracy']))
            add_to_history(training_history, n_features, acc, prec, rec)
```
Aside from searching for the best hyperparameters combination possible, this code is also designed to save the best model in the format of .h5, with the requirement of having the highest validation accuracy. For more information of the ModelCheckpoint callback, please refer to [this documentation](https://www.tensorflow.org/api_docs/python/tf/keras/callbacks/ModelCheckpoint).

## Thread Recommendation

### Installation
Before proceeding to run the codes related to our community recommendation project, please make sure that you have this Python packages installed in your environment:
* numpy == 1.20.2
* pandas == 1.1.2
* tensorflow == 2.3.1
* scikit_learn == 0.24.2
* openpyxl == 3.0.7

### Stackoverflow Dataset
The original dataset can access in [Kaggle](https://www.kaggle.com/imoore/60k-stack-overflow-questions-with-quality-rate). This dataset contain 60,000 Stack Overflow questions from 2016-2020 and classified them into three categories:

- HQ: High-quality posts with a total of 30+ score and without a single edit.
- LQ_EDIT: Low-quality posts with a negative score, and multiple community edits. However, they still remain open after those changes.
- LQ_CLOSE: Low-quality posts that were closed by the community without a single edit.

### Model Deployment Steps
1. Text Cleaning
   
3. TFIDF word feature extraction
4. Text Classification


## Model
You also can use our trained model in [models](https://drive.google.com/drive/folders/1e5nVwyPkOzEI_N4nYqTpA9Hm5LumRrZO?usp=sharing)

