package com.music.socket.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.music.socket.service.SearchSocketService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Validated
@RestController
@CrossOrigin
@ServerEndpoint("/socket")
public class WebSocketController {
    @Resource
    private SearchSocketService searchSocketService;
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<>();
    private static ConfigurableApplicationContext applicationContext;
    private Session session;
    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        WebSocketController.applicationContext = applicationContext;
    }

    @OnOpen
    public void onOpenWeb(Session session) {
        searchSocketService = applicationContext.getBean(searchSocketService.getClass());
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("New Connect has been created! Now the number of connection is : " + getOnlineCount());
    }
    @SneakyThrows
    @OnMessage// 收到消息的时候执行
    public void onMessage(String condition,Session session) {
        JSONObject jsonObject = JSONObject.parseObject(condition);
        // 获取之后需要传输的对象
        System.out.println("YES");

        searchSocketService.searchElement(condition);
        sendMessage();
    }

    @OnClose
    public void onClose(Session session) {
        subOnlineCount();
        webSocketSet.remove(session);
        System.out.println("One connection has disconnected! Now the number of connection is : " + getOnlineCount());
        System.out.println("Close connecting");
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("Some error has happened");
        error.printStackTrace();
    }

    public void sendMessage() throws IOException{

    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketController.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketController.onlineCount--;
    }

}
