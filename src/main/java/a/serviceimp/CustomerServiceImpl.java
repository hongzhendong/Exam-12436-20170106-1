package a.serviceimp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.dao.AddressMapper;
import a.dao.CityMapper;
import a.dao.CountryMapper;
import a.dao.CustomerMapper;
import a.pojo.Address;
import a.pojo.City;
import a.pojo.Country;
import a.pojo.Customer;
import a.service.ICustomerService;


@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
/*	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private CityMapper cityMapper;
	
	@Autowired
	private CountryMapper countryMapper;*/
	
	public boolean hasCustomer(String userName) throws Exception{
		try{

			if (customerMapper.selectUserName(userName,null)==null) {
				return false;
				
			} else {
				return true;

			}
			
			
		}catch(Exception e){
			throw e;
		}
		
	}

	public boolean login(String userName, String password) throws Exception {
		try{

			if (customerMapper.selectUserName(userName,password)==null) {
				return false;
				
			} else {
				return true;

			}
			
			
		}catch(Exception e){
			throw e;
		}
	}

	public Map<String,Object> getCustomerInfo(String userName) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Customer cus = null;
		Address addr = null;
		City city = null;
		Country country = null;
		try{

			return map;
		}catch(Exception e){
			throw e;
		}
	}

	public boolean updateCustomerBasic(String firstNameOld,String firstName, String lastName, String email)throws Exception {
		try{
			return false;
		}catch(Exception e){
			throw e;
		}
	}

	public List<Customer> getCustomerByOrderbyLimit(String orderByClause, int offset, int limit, String search)
			throws Exception {
		try{
			return customerMapper.selectByOrderByLimit(orderByClause, offset, limit,search);
			
		}catch(Exception e){
			throw e;
		}
	}

	public int getCustomerCount(String search) throws Exception {
		try{
			return customerMapper.selectByOrderByLimit(null, 0, 0, search).size();
			
		}catch(Exception e){
			throw e;
		}
	}
}
