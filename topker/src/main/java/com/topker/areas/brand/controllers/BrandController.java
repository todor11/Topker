package com.topker.areas.brand.controllers;

import com.topker.areas.brand.models.viewModels.BrandNameViewModel;
import com.topker.areas.brand.services.BrandService;
import com.topker.areas.category.models.viewModels.CategoryNameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandNameViewModel>> getCategories(){
        List<BrandNameViewModel> brands =
                this.brandService.getAllBrands();

        if(brands == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<BrandNameViewModel>> responseEntity
                = new ResponseEntity(brands, HttpStatus.OK);

        return responseEntity;
    }
}
