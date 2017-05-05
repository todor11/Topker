package com.topker.areas.product.services;

import com.topker.areas.product.models.bindingModels.MultiProductCreationModel;
import com.topker.areas.product.models.bindingModels.SingleProductCreationModel;
import com.topker.areas.product.models.bindingModels.SubProductCreationModel;
import com.topker.areas.product.models.viewModels.ProductUpdateModel;
import com.topker.areas.product.models.viewModels.ProductAdminMainViewModel;
import com.topker.areas.product.models.viewModels.ProductShortViewModel;
import com.topker.models.Filter;

import java.util.List;

public interface ProductService {

    void create(SingleProductCreationModel singleProductCreationModel);

    void create(MultiProductCreationModel multiProductCreationModel);

    void create(SubProductCreationModel subProductCreationModel);

    void updateProduct(ProductUpdateModel productUpdateModel);

    void deleteProduct(Long productId);

    List<ProductShortViewModel> getFilteredProducts(Filter filter);

    List<ProductShortViewModel> getProductsOrderByPromotion();

    List<ProductAdminMainViewModel> getAdminMainPageProducts();

    ProductUpdateModel getEditProduct(long productId);

    boolean isNameOccupied(String name);

    boolean isUpdatedNameValid(Long productId, String name);
}
