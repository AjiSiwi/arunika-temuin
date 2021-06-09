import pandas as pd
import requests
from google.cloud import firestore
import json


db = firestore.Client()

def get_community(request):

  inputs = request.get_json()

  arr = []

  collection = db.collection('community')
  docs = collection.stream()

  for doc in docs:
    if doc.to_dict()['type'] == inputs['input']:
      # arr.append(sorted(doc.to_dict()))
      arr.append(dict(sorted(doc.to_dict().items())))

  container = { "communities" : arr}

  result = json.dumps(container)

  return result