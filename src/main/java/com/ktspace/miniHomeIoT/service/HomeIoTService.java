package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.domain.Resource;
import com.ktspace.miniHomeIoT.domain.enums.ResourceGroup;
import com.ktspace.miniHomeIoT.dto.DeviceStatusDTO;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.HomeIoTMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import com.ktspace.miniHomeIoT.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    public DeviceStatusDTO readDeviceInfo(int devSeq) {
        // 함수명 변경,
        // 이왕 요청에서 있는 서비스나 모델의 정보를 통해 맞게 가지고왔는지..
        // 데이터 정합성 체크도 해볼 수 있음.. 일단 3, 4 기능 구현 이후 고민
        ArrayList<DeviceStatusDTO> deviceStatusList = deviceMapper.findDeviceStatusList(new DeviceVO(null, devSeq));

        if (deviceStatusList.isEmpty()) {
            return new DeviceStatusDTO();
        } else {
            return deviceMapper.findDeviceStatusList(new DeviceVO(null, devSeq)).get(0);
        }
    }

    public HashMap<String, Object> getUserDevices(String userId) {
        HashMap<String, Object> result = new HashMap<>();

        ArrayList<DeviceStatusDTO> dvcStatusDTOList = deviceMapper.findDeviceStatusList(new DeviceVO(userId, null));

        result.put("deviceCount", dvcStatusDTOList.size());
        result.put("deviceList", dvcStatusDTOList);

        return result;
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
