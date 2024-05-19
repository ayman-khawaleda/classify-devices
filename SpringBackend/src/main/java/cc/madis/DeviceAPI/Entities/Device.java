package cc.madis.DeviceAPI.Entities;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;

import java.util.HashMap;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;
import lombok.Data;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "battery_power")
    private Integer batteryPower;

    @Column(name = "blue")
    private Integer blue;

    @Column(name = "clock_speed")
    private Float clockSpeed;

    @Column(name = "dual_sim")
    private Integer dualSim;

    @Column(name = "fc")
    private Float fc;

    @Column(name = "four_g")
    private Float fourG;

    @Column(name = "int_memory")
    private Float intMemory;

    @Column(name = "m_dep")
    private Float mDep;

    @Column(name = "mobile_wt")
    private Float mobileWt;

    @Column(name = "n_cores")
    private Float nCores;

    @Column(name = "pc")
    private Float pc;

    @Column(name = "px_height")
    private Float pxHeight;

    @Column(name = "px_width")
    private Float pxWidth;

    @Column(name = "ram")
    private Float ram;

    @Column(name = "sc_h")
    private Float scH;

    @Column(name = "sc_w")
    private Float scW;

    @Column(name = "talk_time")
    private Integer talkTime;

    @Column(name = "three_g")
    private Integer threeG;

    @Column(name = "touch_screen")
    private Integer touchScreen;

    @Column(name = "wifi")
    private Integer wifi;

    @Column(name = "predicted_price",nullable = true)
    private Float predictedPrice;
    @Hidden
    private HashMap<String, String> deviceSpecification;
    @Hidden
    public HashMap<String, String> getDeviceSpecification() {
        var specMap = new HashMap<String, String>();
        specMap.put("batteryPower", batteryPower.toString());
        specMap.put("blue", blue.toString());
        specMap.put("clockSpeed", clockSpeed.toString());
        specMap.put("dualSim", dualSim.toString());
        specMap.put("fc", fc.toString());
        specMap.put("fourG", fourG.toString());
        specMap.put("intMemory", intMemory.toString());
        specMap.put("mdep", mDep.toString());
        specMap.put("mobileWt", mobileWt.toString());
        specMap.put("ncores", nCores.toString());
        specMap.put("pc", pc.toString());
        specMap.put("pxHeight", pxHeight.toString());
        specMap.put("pxWidth", pxWidth.toString());
        specMap.put("ram", ram.toString());
        specMap.put("scH", scH.toString());
        specMap.put("scW", scW.toString());
        specMap.put("talkTime", talkTime.toString());
        specMap.put("threeG", threeG.toString());
        specMap.put("touchScreen", touchScreen.toString());
        specMap.put("wifi", wifi.toString());
        return specMap;
    }
    @Hidden
    public void setDeviceSpecification(List<String> record) {
        this.setBatteryPower(Integer.parseInt(record.get(0)));
        this.setBlue(Integer.parseInt(record.get(1)));
        this.setClockSpeed(Float.parseFloat(record.get(2)));
        this.setDualSim(Integer.parseInt(record.get(3)));
        this.setFc(Float.parseFloat(record.get(4)));
        this.setFourG(Float.parseFloat(record.get(5)));
        this.setIntMemory(Float.parseFloat(record.get(6)));
        this.setMDep(Float.parseFloat(record.get(7)));
        this.setMobileWt(Float.parseFloat(record.get(8)));
        this.setNCores(Float.parseFloat(record.get(9)));
        this.setPc(Float.parseFloat(record.get(10)));
        this.setPxHeight(Float.parseFloat(record.get(11)));
        this.setPxWidth(Float.parseFloat(record.get(12)));
        this.setRam(Float.parseFloat(record.get(13)));
        this.setScH(Float.parseFloat(record.get(14)));
        this.setScW(Float.parseFloat(record.get(15)));
        this.setTalkTime(Integer.parseInt(record.get(16)));
        this.setThreeG(Integer.parseInt(record.get(17)));
        this.setTouchScreen(Integer.parseInt(record.get(18)));
        this.setWifi(Integer.parseInt(record.get(19)));
    }
    public HashMap<String, String> getDeviceSpecificationForPrediction() {
        var specMap = new HashMap<String, String>();
        specMap.put("battery_power", batteryPower.toString());
        specMap.put("blue", blue.toString());
        specMap.put("clock_speed", clockSpeed.toString());
        specMap.put("dual_sim", dualSim.toString());
        specMap.put("fc", fc.toString());
        specMap.put("four_g", fourG.toString());
        specMap.put("int_memory", intMemory.toString());
        specMap.put("m_dep", mDep.toString());
        specMap.put("mobile_wt", mobileWt.toString());
        specMap.put("n_cores", nCores.toString());
        specMap.put("pc", pc.toString());
        specMap.put("px_height", pxHeight.toString());
        specMap.put("px_width", pxWidth.toString());
        specMap.put("ram", ram.toString());
        specMap.put("sc_h", scH.toString());
        specMap.put("sc_w", scW.toString());
        specMap.put("talk_time", talkTime.toString());
        specMap.put("three_g", threeG.toString());
        specMap.put("touch_screen", touchScreen.toString());
        specMap.put("wifi", wifi.toString());
        return specMap;
    }
}
