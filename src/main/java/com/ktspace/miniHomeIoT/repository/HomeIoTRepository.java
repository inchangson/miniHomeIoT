package com.ktspace.miniHomeIoT.repository;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.domain.Resource;
import com.ktspace.miniHomeIoT.domain.Service;
import com.ktspace.miniHomeIoT.domain.User;

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
    void removeDevice(Device device);
    Optional<Device> findByUser(String userId);
    Optional<Device> findByService(Integer serviceSeq);
    List<Resource> findAllResources(Integer deviceSeq);
    //endregion

    //region Resource
    Resource saveResource(Resource resource, Integer deviceSeq);
    //endregion
}
