package a.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class JumpController {

	@RequestMapping("/isLogin")
	@ResponseBody
	public String isLogin(HttpServletRequest request){
		Object user = request.getSession().getAttribute("user");
		Map<String,String> map = new HashMap<String,String>();
		if(user == null){
			map.put("result", "false");
			map.put("userName", "");
		}else {
			map.put("result", "true");
			map.put("userName", (String)user);
		}
		return JSON.toJSONString(map);
	}
}
