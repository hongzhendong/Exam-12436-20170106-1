package a.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.alibaba.fastjson.JSON;

import a.service.ICustomerService;


@Controller
public class UserController {
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("/checkUserName")
	@ResponseBody
	public String checkUserName(@RequestParam(value="userName",required=true)String userName){
		try{
			if(customerService.hasCustomer(userName)){
				return "true";
			}else{
				return "false";
			}
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("/getCusInfo")
	@ResponseBody
	public String getCusInfo(@SessionAttribute(value="user",required=true)String firstName){
		try{
			Map<String,Object> result = customerService.getCustomerInfo(firstName);
			if(result.get("customer") == null)
				return "null";
			else
				return JSON.toJSONString(result);
		}catch(Exception e){
			e.printStackTrace();
			return "exception";
		}
	}
	
	@RequestMapping("/changeBasicInfo")
	@ResponseBody
	public String changeBasicInfo(@SessionAttribute(value="user",required=true)String firstNameOld,@RequestParam(value="firstName",required=true)String firstName,@RequestParam(value="lastName",required=true)String lastName,@RequestParam(value="email",required=false)String email,HttpServletRequest req){
		try{
			if(!firstName.equals(firstNameOld) && customerService.hasCustomer(firstName)){
				return "firstNameExist";
			}else if(customerService.updateCustomerBasic(firstNameOld, firstName, lastName, email)){
				if(firstName.equals(firstNameOld)){
					return "success";
				}else{
					req.getSession().removeAttribute("user");
					return "success1";
				}
			}
			return "faild";
		}catch(Exception e){
			return "exception";
		}
	}
}
