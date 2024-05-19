package cc.madis.DeviceAPI.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import cc.madis.DeviceAPI.Entities.Device;
import cc.madis.DeviceAPI.Services.APIServices;
import cc.madis.DeviceAPI.Services.DeviceService;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ClassificationController {
    Logger logger = LoggerFactory.getLogger(ClassificationController.class);

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private APIServices<String, ResponseEntity<String>> apiService;

    @PostMapping("/predict/{deviceId}")
    @Transactional
    public ResponseEntity<Device> predictDevicePrice(@PathVariable UUID deviceId)
            throws JsonMappingException, JsonProcessingException {
        var device = deviceService.getDeviceByID(deviceId).get();
        if (device == null) {
            return ResponseEntity.notFound().build();
        }

        var deviceModelApiUrl = Configs.DEVICE_PREDICTION_MODEL_ENDPOINT;
        var deviceString = apiService.mapper.writeValueAsString(
                device.getDeviceSpecificationForPrediction());
        var response = apiService.makeRequest(deviceString, deviceModelApiUrl);
        if (response.getStatusCode().is2xxSuccessful()) {
            var json_response = apiService.parseBody(response.getBody());
            if (json_response.containsKey("prediction")) {
                try {
                    var predictedPrice = Float.valueOf(json_response.get("prediction").toString());
                    device.setPredictedPrice(predictedPrice);
                    deviceService.updateDevice(device);
                    return ResponseEntity.ok(device);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        } else {
            return ResponseEntity.status(response.getStatusCode()).build();
        }
    }
}
