    package cc.madis.DeviceAPI;

import java.util.List;
import cc.madis.DeviceAPI.Entities.Device;
import cc.madis.DeviceAPI.Repositories.DeviceRepository;
import cc.madis.DeviceAPI.Services.APIServices;
import cc.madis.DeviceAPI.Services.CsvServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import cc.madis.DeviceAPI.Controllers.Configs;

@SpringBootTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeviceApiRestTemplateTests {
    @Autowired
    private CsvServices csvService;
    @Autowired
    private APIServices<String, ResponseEntity<String>> apiService;
    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    public void setUp() {
        apiService.setUrl("http://localhost:8080/api/");
    }

    @Test
    @Order(1)
    public void testAddDevice() throws Exception {
        apiService.appendUrl("devices");
        csvService.skip(1);// Skip the header

        for (int i = 0; i < 11; i++) {
            var new_record = csvService.readNext();
            new_record.remove(0); // Remove id value from the Test Dataset
            var device = new Device();
            device.setDeviceSpecification(new_record);
            // Add with device repository
            this.deviceRepository.save(device);

            // Add with Add Device Endpoint
            var deviceString = apiService.mapper.writeValueAsString(device.getDeviceSpecification());
            var response = apiService.makeRequest(deviceString);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @Test
    @Order(2)
    public void testPredictDevicePrice() throws Exception {
        List<Device> devices = this.deviceRepository.findAll();
        apiService.setUrl( Configs.DEVICE_PREDICTION_MODEL_ENDPOINT);
        for (var device : devices) {
            var deviceString = apiService.mapper.writeValueAsString(device.getDeviceSpecificationForPrediction());
            var response = apiService.makeRequest(deviceString);
            var json_response = apiService.parseBody(response.getBody());
            var predictedPrice = Float.valueOf(json_response.get("prediction").toString());
            device.setPredictedPrice(predictedPrice);
            this.deviceRepository.save(device);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }
}