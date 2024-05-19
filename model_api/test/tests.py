import os
import pandas as pd
import requests
import json
DATA_PATH = os.path.join("..", "..", "data", "devices-test.csv")
BASE_URL = "http://localhost:8000/"

df = pd.read_csv(DATA_PATH)
df = df.drop("id", axis=1)

post_url = BASE_URL+'api/devices/'

devices_ids = []
for i in range(10):
    headers = {'Content-Type': 'application/json'}
    json_data = json.loads(df.sample(1).to_json(orient='records'))[0]
    response = requests.post(post_url, headers=headers, data=json.dumps(json_data))
    if response.status_code in [200, 201]:
        devices_ids.append(json.loads(response.text)["id"])
    else:
        print(f"Error uploading DataFrame  {response.status_code} {i}: {response.text}")

for id in devices_ids:
    url = BASE_URL+f"api/predict/{id}"
    response = requests.get(url)
    if response.status_code == 200:
        print(f"Response: Device ID {id} ", "\tPrice Range: ",json.loads(response.text)["prediction"])
    else:
        print(f"Error: {response.status_code} - {response.text}")