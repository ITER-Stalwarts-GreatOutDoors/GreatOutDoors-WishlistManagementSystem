package com.cg.iter.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.WishlistException;
import com.cg.iter.repository.WishlistRepository;

public class WishlistServiceImpl implements WishlistService {
	
	@Autowired
	WishlistRepository repository;

	@Override
	public boolean addToWishlist(WishlistDTO addItem) throws WishlistException{
		// TODO Auto-generated method stub
		repository.save(addItem);
		return true;
	}

}
