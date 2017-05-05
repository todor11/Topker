package com.topker.areas.product.controllers;

import com.topker.areas.brand.models.viewModels.BrandNameViewModel;
import com.topker.areas.brand.services.BrandService;
import com.topker.areas.category.models.viewModels.CategoryNameViewModel;
import com.topker.areas.category.services.CategoryService;
import com.topker.areas.product.models.bindingModels.SingleProductCreationModel;
import com.topker.areas.product.models.viewModels.ProductUpdateModel;
import com.topker.areas.product.models.viewModels.ProductAdminMainViewModel;
import com.topker.areas.product.models.viewModels.ProductShortViewModel;
import com.topker.areas.product.services.ProductService;
import com.topker.models.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    private final BrandService brandService;

    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, BrandService brandService, CategoryService categoryService) {
        this.productService = productService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @ModelAttribute(name = "brandNames")
    public List<BrandNameViewModel> getBrandNames(){
        List<BrandNameViewModel> brands = this.brandService.getAllBrands();

        return brands;
    }

    @ModelAttribute(name = "categories")
    public List<CategoryNameViewModel> getCategories(){
        List<CategoryNameViewModel> categories = this.categoryService.getAllCategories();

        return categories;
    }

    @PostMapping("/products/filter")
    public ResponseEntity<List<ProductShortViewModel>> getFilteredProducts(@RequestBody Filter filter){
        List<ProductShortViewModel> products = this.productService.getFilteredProducts(filter);

        if(products == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<ProductShortViewModel>> responseEntity = new ResponseEntity(products, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/admin/products")
    public String getAdminMainPageProducts(Model model){
        List<ProductAdminMainViewModel> products = this.productService.getAdminMainPageProducts();
        model.addAttribute("products", products);
        model.addAttribute("view", "home/admin-home");

        return "base-layout";
    }

    @GetMapping("/admin/products/create")
    public String getCreateProduct(Model model){
        if (!model.containsAttribute("singleProductCreationModel")) {
            model.addAttribute("singleProductCreationModel", new SingleProductCreationModel());
        }
        model.addAttribute("view", "products/create-product");

        return "base-layout";
    }

    @PostMapping("/admin/products/create")
    public String createProduct(@Valid SingleProductCreationModel singleProductCreationModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        boolean isNameOccupied = this.productService.isNameOccupied(singleProductCreationModel.getName());
        if (isNameOccupied) {
            FieldError fieldError = bindingResult.getFieldError("name");
            if (fieldError == null) {
                fieldError = new FieldError("name", "name", "This name is occupied");
                bindingResult.addError(fieldError);
            }
        }
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("singleProductCreationModel", singleProductCreationModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.singleProductCreationModel", bindingResult);
            return "redirect:/admin/products/create";
        }

        this.productService.create(singleProductCreationModel);
        return "redirect:/";
    }

    @DeleteMapping("/admin/products/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable long productId){
        this.productService.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/admin/products/edit/{productId}")
    public String getEditProduct(@PathVariable long productId, Model model){

        if (!model.containsAttribute("productUpdateModel")) {
            ProductUpdateModel productUpdateModel = this.productService.getEditProduct(productId);
            model.addAttribute("productUpdateModel", productUpdateModel);
        }
        model.addAttribute("view", "products/edit-product");

        return "base-layout";
    }

    @PostMapping("/admin/products/edit/{productId}")
    public String updateProduct(@PathVariable long productId, @Valid  ProductUpdateModel productUpdateModel, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){

        boolean isNewNameValid = this.productService.isUpdatedNameValid(productId, productUpdateModel.getName());
        productUpdateModel.setId(productId);
        if (!isNewNameValid) {
            FieldError fieldError = bindingResult.getFieldError("name");
            if (fieldError == null) {
                fieldError = new FieldError("name", "name", "This name is occupied");
                bindingResult.addError(fieldError);
            }
        }
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productUpdateModel", productUpdateModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productUpdateModel", bindingResult);
            return "redirect:/admin/products/edit/" + productUpdateModel.getId();
        }

        this.productService.updateProduct(productUpdateModel);
        return "redirect:/";
    }



}
