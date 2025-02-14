package com.microservices.reviewms.review.impl;

import com.microservices.reviewms.review.Review;
import com.microservices.reviewms.review.ReviewRepository;
import com.microservices.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReviewForCompany(Long companyId, Review review) {
        if (companyId != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewByReviewId(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);

    }

    @Override
    public boolean updateReviewByReviewId(Long reviewId, Review updatedReview) {
//        Review review = this.getReviewByReviewId(companyId,reviewId);
//        if(review!=null){
//            review.setDescription(updatedReview.getDescription());
//            review.setRating(updatedReview.getRating());
//            review.setTitle(updatedReview.getTitle());
//            reviewRepository.save(review);
//            return true;
//        }
//        return false;
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviewByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
