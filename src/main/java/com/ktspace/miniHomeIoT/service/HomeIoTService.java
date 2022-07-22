package com.ktspace.miniHomeIoT.service;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.domain.Resource;
import com.ktspace.miniHomeIoT.domain.ResourceGroup;
import com.ktspace.miniHomeIoT.repository.HomeIoTRepository;
import com.ktspace.miniHomeIoT.repository.MyBatisHomeIoTRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeIoTService {

        private final HomeIoTRepository homeIoTRepository = new MyBatisHomeIoTRepository();

//    public HomeIoTService(HomeIoTRepository homeIoTRepository) {
//        this.homeIoTRepository = homeIoTRepository;
//    }

        public List<Device> getUserDevices(String userId) {
            return homeIoTRepository.findAllDevices(userId);
        }


        public Optional<Device> readDeviceInfo(int devSeq){
        return homeIoTRepository.findByDeviceSeq(devSeq);
    }


    public String controlResource(int devSeq, String rscGrpName, String value){
        Resource resource = new Resource(ResourceGroup.findByName(rscGrpName), value);

        homeIoTRepository.saveResource(resource, devSeq);

        boolean isSucceed = value.equals(homeIoTRepository
                            .getResource(ResourceGroup.findByName(rscGrpName), devSeq)
                            .getValue());

        return isSucceed ? "제어 성공" : "제어 실패";
    }


    public void deleteDevice(int devSeq){

    }


    public void createDevice(int devSeq){

    }
}
