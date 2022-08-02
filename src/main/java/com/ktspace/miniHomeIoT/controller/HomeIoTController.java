package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.dto.ResponseDTO;
import com.ktspace.miniHomeIoT.service.HomeIoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * HomeIoT의 컨트롤러입니다.
 * @version 1.0
 * @author 손인창
 */
@RestController
public class HomeIoTController {
    /**
     * 사용자 요청을 처리하는 서비스 객체입니다. {@link HomeIoTService HomeIoTService}
     */
    private final HomeIoTService homeIotService;
    /**
     * 요청에 따른 응답 코드들입니다.
     */
    private final String CODE_NA = "000";
    private final String CODE_SUCCEED = "200";

    @Autowired
    public HomeIoTController(HomeIoTService homeIotService) {

        this.homeIotService = homeIotService;
    }

    /**
     * {@link HomeIoTService 서비스}를 통해 온 데이터 값들의
     * null 체크를 통해 값이 제대로 구해졌는지 확인하고
     * 그에 따른 결과 코드를 반환합니다.
     * @param responseDTO
     * @return 결과 값의 null 여부에 따라 알맞은 code값 반환
     */
    private String getResultCode(ResponseDTO responseDTO) {
        if (responseDTO.getData() == null) {
            return CODE_NA;
        }
        return CODE_SUCCEED;
    }

    /**
     * 장치 정보 조회는 {@link HomeIoTService#getUserDevices(String) getUserDevices} 를 참조
     * @param userId
     * @return 해당 아이디에 존재하는 모든 장치의 정보(리소스 포함)를 반환
     */
    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public ResponseEntity<?> getUserDevices(@RequestHeader("userId") String userId) {
        //장치 목록 조회 : 관련된 리소스 모두 가져옴
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.getUserDevices(userId));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * 헤더의 아이디를 통한 사용자 검증과 path parameter 로 넘어오는 장치 seq값을 기준으로
     * 해당하는 장치 정보를 불러옵니다.
     * 장치 정보 조회는 {@link HomeIoTService#readDeviceInfo(String, Integer) readDeviceInfo} 를 참조
     * @param userId
     * @param dvcSeq
     * @return 특정 deviceSequence에 해당하는 장치의 정보(리소스 포함)를 반환
     */
    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> readDeviceInfo(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer dvcSeq) {
        //장치 리소스 조회
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.readDeviceInfo(userId, dvcSeq));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * 해당하는 장치의 리소스 값(DB)을 변경하고 로그(이력)를 남깁니다.
     * {@link HomeIoTService#controlResource(String, Integer, String, String) controlResource}를 참조
     * @param userId
     * @param dvcSeq
     * @param map
     * @return 값 수정 성공 여부를 반환합니다.
     */
    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.PUT)
    public ResponseEntity<?> controlResource(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer dvcSeq, @RequestBody HashMap<String, Object> map) {
        //장치 리소스 수정
        HashMap<String, String> resourceDTO = (HashMap<String, String>) map.get("resource");
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.controlResource(userId, dvcSeq, resourceDTO.get("group"), resourceDTO.get("value")));
        responseDTO.setResultCode(getResultCode(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * 해당하는 장치를 삭제합니다.
     * {@link HomeIoTService#deleteDevice(String, Integer) deleteDevice}를 참조
     * @param userId
     * @param devSeq
     * @return 장치 삭제 성공 여부를 반환합니다.
     */
    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDevice(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer devSeq) {
        // 장치 삭제
        ResponseDTO responseDTO = new ResponseDTO();//알아서 날려주려나..?
        responseDTO.setData(homeIotService.deleteDevice(userId, devSeq));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
