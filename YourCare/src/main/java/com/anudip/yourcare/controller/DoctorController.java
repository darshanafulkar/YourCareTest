package com.anudip.yourcare.controller;

import java.util.List;


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

import com.anudip.yourcare.dto.DoctorDto;
import com.anudip.yourcare.entity.Doctor;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.service.impl.DoctorServiceImpl;

import jakarta.validation.Valid;

@RestController
public class DoctorController {

	@Autowired
	DoctorServiceImpl doctorServiceImpl;
	
	   //This will get the request to create the new Doctor
		@PostMapping("/doctors/create")
		public ResponseEntity<Doctor> addDoctor(@RequestBody @Valid Doctor doctor)
		{
			return new ResponseEntity<>(doctorServiceImpl.createDoctor(doctor),HttpStatus.CREATED);
		}
		
		//This will get the request to print all the existing Doctor
		@GetMapping("/doctors")
		public ResponseEntity<List<DoctorDto>> getAllUser(@RequestBody Doctor doctor)
		{
			return ResponseEntity.ok(doctorServiceImpl.getAllDoctor());
		}
		
		
		//This will get the request to print the Doctor by using id
		@GetMapping("/doctors/byid/{id}")
		public ResponseEntity<Doctor> findDoctorById(@PathVariable int id) throws ResourceNotFoundException
		{
			Doctor doctor = doctorServiceImpl.getDoctorById(id);
			return new ResponseEntity<>(doctor,HttpStatus.OK);
		}
		
		//This will get the request to update the existing Doctor 
		@GetMapping("/doctors/byname/{fname}")
		public ResponseEntity<Doctor> findByName(@PathVariable String fname) throws ResourceNotFoundException
		{
			Doctor doctor = doctorServiceImpl.getDoctorByName(fname);
			return new ResponseEntity<>(doctor,HttpStatus.OK);
		}
		
		
		//This will Get the request to update the existing doctor in the DB
		@PutMapping("/doctors/update")
		public ResponseEntity<Doctor> updateDoctor(@RequestBody @Valid Doctor doctor) throws ResourceNotFoundException
		{
			Doctor doctor1 = doctorServiceImpl.updateDoctorByName(doctor);
			return new ResponseEntity<>(doctor1,HttpStatus.OK);
		}
		
		@DeleteMapping("/doctors/delete/{fname}")
		public String deleteByName(@PathVariable String fname) throws ResourceNotFoundException
		{
			String msg = doctorServiceImpl.deleteDoctorByName(fname);
			return msg;
		}
		
		
}
