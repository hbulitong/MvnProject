package com.service.impl.getui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.service.getui.IPushMsgService;

import common.MessageTemplateFactory;
import common.ParamConstant;
import common.bean.MessageUser;
import common.bean.RequestBody;
/**
 * ����������Ϣ����
 * @author Administrator
 *
 */
@Service
public class PushMsgServiceImpl implements IPushMsgService{
	
	private Logger log=Logger.getLogger(this.getClass());
	
	/**
	 * ������Ϣ��APP�����û�
	 */
	public Map<String, Object> pushToSingle(RequestBody requsetBody) {
		Map<String,Object> result=new HashMap<String,Object>();
		//�������ӣ���ʼ��
		IGtPush push = new IGtPush(ParamConstant.Host,requsetBody.getAppey(), requsetBody.getMasterSecret());
		TransmissionTemplate template = MessageTemplateFactory.getTransmissionTemplate(requsetBody);
		LinkTemplate template1=MessageTemplateFactory.getlinkTemplateDemo(requsetBody);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // ������Чʱ�䣬��λΪ���룬��ѡ
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template1);
        // ��ѡ��1Ϊwifi��0Ϊ���������绷���������ֻ����ڵ���������������Ƿ��·�
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(requsetBody.getAppId());
        target.setClientId(requsetBody.getClientId());
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            log.info(ret.getResponse().toString());
            result.put("ret", ret.getResponse().toString());
        } else {
            log.info("��������Ӧ�쳣");
            result.put("ret", "��������Ӧ�쳣");
        }
        return result;
        
		
	}
	/**
	 * ������Ϣ��APP����û�
	 * @param requsetBody
	 * @return
	 */
	public Map<String, Object> pushToArray(RequestBody requsetBody){
		log.info("���ƿ�ʼ++++++++++++++");
		Map<String,Object> result=new HashMap<String,Object>();
		//�������ӣ���ʼ��
		IGtPush push = new IGtPush(ParamConstant.Host,requsetBody.getAppey(), requsetBody.getMasterSecret());
		TransmissionTemplate template = MessageTemplateFactory.getTransmissionTemplate(requsetBody);
		//����֪ͨģ��
		LinkTemplate template1=MessageTemplateFactory.getlinkTemplateDemo(requsetBody);
		ListMessage message=new ListMessage(); //�����͵������û�������
		message.setData(template1); //����ģ��
		// ������Ϣ���ߣ�����������ʱ��
		message.setOffline(true);
		// ������Чʱ�䣬��λΪ���룬��ѡ
		message.setOfflineExpireTime(24*1000*3600);
		//��������Ŀ��
		ArrayList<Target> targets=new ArrayList<Target>();
		MessageUser[] messageUser=requsetBody.getUser();
		for(MessageUser user:messageUser){		//ѭ������
			Target target=new Target();
			target.setAppId(requsetBody.getAppId());
			log.info("�û�CID:"+user.getClientId());
			target.setClientId(user.getClientId());
			targets.add(target);
		}
		// taskId����������ʱȥ���Ҷ�Ӧ��message
        String taskId = push.getContentId(message);
        IPushResult ret = null;
        try{
        	ret=push.pushMessageToList(taskId, targets);
        }catch(RequestException e){
        	e.printStackTrace();
        }
        
        if (ret != null) {
            log.info(ret.getResponse().toString());
            result.put("ret", ret.getResponse().toString());
        } else {
            log.info("��������Ӧ�쳣");
            result.put("ret", "��������Ӧ�쳣");
        }
        return result;
	}
	
	

}
