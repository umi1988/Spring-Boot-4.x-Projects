package com.starttohkar.controller;

import com.starttohkar.model.Subscriber;
import com.starttohkar.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    public SubscriberService service;

    @GetMapping("/findAllSubscribers")
    public List<Subscriber> findAllSubscriber(){
        return service.getSubscribers();
    }
}
