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

        // userId에 대한 처리 같이 해주기 !
        ArrayList<DeviceStatusDTO> deviceStatusList = deviceMapper.findDvcList(new DeviceVO(null, devSeq));

        if (deviceStatusList.isEmpty()) {
            return new DeviceStatusDTO();//차라리 없다는 거에 대한 메시지를 넘겨주는 걸로..
        } else {
            return deviceMapper.findDvcList(new DeviceVO(null, devSeq)).get(0);
        }
    }

    public HashMap<String, Object> getUserDevices(String userId) {
        HashMap<String, Object> result = new HashMap<>();

        ArrayList<DeviceStatusDTO> dvcStatusDTOList = deviceMapper.findDvcList(new DeviceVO(userId, null));

        result.put("deviceCount", dvcStatusDTOList.size());
        result.put("deviceList", dvcStatusDTOList);

        return result;
    }

    private HashMap<String, Object> getRscCtrlRsltData(int dvcSeq, String rscGrp, String value) {
        HashMap<String, String> curRsc = resourceMapper.findResources(dvcSeq, rscGrp).get(0);
        HashMap<String, Object> result = new HashMap<String, Object>();

        String code;
        String msg;

        if (value.equals(curRsc.get("value"))){
            code = "200";
            msg = "제어성공";
        }
        else{
            code = "500";
            msg = "제어실패";
        }

        result.put("resultCode", code);
        result.put("resultMessage", msg);

        return result;
    }

    public HashMap<String, Object> controlResource(int dvcSeq, String rscGroup, String value) {
        resourceMapper.updateRscValueByDvcSeq(dvcSeq, rscGroup, value);
        //resource_log 추가..
        resourceMapper.insertRscLog(dvcSeq, rscGroup, value);
        return getRscCtrlRsltData(dvcSeq, rscGroup, value);
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
