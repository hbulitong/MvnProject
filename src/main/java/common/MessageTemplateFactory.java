package common;

import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import common.bean.RequestBody;

/**
 * ������Ϣģ��
 * @author Administrator
 *
 */
public class MessageTemplateFactory {
	/**
	 * ͸��ģ��
	 * @param req
	 * @return
	 */
	public static TransmissionTemplate getTransmissionTemplate(RequestBody req){
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(req.getAppId());
		template.setAppkey(req.getAppey());
		template.setTransmissionContent("�����õ�͸���ı�");
		template.setTransmissionType(1);
		//�����IOS�豸��Ҫ����APNS��ز���
		//APNPayload payload=new APNPayload();
		return template;
	}
	
	public static LinkTemplate getlinkTemplateDemo(RequestBody requsetBody) {
        LinkTemplate template = new LinkTemplate();
        // ����APPID��APPKEY
        template.setAppId(requsetBody.getAppId());
        template.setAppkey(requsetBody.getAppey());
        // ����֪ͨ������������
        template.setTitle("Single��ʾ");
        template.setText("hbulitong���㷢����Ϣ��");
        // ����֪ͨ��ͼ��
        template.setLogo("icon.png");
        // ����֪ͨ������ͼ�꣬��дͼ��URL��ַ
        template.setLogoUrl("");
        // ����֪ͨ�Ƿ����壬�𶯣����߿����
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // ���ô򿪵���ַ��ַ
        template.setUrl("http://www.baidu.com");
        return template;
    }
}
