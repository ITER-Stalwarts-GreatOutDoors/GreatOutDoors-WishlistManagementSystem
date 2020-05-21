package com.cg.iter.service;

import java.util.List;

import com.cg.iter.dto.ProductDTO;
import com.cg.iter.dto.WishlistDTO;

//import com.cg.iter.exception.ProductNotFoundException;

public interface WishlistService {
	/*
	 * name - add to wishlist
	 * description - It will add an item to the wishlist.
	 */
	
	boolean addToWishlist(WishlistDTO addItem) ;
	
	

	List<WishlistDTO> viewAllItems() ;
	/*
	 * name - delete item from the wishlist
	 * description - it will delete available item from the wishlist
	 */
	boolean deleteProduct(WishlistDTO removeItem);
	
	 /*
     * showProductsFromWishlist
     * descriptio:shows all products in the wishlist
     */

	List<ProductDTO> viewAllProductFromWishList() ;



	



}
