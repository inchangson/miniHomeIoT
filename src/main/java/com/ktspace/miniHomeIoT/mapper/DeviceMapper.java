package com.ktspace.miniHomeIoT.mapper;

import com.ktspace.miniHomeIoT.dto.DeviceStatusDTO;
import com.ktspace.miniHomeIoT.vo.DeviceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Device 관련 기능을 위한 매퍼 클래스입니다.
 */
@Mapper
public interface DeviceMapper {
    /**
     * 사용자 ID, 장치 Sequence를 통해
     * 해당하는 장치 정보를 리스트 형태로 불러옵니다.
     * 만약 Parameter(userId/ deviceSeq)에 빈 값이 있으면
     * 해당 조건은 무시하고 조회합니다.
     * @param param
     * @return 장치 정보를 리스트로 반환
     */
    ArrayList<DeviceStatusDTO> findDvcList(DeviceVO param);

    /**
     * 해당하는 deviceSeq를 삭제합니다.
     * 단 userId가 일치하지 않는, 즉 해당 사용자가 장치의 주인이 아니면
     * 삭제되지 않습니다.
     * @param userId
     * @param dvcSeq
     * @return 삭제 row 개수를 반환
     */
    Integer deleteDvcByDvcSeq(@Param("userId") String userId, @Param("dvcSeq") Integer dvcSeq);
}
