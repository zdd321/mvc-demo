package com.zdd.config.rabbit;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;

public class MQErrorHandler implements ErrorHandler {

	protected Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void handleError(Throwable cause) {
    	logger.error("An error occurred.", cause);
    }

}
