package com.jasa.Get.Help.service;

import com.jasa.Get.Help.model.ServiceProvider;
import com.jasa.Get.Help.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService{

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;


    @Override
    public List<ServiceProvider> getAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }
}
