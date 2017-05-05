package com.topker.areas.cart.controllers;

import com.topker.areas.cart.models.viewModels.CartViewModel;
import com.topker.areas.cart.services.CartService;
import com.topker.areas.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    private final CartService cartService;

    @Autowired
    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }


    @PostMapping("/add/{productId}")
    public ResponseEntity<CartViewModel> addProductToCart(@PathVariable long productId, HttpSession session, Principal principal){
        String sessionId = session.getId();
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }

        CartViewModel cartViewModel = this.cartService.addProductToCart(productId, username, sessionId);
        if(cartViewModel == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<CartViewModel> responseEntity = new ResponseEntity(cartViewModel, HttpStatus.OK);

        return responseEntity;
    }
}
