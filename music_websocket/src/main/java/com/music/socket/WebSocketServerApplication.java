package com.music.socket;


import com.music.socket.controller.WebSocketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

@SpringBootApplication
public class WebSocketServerApplication  {
    public static void main(String[] args) {
        //解决springboot和websocket之间使用@autowired注入为空问题
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebSocketServerApplication.class, args);
        //这里将Spring Application注入到websocket类中定义的Application中。
        WebSocketController.setApplicationContext(applicationContext);
    }


}
