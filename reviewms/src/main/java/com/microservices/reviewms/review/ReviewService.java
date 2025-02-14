package com.microservices.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReviewForCompany(Long companyId, Review review);
    Review getReviewByReviewId(Long reviewId);
    boolean updateReviewByReviewId(Long reviewId, Review review);
    boolean deleteReviewByReviewId(Long reviewId);
}
