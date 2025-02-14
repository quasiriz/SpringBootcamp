package com.microservices.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews != null)
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review) {
        boolean reviewSafe = reviewService.addReviewForCompany(companyId, review);
        if (reviewSafe)
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewByReviewId(
            @PathVariable Long reviewId) {
        Review review = reviewService.getReviewByReviewId(reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean reviewUpdateFlag = reviewService.updateReviewByReviewId(reviewId, review);
        if (reviewUpdateFlag)
            return new ResponseEntity<>("Update Success", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean status = reviewService.deleteReviewByReviewId(
                reviewId);
        if (status) {
            return new ResponseEntity<>("Delete Success",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }

}
