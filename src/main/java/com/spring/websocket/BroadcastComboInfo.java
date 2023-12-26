package com.spring.websocket;

import com.alibaba.fastjson.JSON;
import com.spring.dao.Combo;
import com.spring.domain.SqlTable.ComboTable;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/comboInfo", configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class BroadcastComboInfo {
    private static Map<String, BroadcastComboInfo> onlineUsers = new ConcurrentHashMap<>();

    private Session session;
    private HttpSession httpSession;
    private static Combo combo;
    // 注意事项，注入bean的方式，因为spring管理的都是单例。WebSocket是对对象 相冲突
    // 注入方式，这么写就可以了。

    private static BroadcastComboInfo broadcastComboInfo; //关键点2

    @PostConstruct  //关键点3,防止注入的BroadcastComboInfo实例变量为null的情况
    public void init() {
        broadcastComboInfo = this;
    }

    @Autowired
    public void setCombo(Combo combo) {
        BroadcastComboInfo.combo = combo;
    }


    @OnOpen
    public void open(Session session, EndpointConfig config) throws IOException, EncodeException {
        System.out.println("WebSocket链接建立");
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
//         为每一个客户端(以httpsession中存的用户名来区别)储存websocket session
        String username = (String) httpSession.getAttribute("name");
        onlineUsers.put(username, this);
//         广播所有消息
//        broadcastComboInfo();
//        broadcastComboInfo.sendCombo(0);
    }


    @OnClose
    public void close(Session session) {
    }

    @OnMessage
    public void message(String msg, Session session) {
        System.out.println(msg);
    }


    // 只是向在线的用户(分配httpSession的用户广播消息)
    public void broadcastComboInfo() throws EncodeException, IOException {
//        Set<String> userlist = onlineUsers.keySet();
//        List<ComboTable> comboList = null;
//        String json = "";
//        try {
//            comboList = combo.getAllInfo();
//            json = JSON.toJSONString(comboList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (String i : userlist) {
//            System.out.println(i);
//            BroadcastComboInfo broadcastComboInfo = onlineUsers.get(i);
//            broadcastComboInfo.session.getBasicRemote().sendText(json);
//        }
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
