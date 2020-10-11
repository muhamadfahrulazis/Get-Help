package com.jasa.Get.Help.controller;

import com.jasa.Get.Help.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

//    @GetMapping("/home")
//    public String viewHome(){
//        return "index";
//    }

    @GetMapping("/service_providers")
    public String viewServiceProvider(Model model){
        model.addAttribute("listServiceProviders", serviceProviderService.getAllServiceProviders());
        return "registered_sp";
    }
}
