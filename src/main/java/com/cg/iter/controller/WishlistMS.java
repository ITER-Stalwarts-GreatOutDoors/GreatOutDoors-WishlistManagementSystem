package com.cg.iter.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.iter.dto.ProductDTO;
import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.NullParameterException;

import io.swagger.annotations.ApiOperation;
import com.cg.iter.exception.CrudException;

import com.cg.iter.service.WishlistService;
//this is a restcontroller
@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*")
public class WishlistMS {
	
	private static final Logger logger = Logger.getLogger(WishlistMS.class);
	
	
	@Autowired
	WishlistService service;
	
	
	@PostMapping("/addToWishlist")
	public String addToWishlist(@RequestBody WishlistDTO addItem) {
		
		if(addItem==null || addItem.getUserId().trim().length()==0 ||addItem.getProductId().trim().length()==0) { 
			logger.error("Null request, Wishlist details not provided at /addToWishlist");
			throw new NullParameterException("Null request, please provide Wishlist details!");
		}
		    String status= "Added to wishlist";
		    service.addToWishlist(addItem);
		    return status;
		
               
		}

	@PostMapping("/deleteProduct")
   public String deleteProduct(@RequestBody WishlistDTO removeItem )  {
		
		if(removeItem==null || removeItem.getUserId().trim().length()==0 ||removeItem.getProductId().trim().length()==0) { 
			logger.error("Null request, wishlist details are not provided at /deleteProduct");
			throw new NullParameterException("Null request, please provide wishlist details to remove item from wishlist!");
			
		}
		
		String status= "Removed item";
	     service.deleteProduct(removeItem);
		   return status;
	
	}
		
	
	
	@GetMapping("/viewAll")
	public List<WishlistDTO> viewAllItems() {
		List<WishlistDTO> list = service.viewAllItems();
		return list;
	}
	

	@GetMapping("/viewAllProducts")
	
	public List<ProductDTO> viewAllProductFromWishList() {
		return service.viewAllProductFromWishList();
	}

		
	}
	

