package common.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class XMLUtil {
	
	private static final Logger log=Logger.getLogger(XMLUtil.class);
	/**
	 * ��ָ���ڵ㿪ʼ,�ݹ���������ӽڵ�
	 * @author chenleixing
	 */
	public static void getNodes(Element node){
		log.info("--------------------");
		
		//��ǰ�ڵ�����ơ��ı����ݺ�����
		log.info("��ǰ�ڵ����ƣ�"+node.getName());//��ǰ�ڵ�����
		log.info("��ǰ�ڵ�����ݣ�"+node.getTextTrim());//��ǰ�ڵ�����
		List<Attribute> listAttr=node.attributes();//��ǰ�ڵ���������Ե�list
		for(Attribute attr:listAttr){//������ǰ�ڵ����������
			String name=attr.getName();//��������
			String value=attr.getValue();//���Ե�ֵ
			log.info("�������ƣ�"+name+"����ֵ��"+value);
		}
		
		//�ݹ������ǰ�ڵ����е��ӽڵ�
		List<Element> listElement=node.elements();//����һ���ӽڵ��list
		for(Element e:listElement){//��������һ���ӽڵ�
			getNodes(e);//�ݹ�
		}
	}

}
