package com.cg.iter.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.NullParameterException;
import com.cg.iter.exception.WishlistException;
import com.cg.iter.service.WishlistService;
//this is a restcontroller
@RestController
@RequestMapping("/wishlist")
public class WishlistMS {
	
	private static final Logger logger = Logger.getLogger(WishlistMS.class);
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	WishlistService service;
	
	@SuppressWarnings("finally")
	@PostMapping("/addToWishlist")
	public String addToWishlist(@RequestBody WishlistDTO addItem) throws WishlistException {
		
		if(addItem==null || addItem.getUserId()==null ||addItem.getProductId()==null) { 
			logger.error("Null request, Wishlist details not provided at /addItemToCart");
			throw new NullParameterException("Null request, please provide cart details!");
		}
		    String status= "Added to wishlist";
		    service.addToWishlist(addItem);
		    return status;
		
               
		}
	
	@PostMapping("/deleteProduct")
	String deleteProduct(@RequestBody WishlistDTO removeItem ) throws WishlistException {
		
		if(removeItem==null || removeItem.getUserId()==null ||removeItem.getProductId()==null  ) { 
			logger.error("Null request, cart details are not provided at /removeFromCart");
			throw new NullParameterException("Null request, please provide cart details to remove iteam from cart!");
		}
		
		String status= "Removed item";
	     service.deleteProduct(removeItem);
		   return status;
	
	}
		
	
	
	@GetMapping("/viewAll")
	public List<WishlistDTO> viewAllItems() throws WishlistException{
		List<WishlistDTO> list = service.viewAllItems();
		return list;
	}

		
	}
	

