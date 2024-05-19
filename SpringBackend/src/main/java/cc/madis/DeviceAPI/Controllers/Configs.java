package cc.madis.DeviceAPI.Controllers;

public class Configs {
    public static String DEVICE_PREDICTION_MODEL_BASE_URL = "http://localhost:8000";
    public static String DEVICE_PREDICTION_MODEL_ENDPOINT = DEVICE_PREDICTION_MODEL_BASE_URL
            + "/api/predict/device-spec/";
}