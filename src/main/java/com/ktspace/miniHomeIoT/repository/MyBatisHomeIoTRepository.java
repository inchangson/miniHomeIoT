package com.ktspace.miniHomeIoT.repository;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.domain.Resource;
import com.ktspace.miniHomeIoT.domain.Service;
import com.ktspace.miniHomeIoT.domain.User;

import java.util.List;
import java.util.Optional;

public class MyBatisHomeIoTRepository implements HomeIoTRepository{
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Device> findAllDevices(String userId) {
        return null;
    }

    @Override
    public List<Service> findAllServices(String userId) {
        return null;
    }

    @Override
    public Device saveDevice(Device device) {
        return null;
    }

    @Override
    public void removeDevice(Device device) {

    }

    @Override
    public Optional<Device> findByUser(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Device> findByService(Integer serviceSeq) {
        return Optional.empty();
    }

    @Override
    public List<Resource> findAllResources(Integer deviceSeq) {
        return null;
    }

    @Override
    public Resource saveResource(Resource resource, Integer deviceSeq) {
        return null;
    }
}
