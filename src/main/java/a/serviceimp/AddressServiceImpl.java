package a.serviceimp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.dao.AddressMapper;
import a.dao.CityMapper;
import a.dao.CountryMapper;
import a.pojo.Address;
import a.pojo.City;
import a.pojo.Country;
import a.service.IAddressService;



@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private AddressMapper addressMapper;
	

	public List<Country> getCountry() throws Exception {
		try{
			
			return null;
		}catch(Exception e){
			throw e;
		}
	}

	public List<City> getCityByCountryId(int countryId) throws Exception {
		try{
			
			return cityMapper.selectBy(countryId);
		}catch(Exception e){
			throw e;
		}
		
	}


	public boolean updateAddress(Address address) throws Exception {
		try{
			address.setLastUpdate(new Date());
			int count = addressMapper.updateByPrimaryKeySelective(address);
			if(count > 0)return true;
			else return false;
		}catch(Exception e){
			throw e;
		}
	}

}
