package com.companySkyTec.inventory.inventory.controllers;

import com.companySkyTec.inventory.inventory.model.Product;
import com.companySkyTec.inventory.inventory.response.ProductResponseRest;
import com.companySkyTec.inventory.inventory.services.IProductService;
import com.companySkyTec.inventory.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    @Autowired
    private IProductService productService;


    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(@RequestParam("picture") MultipartFile picture,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("price") int price,
                                                    @RequestParam("account") int account,
                                                    @RequestParam("categoryId") Long categoryId) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setAccount(account);
        product.setPicture(Util.compressZLib(picture.getBytes()));

        ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);

        return response;

    }
}
