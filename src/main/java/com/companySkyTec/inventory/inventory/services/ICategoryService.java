package com.companySkyTec.inventory.inventory.services;

import com.companySkyTec.inventory.inventory.model.Category;
import com.companySkyTec.inventory.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);





}
