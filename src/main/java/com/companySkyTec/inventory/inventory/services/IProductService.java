package com.companySkyTec.inventory.inventory.services;

import com.companySkyTec.inventory.inventory.model.Product;
import com.companySkyTec.inventory.inventory.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

    public ResponseEntity<ProductResponseRest> save(Product product, Long CategoryId);

    public ResponseEntity<ProductResponseRest> findById(Long id);
}
