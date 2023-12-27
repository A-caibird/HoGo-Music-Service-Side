package com.spring.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.Getter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@WebListener
@Component
@Order(1) // 设置较低的顺序值
public class OnlineUserSessionListener implements HttpSessionListener {
    // 使用Map保存所有的HttpSession
    @Getter
    private static Map<String, HttpSession> sessionMap = new HashMap<>();
    @Getter
    // 保证多线程安全
    private static final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());

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
        HttpSession session = se.getSession();
        // 从Map,Set中移除已销毁的HttpSession
        sessionIds.remove(sessionIds);
        sessionMap.remove(session.getId());

        System.out.println("Session Destroyed: ID-" + session.getId() + " Name-" + session.getAttribute("name"));
    }

    public static int getOnlineCnt() {
        // 获取在线人数
        return sessionIds.size();
    }
}
