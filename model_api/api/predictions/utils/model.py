import os
import joblib
import pandas as pd
from .preprocessing import preprocessing_log_transform, preprocessing_remove_id


preprocessing_operations = [
    preprocessing_log_transform,
    preprocessing_remove_id
]
model_path = os.path.join("resources","devices_price_model.pkl")
model = joblib.load(model_path)

class DevicePriceClassifier:
    model = model
    model_path = model_path
    preprocessing_operations = preprocessing_operations
    
    @classmethod
    def load_model(cls):
        cls.model = joblib.load(cls.model_path)
    
    @classmethod
    def preprocessing(cls, values: dict) -> dict:
        for operation in preprocessing_operations:
            values = operation(values)
        return values

    @classmethod
    def predict(cls, values: dict) -> int:
        if not cls.model:
            cls.load_model()
        values = cls.preprocessing(values)
        values = pd.DataFrame(values, index=[0])
        return cls.model.predict(values)[0]