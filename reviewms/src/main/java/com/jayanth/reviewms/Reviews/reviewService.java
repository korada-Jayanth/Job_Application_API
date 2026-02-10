package com.jayanth.reviewms.Reviews;

import java.util.List;


public interface reviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId,Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId, Review updatedReview);
    boolean deleteReview(Long reviewId);
}
