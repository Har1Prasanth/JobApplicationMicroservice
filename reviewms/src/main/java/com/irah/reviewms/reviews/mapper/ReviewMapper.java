package com.irah.reviewms.reviews.mapper;

import com.irah.reviewms.reviews.entity.Review;
import com.irah.reviewms.reviews.model.ReviewDto;
import org.springframework.beans.BeanUtils;

public class ReviewMapper {

    public static ReviewDto mapToReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(review,reviewDto);
        return reviewDto;
    }

    public static Review mapToReview(ReviewDto reviewDto){
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto,review);
        return review;
    }

}
