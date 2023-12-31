package com.spring.websocket.Endpoint;

import com.alibaba.fastjson.JSON;
import com.spring.dao.Combo;
import com.spring.domain.SqlTable.ComboTable;
import com.spring.websocket.configurator.GetHttpSessionConfigurator;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/comboInfo", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j

public class BroadcastComboInfo {

    // websocket对象是多对象的,不受spring管理(受tomcat),spring管理的是单例(共享对象引用
    private static BroadcastComboInfo broadcastComboInfo; //关键点1
    private static Map<String, BroadcastComboInfo> onlineUsers = new ConcurrentHashMap<>();

    private Session session;
    private HttpSession httpSession;
    private static Combo combo;


    // 关键点2,防止注入的BroadcastComboInfo实例变量为null的情况
    // @PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    // 该注解只能作用于方法上，执行依赖注入后执行任何初始化操作。
    @PostConstruct
    public void init() {
        broadcastComboInfo = this;
        // 在WebSocket对象创建时，通过调用WebSocketManager.setWebSocket()方法来设置WebSocket对象的引用。
    }

    @Autowired
    public void setCombo(Combo combo) {
        BroadcastComboInfo.combo = combo;
    }

    @OnOpen
    public void open(Session session, EndpointConfig config) throws IOException, EncodeException {
        log.info("WebSocket链接建立: " + broadcastComboInfo.getClass().getSimpleName());

        broadcastComboInfo.session = session;
        broadcastComboInfo.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        // 为每一个客户端(以httpsession中存的用户名来区别)储存websocket session
        onlineUsers.put(broadcastComboInfo.httpSession.getId(), this);

        // 广播所有消息
        broadcastComboInfo.sendCombo(0);
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        log.info(broadcastComboInfo.getClass().getSimpleName() + ":,code " + reason.getCloseCode() + "  reason:" + reason.getReasonPhrase() + " -- Websocket");
    }

    @OnMessage
    public void message(String msg, Session session) throws IOException {
        if (Objects.equals(msg, "heartbeat")) {
            broadcastComboInfo.session.getBasicRemote().sendText("receive heartbeat");
            log.info("websocket心跳 --" + broadcastComboInfo.getClass().getSimpleName());
        }
        log.info(broadcastComboInfo.getClass().getSimpleName() + "收到消息：" + msg + " --websocket");
    }

    @Data
    private static class MyObject {
        private int type;
        private List<ComboTable> list;

        public MyObject(int type, List<ComboTable> list) {
            this.type = type;
            this.list = list;
        }
    }

    public void sendCombo(int i) throws IOException {
        MyObject res = new MyObject(i, combo.getAllInfo());
        broadcastComboInfo.session.getBasicRemote().sendText(JSON.toJSONString(res));
    }
}
