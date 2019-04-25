package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.listener.MessageListener;

public class KafkaMessageListener implements MessageListener<String,String>{
	private static Logger LOG = Logger.getLogger(KafkaMessageListener.class);
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
	    LOG.info("������Ϣtopic��"+data.topic()+" value:"+data.value());
	    String topic = data.topic();
	    String content = data.value();
	    //��ͬʱ�������topic�����ݲ�ͬtopic����ͬ��ҵ��
	    if (topic.equals("topica")) {           
	        LOG.info("###############topic:"+topic+" value:"+content);
	    } else if (topic.equals("topicb")) {
	    	LOG.info("###############topic:"+topic+" value:"+content);
	    } 
	    
		
	}

}
