package com.spring.websocket;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/logout", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class Redirect {
    private static Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    private static Redirect redirect;

    @PostConstruct
    public void init() {
        redirect = this;
    }

    @OnOpen
    public void open(Session session, EndpointConfig config) throws IOException, EncodeException {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        onlineUsers.put(httpSession.getId(), session);
    }

    @OnClose
    public void close(Session session) {
    }

    public void redirectUrl(HttpSession httpSession) throws IOException {
        Session user = onlineUsers.get(httpSession.getId());
        user.getBasicRemote().sendText("session过期,请重新登录");
    }
}
