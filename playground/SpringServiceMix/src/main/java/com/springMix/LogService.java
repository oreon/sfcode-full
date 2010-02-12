package com.springMix;

import javax.jbi.messaging.MessageExchange;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.servicemix.bean.Operation;



public class LogService {

      private static final Log log = LogFactory.getLog(LogService.class);

      @Operation
      public void log(MessageExchange message) {
          log.info("Spring - ServiceMix Integration: " + message);
      }
}
