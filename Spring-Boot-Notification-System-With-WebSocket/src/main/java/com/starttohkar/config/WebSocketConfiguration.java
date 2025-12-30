package com.starttohkar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // /topic/cricket
        // /topic/orders
        //  -> /app/<url defined in controller> for client side
        registry.enableSimpleBroker("/topic"); // This is for broadcasting the messages
        registry.setApplicationDestinationPrefixes("/app"); // This is for client to access it in their application. It's endpoint url.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // This ws define here is the websocket endpoint that client used to establish the connection.
//                .setAllowedOrigins("*") // * is to support all the host and port for client to access the backend app
                .setAllowedOrigins("http://localhost:63342")
                .withSockJS(); // This is the fallback because few browser don't support the websocket.If the websocket support fails in the
                              // browser then it will fall back to http streaming or long pooling
    }
}
