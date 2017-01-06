package a.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import a.pojo.Customer;
import a.service.ICustomerService;
import a.utils.MyStringUtil;


@Controller
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("/showAllCustomers")
	@ResponseBody
	public String showAllCustomers(@RequestParam(value="offset",required=false)String sOffset,@RequestParam(value="limit",required=false)String sLimit,
			@RequestParam(value="sort",required=false)String orderBy,@RequestParam(value="order",required=false)String order,@RequestParam(value="search",required=false)String search){
		List<Customer> list = new ArrayList<Customer>();
		int count = 0;
		try{
			if(orderBy != null){
				orderBy = MyStringUtil.camelToUnderLine(orderBy);
				orderBy=orderBy+" "+order;
			}
			if(sOffset == null)sOffset = "0";
			if(sLimit == null)sLimit = "10";
			int offset = Integer.parseInt(sOffset);
			int limit = Integer.parseInt(sLimit);
			list = customerService.getCustomerByOrderbyLimit(orderBy, offset, limit,search);
			count = customerService.getCustomerCount(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", count);
		map.put("rows", list);
		return JSON.toJSONString(map);
	}
}
