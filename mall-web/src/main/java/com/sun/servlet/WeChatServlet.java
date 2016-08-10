package com.sun.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.until.CommonUntil;

public class WeChatServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//微信发来验证是get方法
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			/** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */ 
	        String echostr = request.getParameter("echostr");
	        if(!"".equals(echostr) && echostr.length()>0 ){
	        	//验证来源
	        	CommonUntil until = new CommonUntil();
	        	if(until.accessing(request, response)){
	        		//返回
		        	response.getWriter().print(echostr);
	        	}else{
	        		
	        	}
	        }else{
	        	
	        }
		}catch(Exception e){
			
		}
	}
	@Override
	public void init() throws ServletException{
		//容器启动时执行
		System.out.println("容器启动时候执行~~~~~");
	}
}
