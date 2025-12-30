package com.starttohkar.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    // Server application.
    // Client will access this endpoint as /root_url/secondary_url(/app/sendMessage)
    @MessageMapping("/sendMessage") // we can't define here the @GetMapping/@PostMapping(These are for web). For webSocket we need to use @MessageMapping.
    @SendTo("/topic/notifications")   // Once the request reach here Now we need to send these messages to broker by configuring like this.
    public String sendMessage(String message){
        System.out.println(" Message :- " +  message);
        return message;
    }
}
