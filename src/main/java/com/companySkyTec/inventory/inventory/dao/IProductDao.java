package com.companySkyTec.inventory.inventory.dao;

import com.companySkyTec.inventory.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductDao extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM product p where p.name like %?1%")
//    List<Product> findByNameLike(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

}
