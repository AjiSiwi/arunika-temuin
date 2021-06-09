import pandas
import requests
import numpy as np
import json

from google.cloud import storage
from numpy import loadtxt
from keras.models import load_model
from sklearn import preprocessing
from sklearn.preprocessing import LabelEncoder
from keras.utils import np_utils

def get_model(bucket_name,source_file,destination):
    storage_client = storage.Client()
    bucket = storage_client.get_bucket(bucket_name)
    blob = bucket.blob(source_file)
    blob.download_to_filename(destination)

def predict_community(request):
    if request.method == 'POST':
        get_model('arunika-b21-cap0049.appspot.com','model/model_rekomendasi.h5','/tmp/model_rekomendasi.h5')
        model = load_model('/tmp/model_rekomendasi.h5')

        inputs = request.get_json()

        if 'input' in inputs:
            # data = np.array(inputs['input'])
            data = np.array(inputs['input'])
        
            predict_dict = {0: 'arts and literature', 1: 'technology' ,2: 'economics',3: 'social',4: 'science'}

            #load data
            features = data.reshape((1, data.shape[0]))
            norm_features = preprocessing.normalize(features)
            
            #make prediction:
            predict = model.predict_classes(norm_features)
            community = predict_dict[predict[0]]
            
            # return data
            dic = {
                "type" : community
            }

            result = json.dumps(dic)

            return result
            
        else:
            return f'wrong format input'
    else: 
        return "Use POST please"