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

import com.cg.iter.service.WishlistService;
//this is a restcontroller
@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*")
public class WishlistMS {
	
	private static final Logger logger = Logger.getLogger(WishlistMS.class);
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	WishlistService service;
	
	
	@ApiOperation(
			value = "Add products to wishlist",
			notes = " can add product to wishlist with this API",
			response = String.class
			)

	@PostMapping("/addToWishlist")
	public String addToWishlist(@RequestBody WishlistDTO addItem) {
		
		if(addItem==null || addItem.getUserId()==null ||addItem.getProductId()==null) { 
			logger.error("Null request, Wishlist details not provided at /addItemToCart");
			throw new NullParameterException("Null request, please provide cart details!");
		}
		    String status= "Added to wishlist";
		    service.addToWishlist(addItem);
		    return status;
		
               
		}
	@ApiOperation(
			value = "Remove product from wishlist",
			notes = "can remove product from wishlist with this API",
			response = String.class
			)
	
	@PostMapping("/deleteProduct")
	String deleteProduct(@RequestBody WishlistDTO removeItem )  {
		
		if(removeItem==null || removeItem.getUserId()==null ||removeItem.getProductId()==null  ) { 
			logger.error("Null request, cart details are not provided at /removeFromCart");
			throw new NullParameterException("Null request, please provide cart details to remove iteam from cart!");
			
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
	
	@ApiOperation(
			value = "View all products",
			notes = " can view all products in the cart with this API",
			response = List.class
			)
	
	@GetMapping("/viewAllProducts")
	
	public List<ProductDTO> viewAllProductFromWishList() {
		return service.viewAllProductFromWishList();
	}

		
	}
	

