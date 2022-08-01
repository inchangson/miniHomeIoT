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
    final int    SUCCEED_CODE   = 200;
    final int    FAIL_CODE      = 500;
    final String SUCCEED_MSG    = "제어 성공";
    final String FAIL_MSG       = "제어 실패";


    @Autowired
    public HomeIoTService(DeviceMapper deviceMapper, ResourceMapper resourceMapper) {
        this.deviceMapper = deviceMapper;
        this.resourceMapper = resourceMapper;
    }

    public DeviceStatusDTO readDeviceInfo(String userId, Integer devSeq) {
        ArrayList<DeviceStatusDTO> deviceStatusList = deviceMapper.findDvcList(new DeviceVO(userId, devSeq));
        DeviceStatusDTO result = deviceMapper.findDvcList(new DeviceVO(null, devSeq)).get(0);
        return result;
    }

    public HashMap<String, Object> getUserDevices(String userId) {
        HashMap<String, Object> result = new HashMap<>();

        ArrayList<DeviceStatusDTO> dvcStatusDTOList = deviceMapper.findDvcList(new DeviceVO(userId, null));

        result.put("deviceCount", dvcStatusDTOList.size());
        result.put("deviceList", dvcStatusDTOList);

        return result;
    }

    public HashMap<String, Object> controlResource(String userId, Integer dvcSeq, String rscGroup, String value) {
        Integer updatedRowCnt = resourceMapper.updateRscValueByDvcSeq(userId, dvcSeq, rscGroup, value);
        HashMap<String, Object> result = new HashMap<String, Object>();

        if (updatedRowCnt == 0){
            result.put("resultCode", FAIL_CODE);
            result.put("resultMessage", FAIL_MSG);
            return result;
        }

        result.put("resultCode", SUCCEED_CODE);
        result.put("resultMessage", SUCCEED_MSG);
        resourceMapper.insertRscLog(dvcSeq, rscGroup, value);
        return result;
    }

    public Map<String, Object> deleteDevice(String userId, Integer dvcSeq) {
        Map<String, Object> result = new HashMap<>();
        Integer deletedRowCnt = deviceMapper.deleteDvcByDvcSeq(userId, dvcSeq);
        if (deletedRowCnt == 0){
            result.put("message", String.format("[deviceSeq : %d] 장치 삭제 실패\n", dvcSeq));
        }else{
            result.put("message", String.format("[deviceSeq : %d] 장치 삭제 성공\n", dvcSeq));
        }

        return result;
    }
}
