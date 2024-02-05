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

import com.anudip.yourcare.dto.AppointmentDto;
import com.anudip.yourcare.entity.Appointment;
import com.anudip.yourcare.entity.Doctor;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.service.impl.AppointmentServiceImpl;
import com.anudip.yourcare.service.impl.DoctorServiceImpl;

import jakarta.validation.Valid;



@RestController
public class AppointmentControler {

	@Autowired
	AppointmentServiceImpl appointmentServiceImpl;
	
	@Autowired
	DoctorServiceImpl doctorServiceImpl;
	
	
	@PostMapping("/appointment/create/{fname}")
	public ResponseEntity<Appointment> addAppointment(@RequestBody @Valid Appointment appointment,@PathVariable String fname) throws ResourceNotFoundException
	{
		Doctor doctor = doctorServiceImpl.getDoctorByName(fname);
		appointment.setDoctor(doctor);
		
		return new ResponseEntity<>(appointmentServiceImpl.createAppointment(appointment),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/appointments")
	public ResponseEntity<List<AppointmentDto>> getAllAppointments()
	{
		return ResponseEntity.ok(appointmentServiceImpl.getAllAppointments());
	}
	
	@GetMapping("/appointments/byid/{id}")
	public ResponseEntity<AppointmentDto> getAppointmentBYId(@PathVariable int id) throws ResourceNotFoundException
	{
		AppointmentDto appointmentDto=appointmentServiceImpl.getAppointmentById(id);
		return new ResponseEntity<>(appointmentDto,HttpStatus.OK);
	}
	
	@GetMapping("/appointments/byname/{fname}")
	public ResponseEntity<AppointmentDto> getAppointmentByFname(@PathVariable String fname) throws ResourceNotFoundException
	{
		AppointmentDto appointmentDto= appointmentServiceImpl.getAppointmentByName(fname);
		return new ResponseEntity<>(appointmentDto,HttpStatus.OK);
	}
	
	@PutMapping("/appointments/update")
	public ResponseEntity<Appointment> updateAppointmentByFname(@RequestBody Appointment appointment) throws ResourceNotFoundException
	{		
		return ResponseEntity.ok(appointmentServiceImpl.createAppointment(appointment));
	}
	
	@DeleteMapping("/appointments/{fname}/delete")
	public String deleteAppointmentById(@PathVariable String fname) throws ResourceNotFoundException
	{
		return appointmentServiceImpl.deleteAppointmentByName(fname);
	}
	
}
