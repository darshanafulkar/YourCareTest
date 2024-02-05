package com.anudip.yourcare.service;

import java.util.List;
import java.util.Optional;

import com.anudip.yourcare.dto.DoctorDto;
import com.anudip.yourcare.entity.Doctor;
import com.anudip.yourcare.exception.ResourceNotFoundException;


public interface DoctorService {

    	//Method for creating the Doctor
		public Doctor createDoctor(Doctor doctor);
		
		//Methods for getting Doctor
		public List<DoctorDto> getAllDoctor();
		
		public Doctor getDoctorById(int id) throws ResourceNotFoundException;
		
		public Doctor getDoctorByName(String name) throws ResourceNotFoundException;
		
		//Method for Updating Doctor
		public Doctor updateDoctorByName(Doctor doctor) throws ResourceNotFoundException;
		
		
		//Method for Deleting the Doctor
		public String deleteDoctorByName(String name) throws ResourceNotFoundException;
		
		
		
}
