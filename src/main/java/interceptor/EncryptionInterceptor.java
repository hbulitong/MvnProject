package interceptor;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import common.ParamConstant;

/**
 * ���������ӽ���
 * @author Administrator
 *
 */
public class EncryptionInterceptor implements HandlerInterceptor {
	
	private Logger log=Logger.getLogger(this.getClass());
	public EncryptionInterceptor(){
		log.info("��������ӽ��ܿ�ʼ������");
	}
	
	/**
	 * afterCompletion��������DispatcherServlet������ͼ����Ⱦ֮��ִ�е��ã���Ҫ�ǽ���һЩ��Դ����ȹ���
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * ִ��controller֮��ִ��
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * ִ��controller֮ǰִ��
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//��ֻ�ܶ�ȡһ�Σ����Խ����ݴ洢��������ʼ������������
		StringBuffer sf =new StringBuffer();
		BufferedReader br;
		br=request.getReader();
		String cache;
		while((cache=br.readLine())!=null){
			sf.append(cache);
		}
		log.info("ԭ�ģ�"+sf.toString());
//		Enumeration<String> param=request.getParameterNames();
//		Map<String,Object> map=new HashMap<String,Object>();
//		while(param.hasMoreElements()){
//			String name=(String)param.nextElement();
//			map.put(name, request.getParameter(name));
//		}
//		JSONObject json=JSONObject.fromObject(map);   //mapתjson
		request.setAttribute(ParamConstant.REQUESTPARAM, sf.toString());
		log.info("��μ���:"+sf.toString());
		return true;
	}

}
