package ru.geekbrains.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class CommonAdvices {
    private final EurekaClient eurekaClient;

    @ModelAttribute
    public void pictureServiceUrlAttribute(Model model) {
        InstanceInfo server = eurekaClient.getNextServerFromEureka("SHOP-GATEWAY", false);
        log.info("Picture service instance: {}", server);
        model.addAttribute("pictureServiceUrl", server.getHomePageUrl() + "picture-service");
    }
}
