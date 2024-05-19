package cc.madis.DeviceAPI.Services;
import cc.madis.DeviceAPI.Entities.Device;
import cc.madis.DeviceAPI.Repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class DeviceService {


    @Autowired
    private DeviceRepository deviceRepository;
    
    public List<Device> getAllDevices(){
        return this.deviceRepository.findAll();
    }

    public Optional<Device> getDeviceByID(UUID id){
        return this.deviceRepository.findById(id);
    }

    @Transactional
    public Device addDevice(Device device){
        return this.deviceRepository.save(device);
    }
    @Transactional
    public Device updateDevice(Device device){
        return this.deviceRepository.save(device);
    }
}
