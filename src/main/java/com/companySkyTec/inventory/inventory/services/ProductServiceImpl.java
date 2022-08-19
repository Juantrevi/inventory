package com.companySkyTec.inventory.inventory.services;

import com.companySkyTec.inventory.inventory.dao.ICategoryDao;
import com.companySkyTec.inventory.inventory.dao.IProductDao;
import com.companySkyTec.inventory.inventory.model.Category;
import com.companySkyTec.inventory.inventory.model.Product;
import com.companySkyTec.inventory.inventory.response.ProductResponseRest;
import com.companySkyTec.inventory.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> findById(Long id) {

        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();

        try {
            //Search product by id
            Optional<Product> product = productDao.findById(id);

            if (product.isPresent()){

                byte [] imageDescompresed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDescompresed);
                list.add(product.get());
                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al guardar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //Search product by name
            listAux = productDao.findByNameContainingIgnoreCase(name);


            if (listAux.size() > 0){

                //Seria como un for pero con programacion reactiva
                listAux.stream().forEach((p) -> {

                    byte [] imageDescompresed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imageDescompresed);
                    list.add(p);

                });

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Producto encontrados");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrados");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto por nombre");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductResponseRest> delete(Long id) {
        ProductResponseRest response = new ProductResponseRest();

        try {
            //Search product by name
            Optional<Product> product = productDao.findById(id);


            if (product.isPresent()){

                productDao.deleteById(product.get().getId());


                response.setMetadata("Respuesta ok", "00", "Producto eliminado");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar producto por nombre");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> getAll() {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> list = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            //Search all products
            listAux = productDao.findAll();


            if (listAux.size() > 0){

                //Seria como un for pero con programacion reactiva
                listAux.stream().forEach((p) -> {

                    byte [] imageDescompresed = Util.decompressZLib(p.getPicture());
                    p.setPicture(imageDescompresed);
                    list.add(p);

                });

                response.getProduct().setProducts(list);
                response.setMetadata("Respuesta ok", "00", "Productos encontrados");

            }else {
                response.setMetadata("Respuesta nok", "-1", "Productos no encontrados");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Respuesta nok", "-1", "Error al buscar productos");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }
}
