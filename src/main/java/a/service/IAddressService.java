package a.service;

import java.util.List;

import a.pojo.Address;
import a.pojo.City;
import a.pojo.Country;



public interface IAddressService {
	List<Country> getCountry()throws Exception;
	List<City> getCityByCountryId(int countryId)throws Exception;
	boolean updateAddress(Address address)throws Exception;
}
