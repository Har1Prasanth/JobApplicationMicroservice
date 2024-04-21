package com.irah.reviewms.reviews.controller;

import com.irah.reviewms.reviews.model.ReviewDto;
import com.irah.reviewms.reviews.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;


    //Get All jobs
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews(@RequestParam Long companyId) {

        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }


    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody ReviewDto reviewDto) {
        boolean isReviewSaved = reviewService.addReview(companyId, reviewDto);
        if (isReviewSaved)
            return ResponseEntity.ok("Review Added Successfully");
        else
            return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> findReviewById(@PathVariable("reviewId") Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody ReviewDto reviewDto) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, reviewDto);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not found to be updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(
            @PathVariable("reviewId") Long reviewId) {

        boolean isDeleted = reviewService.deleteReviewById(reviewId);
        if (isDeleted) {
            return ResponseEntity.ok("Review with ID " + reviewId + " Deleted Successfully");
        }
        return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);

    }


}
