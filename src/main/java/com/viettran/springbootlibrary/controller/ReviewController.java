package com.viettran.springbootlibrary.controller;

import com.viettran.springbootlibrary.requestmodels.ReviewRequest;
import com.viettran.springbootlibrary.service.ReviewService;
import com.viettran.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception {
        System.out.println("reviewBookByUser");
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if ( userEmail == null) {
            throw new Exception("Invalid token");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization") String token, @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if ( userEmail == null) {
            throw new Exception("Invalid token");
        }
        reviewService.postReview(userEmail, reviewRequest);
    }


}
