package com.irah.reviewms.reviews.service;

import com.irah.reviewms.reviews.model.ReviewDto;

import java.util.List;

public interface ReviewService {

        List<ReviewDto> getAllReviews(Long CompanyId);

        boolean addReview(Long companyId, ReviewDto reviewDto);

        ReviewDto getReviewById( Long reviewId);

        boolean updateReview(Long reviewId, ReviewDto reviewDto);

        boolean deleteReviewById( Long reviewId);
}
