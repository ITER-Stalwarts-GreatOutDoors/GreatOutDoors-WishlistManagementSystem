package com.cg.iter.repository;

import org.springframework.data.repository.CrudRepository;

import com.cg.iter.dto.WishlistDTO;

public interface WishlistRepository extends CrudRepository<WishlistDTO,String> {

}
