package a.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a.dao.StoreMapper;
import a.pojo.Store;
import a.service.IStoreService;


@Service
public class StoreServiceImpl implements IStoreService{
	
	@Autowired
	private StoreMapper storeMapper;


	public List<Store> getAllStore() throws Exception {
		try{
			return storeMapper.selectAllStore();
		}catch(Exception e){
			throw e;
		}
	}

}
