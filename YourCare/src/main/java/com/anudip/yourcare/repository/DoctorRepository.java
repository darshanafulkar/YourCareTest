package com.anudip.yourcare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anudip.yourcare.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
 
	public Doctor findByFname(String fname);

	
}
