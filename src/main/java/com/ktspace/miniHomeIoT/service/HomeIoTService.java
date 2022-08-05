package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.dto.DeviceDTO;
import com.ktspace.miniHomeIoT.dto.response.ErrorResponse;
import com.ktspace.miniHomeIoT.exception.InvalidUserException;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import com.ktspace.miniHomeIoT.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * HomeIoT의 서비스 입니다.
 * @version 1.0
 * @author 손인창
 */
@Service
public class HomeIoTService {
    /**
     * 장치 관련 기능 쿼리와 연결되는 매퍼 클래스입니다.
     */
    DeviceMapper deviceMapper;
    /**
     * 리소스 관련 기능 쿼리와 연결되는 매퍼 클래스입니다.
     */
    ResourceMapper resourceMapper;
    /**
     * DB 접근에 따른 결과를 나타내는 코드와 메시지입니다.
     */
    final int    SUCCEED_CODE   = 200;
    final int    FAIL_CODE      = 500;
    final String SUCCEED_MSG    = "제어 성공";
    final String FAIL_MSG       = "제어 실패";

    /**
     * 생성자주입 방식으로 매퍼 클래스를 불러옵니다.
     * @param deviceMapper
     * @param resourceMapper
     */
    @Autowired
    public HomeIoTService(DeviceMapper deviceMapper, ResourceMapper resourceMapper) {
        this.deviceMapper = deviceMapper;
        this.resourceMapper = resourceMapper;
    }

    public ErrorResponse getErrorResponse(String code, String message){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.responseCode = code;
        errorResponse.errorReason = message;
        return errorResponse;
    }

    /**
     * 호출은 {@link com.ktspace.miniHomeIoT.controller.HomeIoTController#readDeviceInfo(String, Integer)} 참조
     * DB조회는 {@link DeviceMapper#findDvcList(DeviceVO) findDvcList} 참조
     * 장치 sequence 값을 통해 장치 정보를 조회합니다.
     * 장치 정보: {serviceTargetSeq, external, deviceSeq, modelTypeCode, modelId, deviceName, resource[group, code, value]}
     * @param userId
     * @param devSeq
     * @return 해당하는 장치 정보(단건) 반환, 없다면 메시지 반환
     */
    @ExceptionHandler(InvalidUserException.class)
    public Object readDeviceInfo(String userId, Integer devSeq) {
        ArrayList<DeviceDTO> deviceStatusList = deviceMapper.findDvcList(new DeviceVO(userId, devSeq));
        /**
         * 매퍼 클래스의 반환 값이 리스트 형태이기 때문에 첫번째 인덱스 값을 반환합니다.
         * 해당하는 값이 없을 경우 null을 반환합니다.
         */
        if (deviceStatusList.isEmpty()){
            return "해당하는 Device가 없거나 소유한 사용자가 아닙니다.";
        }

        DeviceDTO result = deviceStatusList.get(0);

        return result;
    }

    /**
     * 호출은 {@link com.ktspace.miniHomeIoT.controller.HomeIoTController#getUserDevices(String) getUserDevices} 참조
     * DB조회는 {@link DeviceMapper#findDvcList(DeviceVO) findDvcList} 참조
     * userId 에 해당하는 모든 장치 정보를 조회합니다.
     * @param userId
     * @return 해당 사용자의 모든 장치에 대한 정보를 반환
     */
    public HashMap<String, Object> getUserDevices(String userId) {
        HashMap<String, Object> result = new HashMap<>();

        ArrayList<DeviceDTO> dvcStatusDTOList = deviceMapper.findDvcList(new DeviceVO(userId, null));

        result.put("deviceCount", dvcStatusDTOList.size());
        result.put("deviceList", dvcStatusDTOList);

        return result;
    }

    /**
     * 요청된 장치 sequence 정보, 수정할 리소스 정보를 토대로 DB 정보를 수정합니다.
     * @param userId
     * @param dvcSeq
     * @param rscGroup
     * @param value
     * @return 성공 여부 반환
     */
    public HashMap<String, Object> controlResource(String userId, Integer dvcSeq, String rscGroup, String value) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        /**
         * myBatis를 통해 쿼리문을 실행합니다.
         * 또한 updeate된 row의 개수를 반환하기 때문에
         * 이를 통해 성공 여부를 판단합니다.
         */
        Integer updatedRowCnt = resourceMapper.updateRscValueByDvcSeq(userId, dvcSeq, rscGroup, value);

        if (updatedRowCnt == 0){
            result.put("resultCode", FAIL_CODE);
            result.put("resultMessage", FAIL_MSG);
            return result;
        } else{
            result.put("resultCode", SUCCEED_CODE);
            result.put("resultMessage", SUCCEED_MSG);
            /**
             * 리소스 정보 수정 성공 시, 해당하는 리소스 로그도 남깁니다.
             */
            resourceMapper.insertRscLog(dvcSeq, rscGroup, value);
        }

        return result;
    }

    /**
     * 요청된 장치 sequence, 사용자 정보를 통해
     * 해당 사용자가 소유한 장치라면
     * 해당 장치를 삭제합니다.
     * @param userId
     * @param dvcSeq
     * @return 성공 여부를 반환합니다.
     */
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
