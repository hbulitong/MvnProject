package common.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.json.JSONObject;

import common.ParamConstant;
import common.util.MessageTransferUtil;


public class BusContext implements IBusContext{
	private Logger log=Logger.getLogger(BusContext.class);
	private HttpServletRequest request;
	private HttpSession session;
	private Element root;
	//�������
	private Element params;
	//��Ӧ
	private Element response;
	//��ʼ��
	public BusContext(HttpServletRequest request){
		this.request=request;
		this.session=request.getSession();
		init();
	}
	/**
	 * ��ʼ��
	 */
	private void init(){
		Document doc=DocumentHelper.createDocument();  //����document����
		doc.setXMLEncoding("utf-8");  //���ñ����ʽ
		root=doc.addElement("root");
		params=root.addElement("params");
	}

	@Override
	public void jsonToXml(){
		String json=(String)this.request.getAttribute(ParamConstant.REQUESTPARAM);
		log.info("������"+json.toString()); 
		JSONObject jsonObj=null;
		//if(json instanceof JSONObject){
			jsonObj=new JSONObject(json);
			MessageTransferUtil.jsonToXml(params, jsonObj);
		//}else{
			//log.info("�������ݸ�ʽ����");
		//}
	}
	@Override
	public String getParam(String key) {
		try{
			Node node=params.selectSingleNode(key);  //��ȡ�����ڵ㣬����ж�����򷵻ص�һ��
			if(node!=null){
				return node.getText();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
