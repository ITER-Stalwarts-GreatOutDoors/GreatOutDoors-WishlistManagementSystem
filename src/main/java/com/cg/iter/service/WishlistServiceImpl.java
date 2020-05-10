package com.cg.iter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.WishlistException;
import com.cg.iter.repository.WishlistRepository;
@Service
public class WishlistServiceImpl implements WishlistService {
	
	@Autowired
	WishlistRepository repository;

	@Override
	public boolean addToWishlist(WishlistDTO addItem) throws WishlistException{
		
		repository.save(addItem);
		return true;
	}

	@Override
	public List<WishlistDTO> getAllItems() {
		List<WishlistDTO> list = new ArrayList<>();
	//	repository.findAll().forEach(i -> list.add(i));
		return (List<WishlistDTO>) repository.findAll();
	}

}
