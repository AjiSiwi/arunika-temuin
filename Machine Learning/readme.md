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

###

## Thread Recommendation

## Model
You also can use our trained model in [models](https://drive.google.com/drive/folders/1e5nVwyPkOzEI_N4nYqTpA9Hm5LumRrZO?usp=sharing)

