package com.ktspace.miniHomeIoT.repository;

import com.ktspace.miniHomeIoT.domain.*;

import java.util.List;
import java.util.Optional;

public interface HomeIoTRepository {
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
