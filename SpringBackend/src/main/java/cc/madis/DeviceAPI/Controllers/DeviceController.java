package cc.madis.DeviceAPI.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import cc.madis.DeviceAPI.Services.DeviceService;
import cc.madis.DeviceAPI.Entities.Device;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable UUID id) {
        return this.deviceService.getDeviceByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceService.addDevice(device);
    }
}