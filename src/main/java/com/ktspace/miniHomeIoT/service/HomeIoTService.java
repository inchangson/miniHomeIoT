package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.dto.Device;
import com.ktspace.miniHomeIoT.dto.response.DeviceListResponse;
import com.ktspace.miniHomeIoT.dto.response.SingleResponse;
import com.ktspace.miniHomeIoT.exception.DataNotFoundException;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * HomeIoT의 서비스 입니다.
 *
 * @author 손인창
 * @version 1.0
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
     * 장치 서비스 관련 상수들입니다.
     */
    final static String RESULT_CODE = "resultCode";
    final static String RESULT_MESSAGE = "resultMessage";
    final static String MESSAGE = "message";

    final static String RESPONSE_SUCCEED_CODE = "200";
    final static String RESPONSE_FAIL_CODE = "400";
    final static String CONTROL_RESOURCE_SUCCEED = "제어 성공";
    final static String CONTROL_RESOURCE_FAIL = "제어 실패";
    final static String DELETE_RESOURCE_SUCCEED = "[deviceSeq : %d] 장치 삭제 성공";
    final static String DELETE_RESOURCE_FAIL = "[deviceSeq : %d] 장치 삭제 실패";

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * 생성자주입 방식으로 매퍼 클래스를 불러옵니다.
     *
     * @param deviceMapper
     * @param resourceMapper
     */
    @Autowired
    public HomeIoTService(DeviceMapper deviceMapper, ResourceMapper resourceMapper) {
        this.deviceMapper = deviceMapper;
        this.resourceMapper = resourceMapper;
    }

    private void checkUserException(String userId) {
        int result = deviceMapper.getUserCount(userId);
        if (result == 0) {
            throw new DataNotFoundException("해당 사용자가 없습니다.");
        }
    }

    private void checkDeviceException(Integer deviceSeq) {
        int result = deviceMapper.getDeviceCount(deviceSeq);

        if (result == 0) {
            throw new DataNotFoundException("해당 기기가 없습니다.");
        }
    }

    private void checkUserDeviceException(String userId, Integer dvcSeq) {
        if (userId != null) {
            checkUserException(userId);
        }
        if (dvcSeq != null){
            checkDeviceException(dvcSeq);
        }
    }

    /**
     * 호출은 {@link com.ktspace.miniHomeIoT.controller.HomeIoTController#getUserDevices(String) getUserDevices} 참조
     * DB조회는 {@link DeviceMapper#findDvcList(DeviceVO) findDvcList} 참조
     * userId 에 해당하는 모든 장치 정보를 조회합니다.
     *
     * @param userId
     * @return 해당 사용자의 모든 장치에 대한 정보를 반환
     */
    public DeviceListResponse getUserDevices(String userId) {
        if(userId == ""){
            throw new NullPointerException("no data on userId");
        }
        ArrayList<Device> dvcStatusDTOList = deviceMapper.findDvcList(userId, null);

        if (dvcStatusDTOList.size() <= 0) {
            checkUserDeviceException(userId, null);
        }

        DeviceListResponse result = DeviceListResponse.builder()
                .data(DeviceListResponse.ListData.builder()
                        .deviceCount(dvcStatusDTOList.size())
                        .deviceList(dvcStatusDTOList)
                        .build())
                .responseCode(RESPONSE_SUCCEED_CODE)
                .build();
        return result;
    }

    /**
     * 호출은 {@link com.ktspace.miniHomeIoT.controller.HomeIoTController#readDeviceInfo(String, Integer)} 참조
     * DB조회는 {@link DeviceMapper#findDvcList(DeviceVO) findDvcList} 참조
     * 장치 sequence 값을 통해 장치 정보를 조회합니다.
     * 장치 정보: {serviceTargetSeq, external, deviceSeq, modelTypeCode, modelId, deviceName, resource[group, code, value]}
     *
     * @param userId
     * @param dvcSeq
     * @return 해당하는 장치 정보(단건) 반환, 없다면 메시지 반환
     */
    public SingleResponse<Device> readDeviceInfo(String userId, Integer dvcSeq) {
        if(userId == ""){
            throw new NullPointerException("no data on userId");
        }
        ArrayList<Device> deviceStatusList = deviceMapper.findDvcList(userId, dvcSeq);

        if(deviceStatusList.size() <= 0){
            checkUserDeviceException(userId, dvcSeq);
        }

        Device device = deviceStatusList.get(0);
        SingleResponse<Device> singleResponse = SingleResponse.<Device>builder()
                .responseCode(RESPONSE_SUCCEED_CODE)
                .data(device)
                .build();

        return singleResponse;
    }

    private HashMap<String, String> getControlResultMessage(String code, String message) {
        HashMap<String, String> result = new HashMap<>();

        result.put(RESULT_CODE, code);
        result.put(RESULT_MESSAGE, message);

        return result;
    }

    /**
     * 요청된 장치 sequence 정보, 수정할 리소스 정보를 토대로 DB 정보를 수정합니다.
     *
     * @param userId
     * @param dvcSeq
     * @param rscGroup
     * @param value
     * @return 성공 여부 반환
     */
    public SingleResponse<HashMap<String, String>> controlResource(String userId, Integer dvcSeq,
                                                                   String rscGroup, String value) {
        if(userId == ""){
            throw new NullPointerException("no data on userId");
        }
        if(rscGroup == ""){
            throw new NullPointerException("no data on rscGroup");
        }
        if(value == ""){
            throw new NullPointerException("no data on value");
        }


        /**
         * myBatis를 통해 쿼리문을 실행합니다.
         * 또한 updeate된 row의 개수를 반환하기 때문에
         * 이를 통해 성공 여부를 판단합니다.
         */
        Integer updatedRowCnt = resourceMapper.updateRscValueByDvcSeq(userId, dvcSeq, rscGroup, value);

        HashMap<String, String> updateResult;
        if (updatedRowCnt == 0) {
            checkUserDeviceException(userId, dvcSeq);
            updateResult = getControlResultMessage(RESPONSE_FAIL_CODE, CONTROL_RESOURCE_FAIL);
        } else {
            updateResult = getControlResultMessage(RESPONSE_SUCCEED_CODE, CONTROL_RESOURCE_SUCCEED);
            /**
             * 리소스 정보 수정 성공 시, 해당하는 리소스 로그도 남깁니다.
             */
            resourceMapper.insertRscLog(dvcSeq, rscGroup, value);
        }

        SingleResponse<HashMap<String, String>> result;
        result = SingleResponse.<HashMap<String, String>>builder()
                .responseCode(RESPONSE_SUCCEED_CODE)
                .data(updateResult)
                .build();

        return result;
    }

    /**
     * 요청된 장치 sequence, 사용자 정보를 통해
     * 해당 사용자가 소유한 장치라면
     * 해당 장치를 삭제합니다.
     *
     * @param userId
     * @param dvcSeq
     * @return 성공 여부를 반환합니다.
     */
    public SingleResponse<HashMap<String, String>> deleteDevice(String userId, Integer dvcSeq) {
        if(userId == ""){
            throw new NullPointerException("no data on userId");
        }
        HashMap<String, String> deleteResult = new HashMap<>();
        Integer deletedRowCnt = deviceMapper.deleteDvcByDvcSeq(userId, dvcSeq);
        if (deletedRowCnt == 0) {
            checkUserDeviceException(userId, dvcSeq);
            deleteResult.put(MESSAGE, String.format(DELETE_RESOURCE_FAIL, dvcSeq));
        } else {
            deleteResult.put(MESSAGE, String.format(DELETE_RESOURCE_SUCCEED, dvcSeq));
        }

        SingleResponse<HashMap<String, String>> result;
        result = SingleResponse.<HashMap<String, String>>builder()
                .responseCode(RESPONSE_SUCCEED_CODE)
                .data(deleteResult)
                .build();

        return result;
    }
}
