package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.domain.Resource;
import com.ktspace.miniHomeIoT.domain.ResourceGroup;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.HomeIoTMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
@Service
public class HomeIoTService {
    HomeIoTMapper homeIoTMapper;
    DeviceMapper deviceMapper;
    ResourceMapper resourceMapper;

    @Autowired
    public HomeIoTService(HomeIoTMapper homeIoTMapper, DeviceMapper deviceMapper, ResourceMapper resourceMapper) {
        this.homeIoTMapper = homeIoTMapper;
        this.deviceMapper = deviceMapper;
        this.resourceMapper = resourceMapper;
    }

    public ArrayList<HashMap<String, Object>> findAll() {
        return homeIoTMapper.findAll();
    }

    public HashMap<String, Object> getUserDevices(String userId) {
        HashMap<String, Object> result = new HashMap<>();

        ArrayList<HashMap<String, Object>> deviceList = deviceMapper.findAllByUserId(userId);

        for(HashMap device : deviceList){
            //굳이 바꿔줘야 하나..
            device.put("resource", resourceMapper.findByDvcSeq((int)device.get("deviceSeq")));
        }

        result.put("deviceCount", deviceList.size());
        result.put("deviceList", deviceList);

        return result;
    }

    public Optional<Device> readDeviceInfo(int devSeq) {

        return homeIoTMapper.findByDeviceSeq(devSeq);
    }

    public String controlResource(int devSeq, String rscGrpName, String value) {
        Resource resource = new Resource(ResourceGroup.findByName(rscGrpName), value);

        homeIoTMapper.saveResource(resource, devSeq);

        boolean isSucceed = value.equals(homeIoTMapper
                .getResource(ResourceGroup.findByName(rscGrpName), devSeq)
                .getValue());

        return isSucceed ? "제어 성공" : "제어 실패";
    }


    public void deleteDevice(int devSeq) {
        homeIoTMapper.removeDevice(devSeq);
    }


    public void createDevice(int devSeq) {

    }
}
