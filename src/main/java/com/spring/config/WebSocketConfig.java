package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    //  @Bean 注解用于告诉 Spring 容器要创建一个特定的 bean 对象，并将其添加到应用程序的上下文中。在这种情况下，serverEndpointExporter() 方法是一个 bean 方法，它返回一个 ServerEndpointExporter 对象。

    //  ServerEndpointExporter 是 Spring 提供的一个用于 WebSocket 服务器端点的辅助类。通过在 WebSocket 配置类中定义一个名为 serverEndpointExporter() 的 @Bean 方法，我们将 ServerEndpointExporter bean 添加到应用程序上下文中。

    //  ServerEndpointExporter 的作用是自动注册和管理带有 @ServerEndpoint 注解的 WebSocket 服务器端点。它会扫描应用程序中的类，查找带有 @ServerEndpoint 注解的类，并将它们注册为 WebSocket 服务器端点(自动注入到类里面,不需要显示说明)，以便客户端可以连接和与之通信。
}
