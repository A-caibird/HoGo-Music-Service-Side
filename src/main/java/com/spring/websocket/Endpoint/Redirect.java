package com.spring.websocket.Endpoint;

import com.spring.websocket.configurator.GetHttpSessionConfigurator;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/logout", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class Redirect {


    // 为每一个用户存一个下线实例
    private static Map<String, Redirect> onlineUsers = new ConcurrentHashMap<>();
    private Session session;
    private static Redirect redirect;

    @PostConstruct
    public void init() {
        redirect = this;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException, EncodeException {
        log.info("WebSocket链接建立: " + redirect.getClass().getSimpleName());

        redirect.session = session;
        this.session = session;

        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        onlineUsers.put(httpSession.getId(), this);
    }

    @OnMessage
    public void onMessage(String msg) throws IOException {
        if (Objects.equals(msg, "heartbeat")) {
            redirect.session.getBasicRemote().sendText("receive heartbeat");
        }
        log.info(redirect.getClass().getSimpleName() + "收到消息：" + msg + " --websocket");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info(redirect.getClass().getSimpleName() + ": close ,code " + reason.getCloseCode() + "  reason:" + reason.getReasonPhrase() + " -- Websocket");
    }

    public void redirectUrl(String id) throws IOException {
        Redirect re = onlineUsers.get(id);
        if (re == null) {
            log.warn("null redirect");
        } else {
            log.info("下线通知!");
            re.session.getBasicRemote().sendText("logout");
            onlineUsers.remove(id);
        }
    }
}
