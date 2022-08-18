package com.companySkyTec.inventory.inventory.services;

import com.companySkyTec.inventory.inventory.dao.ICategoryDao;
import com.companySkyTec.inventory.inventory.dao.IProductDao;
import com.companySkyTec.inventory.inventory.model.Category;
import com.companySkyTec.inventory.inventory.model.Product;
import com.companySkyTec.inventory.inventory.response.ProductResponse;
import com.companySkyTec.inventory.inventory.response.ProductResponseRest;
import com.companySkyTec.inventory.inventory.response.ResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IProductDao productDao;

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //Search category to set in the product object
            Optional<Category> category = categoryDao.findById(categoryId);

            if (category.isPresent()){
                product.setCategory(category.get());
            } else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            //Save the product
            Product productSaved = productDao.save(product);

            if (productSaved != null){
                list.add(productSaved);
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto guardado");
            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }


}
