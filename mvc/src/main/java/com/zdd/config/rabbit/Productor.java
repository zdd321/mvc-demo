package com.zdd.config.rabbit;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@PropertySource(value="classpath:rabbit.properties")
public class Productor {

	private Logger logger=Logger.getLogger(Productor.class);
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Environment env;
	
	public void sendMessage(String message){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("orderid", message);
		String sendMessage;
		try {
			sendMessage = new ObjectMapper().writeValueAsString(map);
			logger.info("productor:"+message);
			System.out.println("发送消息" +message);
//			rabbitTemplate.convertAndSend("exchange.direct","route.my_queue",sendMessage);
			rabbitTemplate.convertAndSend(env.getProperty("exchange.direct"),
					env.getProperty("route.my_queue"),sendMessage);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
