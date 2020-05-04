package com.cg.iter.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wishlist")
public class WishlistDTO {
	
	@Id
	@Column(name = "user_id" ,nullable=false)
	private String userId;
	@Column(name= "product_id" ,nullable=false)
	private String productId;
	
	
public WishlistDTO() {}

public WishlistDTO(String userId, String productId) {
	super();
	this.userId = userId;
	this.productId = productId;
}

@Override
public String toString() {
	return "WishlistDTO [userId=" + userId + ", productId=" + productId + "]";
}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	
	

}
