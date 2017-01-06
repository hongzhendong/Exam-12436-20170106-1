package a.service;

import java.util.List;
import java.util.Map;

import a.pojo.Customer;



public interface ICustomerService {
	boolean hasCustomer(String userName)throws Exception;
	boolean login(String userName,String password)throws Exception;
	Map<String,Object> getCustomerInfo(String userName)throws Exception;
	boolean updateCustomerBasic(String firstNameOld,String firstName,String lastName,String email)throws Exception;
	List<Customer> getCustomerByOrderbyLimit(String orderByClause,int offset,int limit,String search)throws Exception;
	int getCustomerCount(String search)throws Exception;
}
