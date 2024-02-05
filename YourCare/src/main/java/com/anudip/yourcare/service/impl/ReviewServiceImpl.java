package com.anudip.yourcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anudip.yourcare.dto.ReviewDto;
import com.anudip.yourcare.entity.Review;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.repository.DoctorRepository;
import com.anudip.yourcare.repository.ReviewRepository;
import com.anudip.yourcare.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository reviweRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
    DoctorRepository doctorRepository;
	
	@Override
	public Review createReview(Review review) {
		
//		Doctor doctor = review.getDoctor(doctor.getId());
//		review.setDoctor(doctor);
		
		return reviweRepository.save(review);
	}

	@Override
	public List<ReviewDto> getAllReview() throws ResourceNotFoundException {
		
		List<Review> reviews = reviweRepository.findAll();
		if(reviews != null)
		{
			return reviews.stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
		}
		else
		{
			throw new ResourceNotFoundException("No Review is present");
		}
		
	}

	@Override
	public ReviewDto getReviewById(int id) throws ResourceNotFoundException {
		
		Optional<Review> existingReview = reviweRepository.findById(id);
		if(existingReview.isPresent())
		{
			ReviewDto reviewDto = modelMapper.map(existingReview, ReviewDto.class);
			return reviewDto;
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With Id : "+id);
		}
	}

	@Override
	public ReviewDto getReviewByName(String fname) throws ResourceNotFoundException {
		
		Optional<Review> existingReview = Optional.ofNullable((reviweRepository.findByFname(fname)));
		 
		ReviewDto reviewDto = modelMapper.map(existingReview, ReviewDto.class);

		if(existingReview.isPresent())
		{
			return reviewDto;
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+fname);
		}
	}

	@Override
	public Review updateReviewByName(Review review) throws ResourceNotFoundException {
		
		Review existingReview = reviweRepository.findByFname(review.getFname());
		
		if(existingReview != null)
		{
			existingReview.setLname(review.getLname());
			existingReview.setRating(review.getRating());
			existingReview.setComments(review.getComments());
			existingReview.setDoctor(review.getDoctor());
		
			return reviweRepository.save(existingReview);
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+review.getFname());
		}
		
	}

	@Override
	public String deleteReviewByName(String fname) throws ResourceNotFoundException {
		
		Review checkReview = reviweRepository.findByFname(fname);
		
		if(checkReview != null)
		{
			reviweRepository.deleteById(checkReview.getReviewid());
			
			return "The record is deleted Successfully";
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+fname);
		}
			
		
	}

	@Override
	public List<Review> getListReviewByFname(String fname) throws ResourceNotFoundException {
		
		List<Review> reviewList = reviweRepository.findReviewByFname(fname);
		if(reviewList != null)
		{
			return reviewList;
		}
		else
		{
			throw new ResourceNotFoundException("No review Found With name : "+fname);
		}
		
	}

	@Override
	public List<Review> getListReviewByDoctorsFname(String fname) throws ResourceNotFoundException {
		List<Review> reviewList = reviweRepository.findDoctorByFname(fname);
		
		if(reviewList != null)
		{
			return reviewList;
		}
		else
		{
			throw new ResourceNotFoundException("No Review Found With name : "+fname);
		}
	}

}
