package a.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import a.pojo.Film;
import a.pojo.FilmResult;
import a.service.IFilmService;
import a.utils.MyStringUtil;



@Controller
public class FilmController {

	@Autowired
	private IFilmService filmService;
	
	@RequestMapping("/deleteFilm")
	@ResponseBody
	public String deleteFilm(@RequestParam(value="ids",required=false)String sIds){
		try {
			if(filmService.deleteFilmById(MyStringUtil.sIdsToList(sIds))){
				return "success";
			}else{
				return "faild";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("/getLanguage")
	@ResponseBody
	public String getLanguage(){
		try{
			Map<String,String> map = filmService.getAllLanguage();
			return JSON.toJSONString(map);
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("/showFilms")
	@ResponseBody
	public String showFilms(@RequestParam(value="offset",required=false)String sOffset,@RequestParam(value="limit",required=false)String sLimit,
			@RequestParam(value="sort",required=false)String orderBy,@RequestParam(value="order",required=false)String order,@RequestParam(value="search",required=false)String search){
		List<FilmResult> list = new ArrayList<FilmResult>();
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
			list = filmService.getFilmByOrderbyLimit(orderBy, offset, limit,search);
			count = filmService.getFilmCount(search);
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", count);
		map.put("rows", list);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/updateFilm")
	@ResponseBody
	public String updateFilm(@RequestParam(value="filmId",required=true)String filmId,@RequestParam(value="title",required=true)String title,
			@RequestParam(value="description",required=false)String description,@RequestParam(value="languageId",required=true)String sLanguageId){
		try{
			Film film = new Film();
			film.setFilmId(Short.valueOf(filmId));
			film.setTitle(title);
			film.setDescription(description);
			film.setLanguageId(Byte.parseByte(sLanguageId));
			if(filmService.updateOrInsertFilm(film)){
				return "success";
			}else{
				return "faild";
			}
		}catch(Exception e){
			return "出现异常";
		}
	}
}
