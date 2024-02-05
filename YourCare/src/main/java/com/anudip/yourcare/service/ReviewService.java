package com.anudip.yourcare.service;

import java.util.List;


import com.anudip.yourcare.dto.ReviewDto;
import com.anudip.yourcare.entity.Review;
import com.anudip.yourcare.exception.ResourceNotFoundException;


public interface ReviewService {


	//Method for creating the Review
	public Review createReview(Review review);
	
	//Methods for getting Review
	public List<ReviewDto> getAllReview() throws ResourceNotFoundException;
	
	public ReviewDto getReviewById(int id) throws ResourceNotFoundException;
	
	public ReviewDto getReviewByName(String fname) throws ResourceNotFoundException;
	
	//Method for Updating Review
	public Review updateReviewByName(Review review) throws ResourceNotFoundException;
	
	
	//Method for Deleting the user
	public String deleteReviewByName(String fname) throws ResourceNotFoundException;
	
	
	//This will return the list of all reviews created by any person 
	List<Review> getListReviewByFname(String fname) throws ResourceNotFoundException;
	
	
	List<Review> getListReviewByDoctorsFname(String fname) throws ResourceNotFoundException;
}
