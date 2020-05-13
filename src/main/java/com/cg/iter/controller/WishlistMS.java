package com.cg.iter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.WishlistException;
import com.cg.iter.service.WishlistService;
//this is a restcontroller
@RestController
@RequestMapping("/wishlist")
public class WishlistMS {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	WishlistService service;
	
	@SuppressWarnings("finally")
	@PostMapping("/addToWishlist")
	public String addToWishlist(@RequestBody WishlistDTO addItem) {
		String status= "Added to wishlist";
		try {
			service.addToWishlist(addItem);
		}
		catch(WishlistException e) {
			e.printStackTrace();
			status=e.getMessage();
		}
		finally {
				return status;
		
                }
		}
	
	@PostMapping("/deleteProduct")
	String deleteProduct(@RequestParam String productId) {
		if(service.deleteProduct(productId)) {
			return "product deleted!";
		}
		return "error";
	}
	
	
	@GetMapping("/viewAll")
	public List<WishlistDTO> viewAllItems() throws WishlistException {
	
		List<WishlistDTO> list = service.viewAllItems();
		return list;
	}

		
	}
	

