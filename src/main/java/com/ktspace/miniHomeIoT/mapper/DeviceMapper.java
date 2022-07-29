package com.ktspace.miniHomeIoT.mapper;

import com.ktspace.miniHomeIoT.dto.DeviceStatusDTO;
import com.ktspace.miniHomeIoT.vo.DeviceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface DeviceMapper {
    /*  1. VO 를 통해서 넘겨주는 방식 vs Param으로 설정하는 방식
        - 객체 단위로 관리하는 것이 더 안전->재사용성))할 것이라 생각해서 앞의 방식 선택
        ...진짜그럴까?

        2. 리스트 or HashMap으로 응답 vs DTO 활용
        - 값을 명확히 주기엔 map이나, 2차원 list 형식이 되어야할 것 같은데,
        - map에선 현재 기능에 필요없는 다양한 메서드들이 같이 있기 때문에
        - 현재 기능에 맞춰진 DTO를 따로 생성하는 것이 더 합리적이라 판단
        - 확장성에 대해선 의문이 있음
        - (매 기능마다 DTO를 분리하면 비슷한 기능을 하는 클래스들이 너무 많이 생김)
        - (-> 상속등을 통해서 보완 가능 할수도..?)

        3. DTO를 어떻게 분리해야할까?
        - domain 의 클래스와 동일하게 생각하기엔
        - 현재 서비스 단에서는 ResourceGroup Enum 을 사용하고 있기 때문에
        - 아울러 ResourceDTO 를 분리 해도 이는 정확히 Resource Entity Class 의 DT 기능만을 하는 객체라고 보기 어려움
        - 일단은, dto 본래 의미에 맞게 데이터 전달용도로만 사용하는 것으로..
    */
    ArrayList<DeviceStatusDTO> findDeviceStatusList(DeviceVO param);

    Integer getDvcCntBydvcSeq(@Param("dvcSeq") Integer dvcSeq);

    void deleteDvcByDvcSeq(@Param("dvcSeq") Integer dvcSeq);
}
