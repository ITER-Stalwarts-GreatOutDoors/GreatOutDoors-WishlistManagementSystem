package com.cg.iter.service;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Service;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.CrudException;
import com.cg.iter.exception.WishlistException;
import com.cg.iter.repository.WishlistRepository;
@Service
public class WishlistServiceImpl implements WishlistService {
	
	private Logger logger = Logger.getLogger(WishlistServiceImpl.class);
	
	private String dataAccessException = "distributed transaction exception!";
	private String transientDataAccessException = "database timeout! exception!";
	@Autowired
	WishlistRepository repository;

	@Override
	public boolean addToWishlist(WishlistDTO addItem) throws WishlistException{
		try {
		repository.save(addItem);
		
         } catch (RecoverableDataAccessException  e) {
			
		
			throw new CrudException(dataAccessException);
		
		} catch (TransientDataAccessException e) {
			
			throw new CrudException(transientDataAccessException);
		}
		return true;
	}

	@Override
	public List<WishlistDTO> viewAllItems() {
		List<WishlistDTO> list = new ArrayList<>();
	//	repository.findAll().forEach(i -> list.add(i));
		return (List<WishlistDTO>) repository.findAll();
	}
	
	@Override
	public boolean deleteProduct(WishlistDTO removeItem) throws WishlistException{
		try {
		repository.delete(removeItem);
} catch (RecoverableDataAccessException  e) {
			
			throw new CrudException(dataAccessException);
			
		} catch (TransientDataAccessException e) {
			
			throw new CrudException(transientDataAccessException);
			
		}
		
		return true;
	}

}
