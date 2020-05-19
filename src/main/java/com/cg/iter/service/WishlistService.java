package com.cg.iter.service;

import java.util.List;

import com.cg.iter.dto.ProductDTO;
import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.WishlistException;

public interface WishlistService {
	
	boolean addToWishlist(WishlistDTO addItem) throws WishlistException;

	List<WishlistDTO> viewAllItems();

	boolean deleteProduct(WishlistDTO removeItem)throws WishlistException;

	List<ProductDTO> viewAllProductFromWishList();



	



}
