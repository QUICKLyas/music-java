package com.music.socket.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class HttpAuthHandler extends TextWebSocketHandler {
// socket 建立成功事件
//    @Override
//    public void afterConnectEstablished(WebSocketSession session) throws Exception {
//        Object sessionId = session.getAttributes().get("session_id");
//        if(sessionId != null) {
////            WsSessionManage
//        }
//    }
}
