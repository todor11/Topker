package com.topker.areas.review.services;

import com.topker.areas.review.entities.Review;
import com.topker.areas.review.models.bindingModels.ProductReviewCreationModel;
import com.topker.areas.review.models.bindingModels.UserReviewCreationModel;
import com.topker.areas.review.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ModelMapper modelMapper;

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ModelMapper modelMapper, ReviewRepository reviewRepository) {
        this.modelMapper = modelMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void create(ProductReviewCreationModel productReviewCreationModel) {
        Review review = this.modelMapper.map(productReviewCreationModel, Review.class);
        //TODO

        this.reviewRepository.save(review);
    }

    @Override
    public void create(UserReviewCreationModel userReviewCreationModel) {
        Review review = this.modelMapper.map(userReviewCreationModel, Review.class);
        //TODO

        this.reviewRepository.save(review);
    }
}
