package com.anudip.yourcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anudip.yourcare.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{

	
	public Review findByFname(String fname);
	
	
	
	public List<Review> findReviewByFname(String fname);
	
	
	public List<Review> findDoctorByFname(String fanem);
}
