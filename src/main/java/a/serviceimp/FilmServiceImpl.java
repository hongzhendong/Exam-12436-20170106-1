package a.serviceimp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.dao.FilmMapper;
import a.dao.LanguageMapper;
import a.pojo.Film;
import a.pojo.FilmResult;
import a.pojo.Language;
import a.service.IFilmService;


@Service
public class FilmServiceImpl implements IFilmService {

	@Autowired
	private FilmMapper filmMapper;
	
	@Autowired
	private LanguageMapper languageMapper;
	
	public List<FilmResult> getFilmByOrderbyLimit(String orderByClause,int offset, int limit,String search)throws Exception {
		try{
			
			return filmMapper.selectByOrderByLimit(orderByClause, offset, limit,search);
		}catch(Exception e){
			throw e;
		}
	}

	public int getFilmCount(String search)throws Exception {
		try{
			
			return filmMapper.selectByOrderByLimit(null, 0, 0, search).size();
		}catch(Exception e){
			throw e;
		}
	}


	public boolean deleteFilmById(List<Integer> idList)throws Exception {
		Film film=new Film();
		List<Short> list = new ArrayList<Short>();
		try{
			if(idList != null && idList.size()>0){
				for(int i=0;i<idList.size();i++){
					list.add(Short.parseShort(String.valueOf(idList.get(i))));
				}
				
				int count = filmMapper.deleteByList(list);
				if(count == idList.size())return true;
				else return false;
			}
			
		}catch(Exception e){
			throw e;
		}
		return true;
	}

	public Map<String, String> getAllLanguage() throws Exception {
		try{
			List<Language> list = languageMapper.selectBy(null);
			Map<String,String> map = new HashMap<String,String>();
			if(list != null && list.size() > 0){
				for(int i=0;i<list.size();i++){
					map.put(String.valueOf(list.get(i).getLanguageId()), list.get(i).getName());
					
				}
				return map;
			}else return null;
		}catch(Exception e){
			throw e;
		}
		
	}


	public boolean updateOrInsertFilm(Film film) throws Exception {
		try{
			int count = 0;
			if(film.getFilmId() == 0){
				count = filmMapper.insertSelective(film);
			}else{
				count = filmMapper.updateByPrimaryKeySelective(film);
			}
			if(count > 0)return true;
			else return false;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}



}
