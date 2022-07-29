package com.ktspace.miniHomeIoT.mapper;

import com.ktspace.miniHomeIoT.dto.DeviceStatusDTO;
import com.ktspace.miniHomeIoT.vo.DeviceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface DeviceMapper {
    /*  1. VO 를 통해서 넘겨주는 방식 vs Param으로 설정하는 방식
        - 객체 단위로 관리하는 것이 더 안전할 것이라 생각해서 앞의 방식 선택

        2. 리스트 or HashMap으로 넘겨주는 방식 vs DTO 활용
        - 값을 명확히 주기엔 map이나, 2차원 list 형식이 되어야할 것 같은데,
        - map에선 현재 기능에 필요없는 다양한 메서드들이 같이 있기 때문에
        - 현재 기능에 맞춰진 DTO를 따로 생성하는 것이 더 합리적이라 판단
        - 확장성에 대해선 의문이 있음
        - (매 기능마다 DTO를 분리하면 비슷한 기능을 하는 클래스들이 너무 많이 생김)
        - (-> 상속등을 통해서 보완 가능 할수도..?)

        3. DTO를 분리하는 게 맞을까?
        - 의미적으로는 DeviceDTO, ResourceDTO 로 분리하는게 맞는 것 같지만
        - 긁어오는 데이터들의 각 로우는 한 디바이스에 여러 리소스가 있을 때
        - Device에 대한 중첩된 정보가 있음
        - 아울러 ResourceDTO 를 분리 해도 이는 정확히 Resource Entity Class 의 DT 기능만을 하는 객체라고 보기 어려움
        - 현재 서비스 단에서는 ResourceGroup Enum 을 사용하고 있기 때문
    */
    ArrayList<DeviceStatusDTO> findDeviceStatusList(DeviceVO param);
    Integer getDvcCntBydvcSeq(@Param("dvcSeq") Integer dvcSeq);
    void deleteDvcByDvcSeq(@Param("dvcSeq") Integer dvcSeq);
}
