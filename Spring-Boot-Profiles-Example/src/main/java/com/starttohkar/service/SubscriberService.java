package com.starttohkar.service;

import com.starttohkar.dao.SubscriberRepository;
import com.starttohkar.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile(value = { "local", "dev", "prod" })
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<Subscriber> getSubscribers(){
        return subscriberRepository.findAll();
    }
}
