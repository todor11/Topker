package com.topker.areas.cart.services;

import com.topker.areas.cart.models.viewModels.CartProductViewModel;
import com.topker.areas.cart.models.viewModels.CartViewModel;

import java.util.List;

public interface CartService {

    CartViewModel addProductToCart(Long productId, String userName, String sessionId);

    List<CartProductViewModel> getCartForSessionByUsername(String username, String sessionId);
}
