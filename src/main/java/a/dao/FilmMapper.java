package a.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import a.pojo.Film;
import a.pojo.FilmResult;


public interface FilmMapper {
    int deleteByPrimaryKey(Short filmId);

    int insert(Film record);

    int insertSelective(Film film);

    Film selectByPrimaryKey(Short filmId);

    int updateByPrimaryKeySelective(Film film);

    int updateByPrimaryKeyWithBLOBs(Film record);

    int updateByPrimaryKey(Film record);
    List<FilmResult> selectByOrderByLimit(@Param("orderByClause")String orderByClause,@Param("offset")int offset,@Param("limit")int limit,@Param("search")String search);

	int deleteByList(List<Short> list);

}