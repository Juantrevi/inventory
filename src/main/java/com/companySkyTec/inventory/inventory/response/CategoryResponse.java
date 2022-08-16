package com.companySkyTec.inventory.inventory.response;

import com.companySkyTec.inventory.inventory.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> category;
}
