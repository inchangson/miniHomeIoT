package com.ktspace.miniHomeIoT.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface ResourceMapper {
    /**
     *
     * @param userId
     * @param dvcSeq
     * @param rscGrp
     * @param value
     * @return DB에 update된 row 개수를 반환
     */
    Integer updateRscValueByDvcSeq(@Param("userId") String userId,
                                @Param("dvcSeq") Integer dvcSeq,
                                @Param("rscGrp") String rscGrp,
                                @Param("value") String value);

    /**
     * 해당하는 리소스 값 변경에 대한 로그를 남깁니다.
     * @param dvcSeq
     * @param rscGrp
     * @param value
     * @return DB에 insert 된 row 개수를 반환
     */
    Integer insertRscLog(@Param("dvcSeq") Integer dvcSeq,
                         @Param("rscGrp") String rscGrp,
                         @Param("value") String value);
}