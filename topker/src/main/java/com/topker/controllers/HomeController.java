package com.topker.controllers;

import com.topker.areas.cart.models.viewModels.CartProductViewModel;
import com.topker.areas.cart.models.viewModels.CartViewModel;
import com.topker.areas.cart.services.CartService;
import com.topker.areas.product.models.viewModels.ProductAdminMainViewModel;
import com.topker.areas.product.models.viewModels.ProductShortViewModel;
import com.topker.areas.product.services.ProductService;
import com.topker.areas.role.entities.Role;
import com.topker.areas.user.entities.User;
import com.topker.areas.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    private final ProductService productService;

    private final CartService cartService;

    @Autowired
    public HomeController(UserRepository userRepository, ProductService productService, CartService cartService) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.cartService = cartService;
    }


    @GetMapping("/")
    public String getHomePage(Principal principal, Model model, HttpSession httpSession){
        if (principal != null) {
            String sessionId = httpSession.getId();
            List<CartProductViewModel> cartProductViewModels = this.cartService.getCartForSessionByUsername(principal.getName(), sessionId);
            httpSession.setAttribute("cartProd", cartProductViewModels);
        }


        httpSession.setAttribute("cart", new CartViewModel());
        boolean isAdmin = false;
        if (principal != null) {
            String name = principal.getName();
            User user = this.userRepository.findOneByUsername(name);
            Set<Role> userAuthorities = user.getAuthorities();

            for (Role userAuthority : userAuthorities) {
                if (userAuthority.getAuthority().equals("ROLE_ADMIN")) {
                    List<ProductAdminMainViewModel> products = this.productService.getAdminMainPageProducts();
                    model.addAttribute("products", products);
                    model.addAttribute("view", "home/admin-home");
                    isAdmin = true;
                    break;
                }
            }
        }

        if (!isAdmin) {
            List<ProductShortViewModel> products = this.productService.getProductsOrderByPromotion();
            model.addAttribute("products", products);
            model.addAttribute("view", "home/main-home");
        }

        return "base-layout";
    }

}
