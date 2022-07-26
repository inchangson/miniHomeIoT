package com.ktspace.miniHomeIoT.mapper;

import com.ktspace.miniHomeIoT.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface HomeIoTMapper {

    ArrayList<HashMap<String, Object>> findAll();
    //region User
    User saveUser(User user);

    Optional<User> findById(String id);

    List<Device> findAllDevices(String userId);

    List<Service> findAllServices(String userId);
    //endregion

    //region Device
    Device saveDevice(Device device);

    void removeDevice(Integer deviceSeq);

    Optional<Device> findByUser(String userId);

    Optional<Device> findByDeviceSeq(Integer serviceSeq);

    List<Resource> findAllResources(Integer deviceSeq);
    //endregion

    //region Resource
    Resource saveResource(Resource resource, Integer deviceSeq);

    Resource getResource(ResourceGroup resourceGroup, Integer deviceSeq);
    //endregion
}
