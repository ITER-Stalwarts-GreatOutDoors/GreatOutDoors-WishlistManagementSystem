package com.cg.iter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.iter.dto.WishlistDTO;
import com.cg.iter.exception.WishlistException;
import com.cg.iter.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistMS {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	WishlistService service;
	
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
}
