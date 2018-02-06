package com.zdd.config.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class MessageReturn  implements ReturnCallback{

	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Environment environment;
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		//将消息发送到错误队列
		RepublishMessageRecoverer recoverer = new RepublishMessageRecoverer(rabbitTemplate,"exchange.direct", "route.error");
		Exception cause = new Exception(new Exception("route_fail_and_republish"));
        recoverer.recover(message,cause);
		logger.error("消息无法到达队列[Returned Message: " + replyText + ",code: " + replyCode + ",exchange: " + exchange + ",routingKey :" + routingKey+"].");
	}


}
