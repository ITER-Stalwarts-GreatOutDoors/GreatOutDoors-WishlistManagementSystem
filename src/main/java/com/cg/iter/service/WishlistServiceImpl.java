package com.cg.iter.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.iter.dto.ProductDTO;
import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.CrudException;
import com.cg.iter.repository.WishlistRepository;
@Service
public class WishlistServiceImpl implements WishlistService {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	WishlistRepository repository;
	
	private String productURL = "http://product-ms/product";

	/*
	 * name - add to wishlist
	 * description - It will add an item to the wishlist.
	 */
	@Override
	public boolean addToWishlist(WishlistDTO addItem) {
		
		repository.save(addItem);	
		
		return true;
	}
	/*
	 * name - delete item from the wishlist
	 * description - it will delete available item from the wishlist
	 */
	
	@Override
	public boolean deleteProduct(WishlistDTO removeItem) {
		
		repository.delete(removeItem);

		return true;
	}
	
	@Override
	public List<WishlistDTO> viewAllItems() {
		if(repository.count()==0) throw new CrudException("Please add something to wishlist");
		List<WishlistDTO> list = new ArrayList<>();
		return (List<WishlistDTO>) repository.findAll();
	}
	 /*
     * showProductsFromWishlist
     * descriptio:shows all products in the wishlist
     */
	@Override
	
	public List<ProductDTO> viewAllProductFromWishList() {
		if(repository.count()==0) throw new CrudException("Please add items to wishlist");
		List<WishlistDTO> listWishListItems = (List<WishlistDTO>) repository.findAll();
		List<ProductDTO> listProducts = new ArrayList<>();
		
		Iterator<WishlistDTO> itr = listWishListItems.iterator();
		int index = 0;
		
		while (itr.hasNext()) {
			ProductDTO product = restTemplate.getForObject(productURL+"/getProductById?productId="+listWishListItems.get(index).getProductId(),
					ProductDTO.class);
			listProducts.add(product);
			index++;
			itr.next();
		}
		return listProducts;
	}




}
