package com.topker.areas.review.services;

import com.topker.areas.review.models.bindingModels.ProductReviewCreationModel;
import com.topker.areas.review.models.bindingModels.UserReviewCreationModel;

public interface ReviewService {

    void create(ProductReviewCreationModel productReviewCreationModel);

    void create(UserReviewCreationModel userReviewCreationModel);
}
