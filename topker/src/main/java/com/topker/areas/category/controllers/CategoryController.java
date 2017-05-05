package com.topker.areas.category.controllers;

import com.topker.areas.category.models.viewModels.CategoryNameViewModel;
import com.topker.areas.category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryNameViewModel>> getCategories(){
        List<CategoryNameViewModel> categories = this.categoryService.getAllCategories();

        if(categories == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<CategoryNameViewModel>> responseEntity = new ResponseEntity(categories, HttpStatus.OK);

        return responseEntity;
    }
}
