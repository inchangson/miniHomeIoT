package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.dto.Device;
import com.ktspace.miniHomeIoT.dto.response.ErrorResponse;
import com.ktspace.miniHomeIoT.dto.response.ListResponse;
import com.ktspace.miniHomeIoT.dto.response.SingleResponse;
import com.ktspace.miniHomeIoT.exception.InvalidUserException;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public SingleResponse<Device> readDeviceInfo(String userId, Integer devSeq) {
        SingleResponse<Device> singleResponse = new SingleResponse<>();
        ArrayList<Device> deviceStatusList = deviceMapper.findDvcList(userId, devSeq);

        Device result = deviceStatusList.get(0);

        return singleResponse;
    }

    /**
     * 호출은 {@link com.ktspace.miniHomeIoT.controller.HomeIoTController#getUserDevices(String) getUserDevices} 참조
     * DB조회는 {@link DeviceMapper#findDvcList(DeviceVO) findDvcList} 참조
     * userId 에 해당하는 모든 장치 정보를 조회합니다.
     * @param userId
     * @return 해당 사용자의 모든 장치에 대한 정보를 반환
     */
    public ListResponse<Device> getUserDevices(String userId) {
        ListResponse<Device> result = new ListResponse<>();

        ArrayList<Device> dvcStatusDTOList = deviceMapper.findDvcList(userId, null);

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
    public SingleResponse<?> controlResource(String userId, Integer dvcSeq, String rscGroup, String value) {
        SingleResponse<?> result = new SingleResponse<>();
        HashMap<String, Object> updateResult = new HashMap<String, Object>();
        /**
         * myBatis를 통해 쿼리문을 실행합니다.
         * 또한 updeate된 row의 개수를 반환하기 때문에
         * 이를 통해 성공 여부를 판단합니다.
         */
        Integer updatedRowCnt = resourceMapper.updateRscValueByDvcSeq(userId, dvcSeq, rscGroup, value);

        if (updatedRowCnt == 0){
            updateResult.put("resultCode", FAIL_CODE);
            updateResult.put("resultMessage", FAIL_MSG);
        } else{
            updateResult.put("resultCode", SUCCEED_CODE);
            updateResult.put("resultMessage", SUCCEED_MSG);
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
    public SingleResponse<?> deleteDevice(String userId, Integer dvcSeq) {
        SingleResponse<?> result = new SingleResponse<>();
        Map<String, Object> deleteResult = new HashMap<>();
        Integer deletedRowCnt = deviceMapper.deleteDvcByDvcSeq(userId, dvcSeq);
        if (deletedRowCnt == 0){
            deleteResult.put("message", String.format("[deviceSeq : %d] 장치 삭제 실패\n", dvcSeq));
        }else{
            deleteResult.put("message", String.format("[deviceSeq : %d] 장치 삭제 성공\n", dvcSeq));
        }

        return result;
    }
}
