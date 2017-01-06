package a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import a.pojo.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Short customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Short customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    Customer selectUserName(@Param(value = "userName") String userName,@Param(value = "password") String password);

	List<Customer> selectByOrderByLimit(@Param("orderByClause")String orderByClause,@Param("offset")int offset,@Param("limit")int limit,@Param("search")String search);
}
