package com.ktspace.miniHomeIoT.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface DeviceMapper {
    HashMap<String, Object> findByDeviceSeq(int devSeq);
    ArrayList<HashMap<String, Object>> findAllByUserId(String userId);
}
