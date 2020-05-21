package com.cg.iter.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.iter.dto.ProductDTO;
import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.CrudException;

import com.cg.iter.exception.ProductNotFoundException;
import com.cg.iter.repository.WishlistRepository;
@Service
public class WishlistServiceImpl implements WishlistService {
	

	
	@Autowired
	RestTemplate rest;
	
	private String productURL = "http://product-ms/product";
	
	private String dataAccessException = "distributed transaction exception!";
	private String transientDataAccessException = "database timeout! exception!";
	@Autowired
	WishlistRepository repository;

	/*
	 * name - add to wishlist
	 * description - It will add an item to the wishlist.
	 */
	@Override
	public boolean addToWishlist(WishlistDTO addItem) {
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
	/*
	 * name - delete item from the wishlist
	 * description - it will delete available item from the wishlist
	 */
	
	@Override
	public boolean deleteProduct(WishlistDTO removeItem) {
		try {
		repository.delete(removeItem);
} catch (RecoverableDataAccessException  e) {
			
			throw new CrudException(dataAccessException);
			
		} catch (TransientDataAccessException e) {
			
			throw new CrudException(transientDataAccessException);
			
		}
	
		return true;
	}
	 /*
     * showProductsFromWishlist
     * descriptio:shows all products in the wishlist
     */
	@Override
	
	public List<ProductDTO> viewAllProductFromWishList() {
		List<WishlistDTO> listWishListItems = (List<WishlistDTO>) repository.findAll();
		List<ProductDTO> listProducts = new ArrayList<>();
		
		Iterator<WishlistDTO> itr = listWishListItems.iterator();
		int index = 0;
		
		while (itr.hasNext()) {
			ProductDTO product = rest.getForObject(productURL+"/getProductById?productId="+listWishListItems.get(index).getProductId(),
					ProductDTO.class);
			listProducts.add(product);
			index++;
			itr.next();
		}
		return listProducts;
	}




}
