package com.companySkyTec.inventory.inventory.dao;


import com.companySkyTec.inventory.inventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryDao extends JpaRepository<Category, Long> {



}
