package com.spring.listener;

import com.spring.websocket.Endpoint.Redirect;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@WebListener
@Component
@Order(1) // 设置较低的顺序值
@Slf4j
public class OnlineUserSessionListener implements HttpSessionListener {
    // 使用Map保存所有的HttpSession
    @Getter
    private final static Map<String, HttpSession> sessionMap = new HashMap<>();

    // 保证多线程安全
    @Getter
    private static final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());

    // 获取websockt注入
    private static Redirect redirect;

    @Autowired
    public void setRedirect(Redirect redirect) {
        OnlineUserSessionListener.redirect = redirect;
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        // 将新创建的HttpSession添加到Map.Set中
        sessionIds.add(session.getId());
        sessionMap.put(session.getId(), session);
        System.out.println("Session Created: ID-" + session.getId() + " Name-" + session.getAttribute("name"));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        if (redirect == null)
            log.warn("redirect is null");
        else {
            // 通知客户端session过期
            try {
                redirect.redirectUrl(httpSession.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // 从Map,Set中移除已销毁的HttpSession
        sessionIds.remove(httpSession.getId());
        sessionMap.remove(httpSession.getId());

        System.out.println("Session Destroyed: ID-" + httpSession.getId() + " Name-" + httpSession.getAttribute("name"));
    }

    // 获取在线人数
    public static int getOnlineCnt() {
        return sessionIds.size();
    }
}
