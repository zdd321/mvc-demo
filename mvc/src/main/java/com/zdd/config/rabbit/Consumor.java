package com.zdd.config.rabbit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

@Component
public class Consumor {

	private Logger logger=LoggerFactory.getLogger(Consumor.class);
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	MessageConverter msgConverter;
	
	
	@RabbitListener(queues="my_queue")
	public void reciveMessage(Message message,Channel channel){
		System.out.println("������");
		String received = msgConverter.fromMessage(message).toString();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map;
		try {
			map = mapper.readValue(received, HashMap.class);
			String orderId = map.get("orderid").trim();
			logger.info("收到消息:"+orderId);
			System.out.println("������Ϣ��"+orderId);
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
