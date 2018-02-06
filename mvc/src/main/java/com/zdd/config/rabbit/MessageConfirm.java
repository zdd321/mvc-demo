package com.zdd.config.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class MessageConfirm implements ConfirmCallback {

	protected Logger logger = Logger.getLogger(this.getClass());

	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
			logger.error("��Ϣ�޷����ｻ����[ack: " + ack + ",correlationData: " + correlationData + ",cause : " + cause+"].");
        }
	}
}
