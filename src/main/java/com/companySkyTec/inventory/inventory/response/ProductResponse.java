package com.companySkyTec.inventory.inventory.response;

import com.companySkyTec.inventory.inventory.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    List<Product> products;

}
