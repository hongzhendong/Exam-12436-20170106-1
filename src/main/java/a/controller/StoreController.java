package a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import a.service.IStoreService;



@Controller
public class StoreController {
	
/*	@Autowired
	private IStoreService storeService;

	@RequestMapping("/getStore")
	@ResponseBody
	public String getStore(){
		try{
			return JSON.toJSONString(storeService.getAllStore());
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}*/
}
