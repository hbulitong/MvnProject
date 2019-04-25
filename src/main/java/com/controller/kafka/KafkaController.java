package com.controller.kafka;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.base.StandardController;
@Controller
@RequestMapping(value="/kafka")
public class KafkaController extends StandardController{
		private static Logger log=Logger.getLogger(KafkaController.class);
	  	@Autowired
	    KafkaTemplate kafkaTemplate;

	    @Value("nwbs-optimizeNetwork-task")
	    private  String optimizeTopic ;

	    @Value("nwbs-business-task")
	    private String businessTopic;
	    
	    @RequestMapping(value="/producer.li",method=RequestMethod.POST)
		@ResponseBody
		public String producer(HttpServletRequest request){
			execute(request);
			return "";
		}

		@Override
		public void realExecute(HttpServletRequest request) throws Exception {
			//kafkaTemplate.send(optimizeTopic,params.toJSONString()+"optimizeTopic");
			String user=context.get().getParam("user");
			log.info("user:"+user);
	        kafkaTemplate.send(businessTopic,user+"businessTopic");
	        ListenableFuture<SendResult<String, String>> listenableFuture =  kafkaTemplate.sendDefault(user);
	        //���ͳɹ��ص�
	        SuccessCallback<SendResult<String, String>> successCallback = new SuccessCallback<SendResult<String, String>>() {
	            @Override
	            public void onSuccess(SendResult<String, String> result) {
	                //�ɹ�ҵ���߼�
	            	log.info("onSuccess");
	            }
	        };
	        //����ʧ�ܻص�
	        FailureCallback failureCallback = new FailureCallback() {
	            @Override
	            public void onFailure(Throwable ex) {
	                //ʧ��ҵ���߼�
	            	log.info("onFailure");
	            }
	        };
	        listenableFuture.addCallback(successCallback, failureCallback);
			
		}
}
