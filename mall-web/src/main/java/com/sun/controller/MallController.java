package com.sun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.model.user;
import com.sun.service.UserService;

@Controller
@RequestMapping("/")
public class MallController {
	@Autowired
	UserService userService;
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}
	@RequestMapping("login")
	@ResponseBody
	public String login(HttpServletRequest request,String userId,String password){
		user user = userService.selectUserList("0");
		if( !"".equals(userId) && !"".equals(password)){
			if("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
				return "true";
			}else{
				return "false";
			}
		}else{
			return "false";
		}
	}
	@RequestMapping("insert")
	@ResponseBody
	public String insert(HttpServletRequest request,String userId,String password){
		return "return code:"+userService.insertUser();
	}
}
