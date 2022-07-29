package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.dto.DeviceStatusDTO;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import com.ktspace.miniHomeIoT.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeIoTService {
    DeviceMapper deviceMapper;
    ResourceMapper resourceMapper;

    @Autowired
    public HomeIoTService(DeviceMapper deviceMapper, ResourceMapper resourceMapper) {
        this.deviceMapper = deviceMapper;
        this.resourceMapper = resourceMapper;
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
//        Resource resource = new Resource(ResourceGroup.findByName(rscGrpName), value);
//
//        homeIoTMapper.saveResource(resource, devSeq);
//
        boolean isSucceed = false;
//        value.equals(homeIoTMapper
//                .getResource(ResourceGroup.findByName(rscGrpName), devSeq)
//                .getValue());

        return isSucceed ? "제어 성공" : "제어 실패";
    }

    private String getDeleteSucceedMsg(int dvcSeq) {
        if (deviceMapper.getDvcCntBydvcSeq(dvcSeq) != 0) {
            return String.format("[deviceSeq : %d] 장치 삭제 실패\n", dvcSeq);
        } else {
            return String.format("[deviceSeq : %d] 장치 삭제 성공\n", dvcSeq);
        }
    }

    public Map<String, Object> deleteDevice(int dvcSeq) {
        Map<String, Object> result = new HashMap<>();
        deviceMapper.deleteDvcByDvcSeq(dvcSeq);
        result.put("message", getDeleteSucceedMsg(dvcSeq));

        return result;
    }
}
