package com.cg.iter.service;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.WishlistException;

public interface WishlistService {
	
	boolean addToWishlist(WishlistDTO addItem) throws WishlistException;

}
