package com.companySkyTec.inventory.inventory.controllers;

import com.companySkyTec.inventory.inventory.model.Category;
import com.companySkyTec.inventory.inventory.response.CategoryResponseRest;
import com.companySkyTec.inventory.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService service;

    //Get all the categories
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories(){

        ResponseEntity<CategoryResponseRest> response = service.search();

        return response;
    }

    //Get categories by id
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response = service.searchById(id);

        return response;
    }

    //POST para guardar una categoria
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){

        ResponseEntity<CategoryResponseRest> response = service.save(category);

        return response;
    }

    /**
     * Update Categorias
     * @param category
     * @param id
     * @return
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response = service.update(category, id);

        return response;
    }

    /**
     * deleteById
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> deleteById(@PathVariable Long id){

        ResponseEntity<CategoryResponseRest> response = service.deleteById(id);

        return response;
    }




}
