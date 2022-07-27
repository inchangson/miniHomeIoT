package com.ktspace.miniHomeIoT.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface DeviceMapper {
    HashMap<String, Object> findByDeviceSeq(int devSeq);
    ArrayList<HashMap<String, Object>> findAllByUserId(@Param("userId") String userId);
}
