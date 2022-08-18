package com.companySkyTec.inventory.inventory.dao;

import com.companySkyTec.inventory.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product, Long> {

}
