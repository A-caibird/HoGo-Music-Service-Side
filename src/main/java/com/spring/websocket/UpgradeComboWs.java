package com.spring.websocket;

import com.alibaba.fastjson.JSON;
import com.spring.dao.Combo;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@ServerEndpoint("/websocket/upgradeCombo")
@Component
public class UpgradeComboWs {
    private HttpSession httpSession;
    private Session session;

    private static Combo combo;
    // 注意事项，注入bean的方式，因为spring管理的都是单例。WebSocket是对对象 相冲突
    // 注入方式，这么写就可以了。

    @Autowired
    public void setCombo(Combo combo) {
        UpgradeComboWs.combo = combo;
    }

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        this.session = session;

        // 广播所有消息
        sendComboInfo();
    }

    @OnClose
    public void close(Session session) {
    }

    @OnMessage
    public void message(String msg, Session session) {
        System.out.println(msg);
    }

    public void sendComboInfo() throws EncodeException, IOException {
      this.session.getBasicRemote().sendText(JSON.toJSONString(combo.getComboInfo()));
    }
}
