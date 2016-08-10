package com.sun.until;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUntil {
	 	public static final String token = "skyOfweixin";
	    /**
		 * 验证请求是否合法
		 * 
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public boolean accessing(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if (isEmpty(signature)) {
				return false;
			}
			if (isEmpty(timestamp)) {
				return false;
			}
			if (isEmpty(nonce)) {
				return false;
			}
			if (isEmpty(echostr)) {
				return false;
			}
			String[] ArrTmp = {token,timestamp, nonce };
			Arrays.sort(ArrTmp);//排序
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ArrTmp.length; i++) {
				sb.append(ArrTmp[i]);
			}
			String pwd = Encrypt(sb.toString());//加密
			if (trim(pwd).equals(trim(signature))) {//比较微信端传来的字符
				return true;
			} else {
				return false;
			}
		}
	    /**
		 * SHA-1加密
		 * 
		 * @param strSrc
		 * @return
		 */
		private String Encrypt(String strSrc) {
			MessageDigest md = null;
			String strDes = null;

			byte[] bt = strSrc.getBytes();
			try {
				md = MessageDigest.getInstance("SHA-1");
				md.update(bt);
				strDes = bytes2Hex(md.digest()); // to HexString
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Invalid algorithm.");
				return null;
			}
			return strDes;
		}

		/**
		 * byte转字符串
		 * 
		 * @param bts
		 * @return
		 */
		public String bytes2Hex(byte[] bts) {
			String des = "";
			String tmp = null;
			for (int i = 0; i < bts.length; i++) {
				tmp = (Integer.toHexString(bts[i] & 0xFF));
				if (tmp.length() == 1) {
					des += "0";
				}
				des += tmp;
			}
			return des;
		}
		/**
		 * 判断是否为空
		 * 
		 * @param str
		 * @return
		 */
		private boolean isEmpty(String str) {
			return null == str || "".equals(str) ? true : false;
		}

		/**
		 * 去除空格
		 * 
		 * @param str
		 * @return
		 */
		private String trim(String str) {
			return null != str ? str.trim() : str;
		}
		/**
		 * 获取流中的xml数据
		 * 
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		public String message(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			InputStream is = request.getInputStream();
			int size = request.getContentLength();
			byte[] buffer = new byte[size];
			byte[] xmldataByte = new byte[size];
			int count = 0;
			int rbyte = 0;
			while (count < size) {
				rbyte = is.read(buffer);
				for (int i = 0; i < rbyte; i++) {
					xmldataByte[count + i] = buffer[i];
				}
				count += rbyte;
			}
			is.close();
			String requestStr = new String(xmldataByte, "UTF-8");
			return requestStr;
		}
		/**
		 * 将信息返回
		 * 
		 * @param response
		 * @param content
		 */
		public void writeToPw(HttpServletResponse response, String content) {
			try {  
	            OutputStream os = response.getOutputStream();  
	            os.write(content.getBytes("UTF-8"));  
	            os.flush();  
	            os.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
		}

}
