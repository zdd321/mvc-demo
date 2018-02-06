package com.zdd.config.websocket;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.messaging.simp.user.UserSessionRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class SystemWebSocketHandler implements WebSocketHandler {

	private Logger log=Logger.getLogger(SystemWebSocketHandler.class);
	private static final ArrayList<WebSocketSession> user=new ArrayList<WebSocketSession>();
	
	//相当于@OnOpen
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		user.add(session);
		
		session.sendMessage(new TextMessage("connect"));
		session.sendMessage(new TextMessage("new_msg"));

	}

	//相当于@OnMessage
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		session.sendMessage(new TextMessage(new Date()+""));

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if(session.isOpen()){
			session.close();
		}
		user.remove(session);
	}

	//相当于@OnClose
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		user.remove(session);

	}

	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}

	/**
	 * 消息群发
	 */
	public void sendMessageToAll(TextMessage message){
		for(WebSocketSession singleuser:user){
			try {
				if(singleuser.isOpen()){
					singleuser.sendMessage(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
