package com.ktspace.miniHomeIoT.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface ResourceMapper {
    ArrayList<HashMap<String, String>> findByDvcSeq(int dvcSeq);

    void updateRscValueByDvcSeq(int dvcSeq);
}