package com.anudip.yourcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anudip.yourcare.dto.ReviewDto;
import com.anudip.yourcare.entity.Doctor;
import com.anudip.yourcare.entity.Review;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.service.impl.DoctorServiceImpl;
import com.anudip.yourcare.service.impl.ReviewServiceImpl;

import jakarta.validation.Valid;


@RestController
public class ReviewController {

			@Autowired
			ReviewServiceImpl reviewServiceImpl;
			
			@Autowired
			DoctorServiceImpl doctorServiceImpl;
	
	
			//This will get the request to create the new Review
			@PostMapping("/review/{docName}/create")
			public ResponseEntity<Review> addDoctorByDoctorName(@RequestBody @Valid Review review,@PathVariable String docName) throws ResourceNotFoundException
			{
				Doctor doc = doctorServiceImpl.getDoctorByName(docName);
				review.setDoctor(doc);
				return new ResponseEntity<>(reviewServiceImpl.createReview(review),HttpStatus.CREATED);
			}
			
			//This will get the request to print all the existing Review
			@GetMapping("/review")
			public ResponseEntity<List<ReviewDto>> getAllUser(@RequestBody Review review) throws ResourceNotFoundException
			{
				return ResponseEntity.ok(reviewServiceImpl.getAllReview());
			}
			
			
			//This will get the request to print the Review by using id
			@GetMapping("/review/byid/{id}")
			public ResponseEntity<ReviewDto> findReviewById(@PathVariable int id) throws ResourceNotFoundException
			{
				ReviewDto reviewDto = reviewServiceImpl.getReviewById(id);
				return new ResponseEntity<>(reviewDto,HttpStatus.OK);
			}
			
			//This will get the request to find the existing review 
			@GetMapping("/review/byname{fname}")
			public ResponseEntity<ReviewDto> findByName(@PathVariable String fname) throws ResourceNotFoundException
			{
				ReviewDto reviewDto = reviewServiceImpl.getReviewByName(fname);
				  return new ResponseEntity<>(reviewDto, HttpStatus.OK);
			}
			
			//This will Get the request to update the existing doctor in the DB
			@PutMapping("/review/update")
			public ResponseEntity<Review> updateDoctor(@RequestBody Review review) throws ResourceNotFoundException
			{
				return ResponseEntity.ok(reviewServiceImpl.updateReviewByName(review));
			}
			
			@DeleteMapping("/review/delete/{fname}")
			public String deleteByName(@PathVariable String fname) throws ResourceNotFoundException
			{
				String msg = reviewServiceImpl.deleteReviewByName(fname);
				return msg;
			}
			
			
			//get list of reviews given by any person to doctors by using there fname
			@GetMapping("/reviewlist/{fName}/user")
			public ResponseEntity<List<Review>> listByFname(@PathVariable String fName) throws ResourceNotFoundException
			{
				return ResponseEntity.ok(reviewServiceImpl.getListReviewByFname(fName));
			}
			
			@GetMapping("/reviewlist/{fName}/doctor")
			public ResponseEntity<List<Review>> ListByDoctorsFname(@PathVariable String fName) throws ResourceNotFoundException
			{
				return ResponseEntity.ok(reviewServiceImpl.getListReviewByDoctorsFname(fName));
			}
}
