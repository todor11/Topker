package com.topker.areas.cart.services;

import com.topker.areas.cart.entities.Cart;
import com.topker.areas.cart.models.viewModels.CartProductViewModel;
import com.topker.areas.cart.models.viewModels.CartViewModel;
import com.topker.areas.cart.repositories.CartRepository;
import com.topker.areas.product.entities.BaseProduct;
import com.topker.areas.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final HttpSession httpSession;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(HttpSession httpSession, CartRepository cartRepository, ProductRepository productRepository) {
        this.httpSession = httpSession;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartViewModel addProductToCart(Long productId, String userName, String sessionId) {
        Cart sessionCart = this.cartRepository.findOneBySessionId(sessionId);
        BaseProduct product = this.productRepository.findOne(productId);
        if (product == null) {
            return null;
        }

        Cart userCart = null;
        if (userName != null) {
            userCart = this.cartRepository.findOneByOwner(userName);
        }

        if (sessionCart == null && userName == null) {
            Cart cart = new Cart();
            cart.setSessionId(sessionId);
            cart.addProduct(product);
            this.cartRepository.save(cart);
            return this.transformCart(cart);
        } else if (sessionCart != null && userName == null) {
            sessionCart.addProduct(product);
            return this.transformCart(sessionCart);
        } else if(sessionCart == null && userCart != null) {
            userCart.addProduct(product);
            return this.transformCart(userCart);
        } else if (sessionCart != null && userCart != null) {
            for (int i = 0; i < sessionCart.getProducts().size(); i++) {
                 userCart.addProduct(sessionCart.getProducts().get(i));
            }

            userCart.setSessionId(null);
            return this.transformCart(userCart);
        }

        return null;
    }

    @Override
    public List<CartProductViewModel> getCartForSessionByUsername(String username, String sessionId) {
        Cart cart = this.cartRepository.findOneByOwner(username);
        Cart sessionCart = this.cartRepository.findOneBySessionId(sessionId);
        if (sessionCart != null) {
            for (BaseProduct product : sessionCart.getProducts()) {
                cart.addProduct(product);
            }
        }

        List<CartProductViewModel> cartProductsViewModels = new ArrayList<>();
        if (cart != null) {
            int productsSize = cart.getProducts().size();
            for (int i = 0; i < productsSize; i++) {
                CartProductViewModel cartProductViewModel = new CartProductViewModel();
                cartProductViewModel.setProductName(cart.getProducts().get(i).getName());
                cartProductViewModel.setQuantity(cart.getQuantities().get(i));
                cartProductViewModel.setProductId(cart.getProducts().get(i).getId());
                cartProductsViewModels.add(cartProductViewModel);
            }
        }

        return cartProductsViewModels;
    }

    private CartViewModel transformCart(Cart cart) {
        CartViewModel cartViewModel = new CartViewModel();
        List<String> productNames = new ArrayList<>();
        List<Integer> productQuantities = new ArrayList<>();
        List<Long> productsIds = new ArrayList<>();
        List<BaseProduct> products = cart.getProducts();
        List<Integer> quantities = cart.getQuantities();

        List<CartProductViewModel> cartProductViewModels = new ArrayList<>();
        for (int i = 0; i < cart.getProducts().size(); i++) {
            productNames.add(products.get(i).getName());
            productQuantities.add(quantities.get(i));
            productsIds.add(products.get(i).getId());

            CartProductViewModel cartProductViewModel = new CartProductViewModel();
            cartProductViewModel.setProductId(products.get(i).getId());
            cartProductViewModel.setProductName(products.get(i).getName());
            cartProductViewModel.setQuantity(quantities.get(i));
            cartProductViewModels.add(cartProductViewModel);
        }

        this.httpSession.setAttribute("cartProd", cartProductViewModels);

        cartViewModel.setProductQuantities(productQuantities);
        cartViewModel.setProductNames(productNames);
        cartViewModel.setProductsIds(productsIds);

        return cartViewModel;
    }

}
