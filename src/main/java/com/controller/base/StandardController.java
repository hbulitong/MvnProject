package com.controller.base;

import javax.servlet.http.HttpServletRequest;

import common.bean.BusContext;
import common.bean.IBusContext;

public abstract class StandardController {
	//��������
	protected ThreadLocal<IBusContext> context=new ThreadLocal<IBusContext>();
	private ThreadLocal<String> SessionID=new ThreadLocal<String>();
	
	public String execute(HttpServletRequest request){
		try{
			this.SessionID.set(request.getSession().getId());
			initContext(request);
			this.context.get().jsonToXml();
			realExecute(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "";
	}
	//��ʼ��context
	public void initContext(HttpServletRequest request){
		this.context.set(new BusContext(request));
	}
	//���󷽷����ɾ����ÿ��controllerʵ��
	public abstract void realExecute(HttpServletRequest request) throws Exception;
}
