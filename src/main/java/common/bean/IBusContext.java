package common.bean;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;

public interface IBusContext {
	//��ʼ����������
	public void jsonToXml();
	
	public String getParam(String key);
}
