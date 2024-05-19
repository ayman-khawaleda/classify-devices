import numpy as np

features = [
    "m_dep",
    "sc_h",
    "sc_w",
    "d_volume"
]

def preprocessing_log_transform(values: float) -> float:
    for k, v in values.items():
        if k in features:
            values[k] = np.log1p(v)
    return values

def preprocessing_remove_id(data: dict) -> dict:
    data.pop("id", None)
    return data