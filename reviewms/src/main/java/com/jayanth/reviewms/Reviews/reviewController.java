package com.jayanth.reviewms.Reviews;

import com.jayanth.reviewms.Reviews.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class reviewController {
    private final reviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;

    public reviewController(reviewService reviewService, ReviewMessageProducer reviewMessageProducer){
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved) {
            reviewMessageProducer.SendMessage(review);
            return new ResponseEntity<>("new review added successfully", HttpStatus.CREATED);

        }
        else{
            return new ResponseEntity<>("Review not added",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewsId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewsId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(reviewId,review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewsId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId ){
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not found ",HttpStatus.NOT_FOUND);
        }
    }

}
