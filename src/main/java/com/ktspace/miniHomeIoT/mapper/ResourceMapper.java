package com.ktspace.miniHomeIoT.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface ResourceMapper {
    ArrayList<HashMap<String, String>> findResources(@Param("dvcSeq") Integer dvcSeq,
                                                     @Param("rscGrp") String rscGrp);

    void updateRscValueByDvcSeq(@Param("dvcSeq") Integer dvcSeq,
                                @Param("rscGrp") String rscGrp,
                                @Param("value") String value);
}