package com.ktspace.miniHomeIoT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktspace.miniHomeIoT.dto.Device;
import com.ktspace.miniHomeIoT.dto.Resource;
import com.ktspace.miniHomeIoT.dto.response.DeviceListResponse;
import com.ktspace.miniHomeIoT.mapper.DeviceMapper;
import com.ktspace.miniHomeIoT.mapper.ResourceMapper;
import com.ktspace.miniHomeIoT.service.HomeIoTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//@Transactional
//@AutoConfigureMybatis
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@RunWith(SpringRunner.class)
public class HomeIoTServiceTest {
//    @Autowired
//    DeviceMapper deviceMapper;
//    @Autowired
//    ResourceMapper resourceMapper;

    HomeIoTService homeIoTService;

    private static final String RESPONSE_SUCCEED_CODE = "200";
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void init(){
//        homeIoTService = new HomeIoTService(deviceMapper, resourceMapper);
    }

    private Resource getResource(String group, String code, String value){
        Resource resource = new Resource(group, code, value);
        return resource;
    }

    private Device getDevice(Integer serviceTargetSeq, String external, Integer deviceSeq,  String modelTypeCode,
                            String modelId, String deviceName, List<Resource> resource) {
        Device device = new Device(serviceTargetSeq, external, deviceSeq,
                modelTypeCode, modelId, deviceName,resource);

        return device;
    }

    private DeviceListResponse makeGetUserDevicesNormalData(){
        DeviceListResponse expected;

        ArrayList<Resource> resources1 = new ArrayList<>();
        resources1.add(getResource("connection-status", "value", "false"));
        resources1.add(getResource("power-switch", "value", "false"));
        ArrayList<Resource> resources2 = new ArrayList<>();
        resources2.add(getResource("power-switch", "value", "false"));

        Device device1 = getDevice(1, "HOME_IOT_GOQUAL", 1, "0601",
                "GOQUAL_GLS_WF", "거실스위치", resources1);
        Device device2 = getDevice(1, "HOME_IOT_GOQUAL", 7, "0607",
                "GOQUAL_FAN", "무선선풍기", resources2);

        ArrayList<Device> expectedDeviceList = new ArrayList<>();
        expectedDeviceList.add(device1);
        expectedDeviceList.add(device2);

        expected = DeviceListResponse.builder()
                .data(DeviceListResponse.ListData.builder()
                        .deviceCount(expectedDeviceList.size())
                        .deviceList(expectedDeviceList)
                        .build())
                .responseCode(RESPONSE_SUCCEED_CODE)
                .build();


        return expected;
    }

    private DeviceListResponse makeGetUserDevicesAbnormalData(){
        DeviceListResponse expected;

        ArrayList<Resource> resources1 = new ArrayList<>();
        resources1.add(getResource("connection-status", "value", "wrong"));
        resources1.add(getResource("power-switch", "value", "wrong"));
        ArrayList<Resource> resources2 = new ArrayList<>();
        resources2.add(getResource("power-switch", "value", "wrong"));

        Device device1 = getDevice(1, "HOME_IOT_SAMSUNG", 1, "0601",
                "GOQUAL_GLS_WF", "거실스위치", resources1);
        Device device2 = getDevice(1, "HOME_IOT_SAMSUNG", 7, "0607",
                "GOQUAL_FAN", "거실공기청정기", resources2);

        ArrayList<Device> expectedDeviceList = new ArrayList<>();
        expectedDeviceList.add(device1);
        expectedDeviceList.add(device2);

        expected = DeviceListResponse.builder()
                .data(DeviceListResponse.ListData.builder()
                        .deviceCount(expectedDeviceList.size())
                        .deviceList(expectedDeviceList)
                        .build())
                .responseCode(RESPONSE_SUCCEED_CODE)
                .build();


        return expected;
    }


    @Test
    public void getUserDevicesNormal() throws Exception {
        //given
        String userId = "ktRookie01";
        DeviceListResponse expected = makeGetUserDevicesNormalData();
        String expectedJson = mapper.writeValueAsString(expected);
        //when
        DeviceListResponse result = homeIoTService.getUserDevices(userId);
        String resultJson = mapper.writeValueAsString(result);

        //then
        assertThat(expectedJson.equals(resultJson));
    }

    @Test
    public void getUserDevicesAbnormal() throws Exception {
        //given
        String userId = "ktRookie01";
        DeviceListResponse expected = makeGetUserDevicesAbnormalData();
        String expectedJson = mapper.writeValueAsString(expected);
        //when
        DeviceListResponse result = homeIoTService.getUserDevices(userId);
        String resultJson = mapper.writeValueAsString(result);

        //then
        assertThat(expectedJson.equals(resultJson));
    }
}
