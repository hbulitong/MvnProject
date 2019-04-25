package common.util;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.json.JSONArray;
import org.json.JSONObject;

public class MessageTransferUtil {
	private static Logger log=Logger.getLogger(MessageTransferUtil.class);
	public static void jsonToXml(Element context,JSONObject obj){
		Iterator<String> iters=obj.keys();
		while(iters.hasNext()){
			String key=iters.next();
			Object value=obj.get(key);
			if(value instanceof JSONArray){  //��array����
				JSONArray jsonArray=(JSONArray)value;
				for(int i=0;i<jsonArray.length();i++){  //ѭ������
					Element ele=context.addElement(key);
					Object obj1=jsonArray.get(i);
					if(obj1 instanceof JSONObject){
						jsonToXml(ele, (JSONObject)obj);   //�ݹ����
					}else{
						ele.addText((String)obj1);
					}
				}
				
			}else if(value instanceof JSONObject){   //jsonObject����
				Element ele=context.addElement(key);
				jsonToXml(ele, (JSONObject)value);
			}else{				//����
				Element ele=context.addElement(key);
				ele.addText(value.toString());
			}
		}
		log.info(context.asXML());
		
		XMLUtil.getNodes(context);   //������ʾ
		
	}
	
	
}
