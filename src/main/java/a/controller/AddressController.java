package a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import a.pojo.Address;
import a.service.IAddressService;


@Controller
public class AddressController {
	@Autowired
	private IAddressService addressService;
	
	@RequestMapping("/getCountry")
	@ResponseBody
	public String getCountry(){
		try{
			return JSON.toJSONString(addressService.getCountry());
		}catch(Exception e){
			e.printStackTrace();
			return "exception";
		}
	}
	
	@RequestMapping("/getCity")
	@ResponseBody
	public String getCity(@RequestParam(value="countryId",required=true)Integer countryId){
		try{
			return JSON.toJSONString(addressService.getCityByCountryId(countryId));
		}catch(Exception e){
			e.printStackTrace();
			return "exception";
		}
	}
	
	@RequestMapping("/changeAddress")
	@ResponseBody
	public String changeAddress(Address address){
		try{
			if(addressService.updateAddress(address))
				return "success";
			else
				return "faild";
		}catch(Exception e){
			e.printStackTrace();
			return "exception";
		}
	}
}
