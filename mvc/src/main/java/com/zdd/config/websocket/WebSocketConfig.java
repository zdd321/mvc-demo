package com.zdd.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//为了解决跨域访问的问题需要加上.setAllowedOrigins("*")
		registry.addHandler(systenWebSocketHandler(), "/webSocketServer").setAllowedOrigins("*");
		registry.addHandler(systenWebSocketHandler(), "/webSocketServer/sockjs")
			.setAllowedOrigins("*").withSockJS();
		
	}

	@Bean
	public WebSocketHandler systenWebSocketHandler(){
		return new SystemWebSocketHandler();
	}
}
