package a.service;

import java.util.List;
import java.util.Map;

import a.pojo.Film;
import a.pojo.FilmResult;


public interface IFilmService {
	List<FilmResult> getFilmByOrderbyLimit(String orderByClause,int offset,int limit,String search)throws Exception;
	int getFilmCount(String search)throws Exception;
	boolean deleteFilmById(List<Integer> idList)throws Exception;
	Map<String,String> getAllLanguage()throws Exception;
	boolean updateOrInsertFilm(Film film)throws Exception;
}
