package com.anudip.yourcare.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anudip.yourcare.dto.AppointmentDto;
import com.anudip.yourcare.entity.Appointment;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.repository.AppointmentRepository;
import com.anudip.yourcare.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	

	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Appointment createAppointment(Appointment appointment) {
		
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<AppointmentDto> getAllAppointments() {
		
		List<Appointment> appointments = appointmentRepository.findAll();
		return appointments.stream().map(appointment -> modelMapper.map(appointment, AppointmentDto.class)).collect(Collectors.toList());
	}

	
	@Override
	public AppointmentDto getAppointmentById(int id) throws ResourceNotFoundException {
		Appointment existingAppointment = appointmentRepository.findById(id).get();
		
		if(existingAppointment != null)
		{
			AppointmentDto appointmentDto = modelMapper.map(existingAppointment, AppointmentDto.class);
			return appointmentDto;
		}
		else
		{
			throw new ResourceNotFoundException("No Appointment found with id : "+id);
		}
		
	}

	@Override
	public AppointmentDto getAppointmentByName(String fname) throws ResourceNotFoundException {
		Appointment existingAppointment = appointmentRepository.findAppointmentByFname(fname);
		
		if(existingAppointment != null)
		{
			AppointmentDto appointmentDto = modelMapper.map(existingAppointment, AppointmentDto.class);
			return appointmentDto;
		}
		else
		{
			throw new ResourceNotFoundException("No Appointment found with name : "+fname);
		}
	}

	@Override
	public Appointment updateAppointmentByName(Appointment appointment) throws ResourceNotFoundException {
		Appointment existingAppointment = appointmentRepository.findAppointmentByFname(appointment.getFname());
		
		if(existingAppointment != null)
		{
			existingAppointment.setLname(appointment.getLname());
			existingAppointment.setAddress(appointment.getAddress());
			existingAppointment.setAge(appointment.getAge());
			existingAppointment.setAppointmentdate(appointment.getAppointmentdate());
			existingAppointment.setAppointmenttime(appointment.getAppointmenttime());
			existingAppointment.setDeseases(appointment.getDeseases());
			existingAppointment.setGender(appointment.getGender());
			existingAppointment.setPhonenumber(appointment.getPhonenumber());
			existingAppointment.setSpecialization(appointment.getSpecialization());
			existingAppointment.setSymptoms(appointment.getSymptoms());
			existingAppointment.setDoctor(appointment.getDoctor());
			
			return appointmentRepository.save(existingAppointment);
		}
		else
		{
			throw new ResourceNotFoundException("No Appointment found ");
		}
		
	}

	@Override
	public String deleteAppointmentByName(String name) throws ResourceNotFoundException {
		Appointment existingAppointment = appointmentRepository.findAppointmentByFname(name);
		if(existingAppointment != null)
		{
			appointmentRepository.delete(existingAppointment);
			
			return "The appointment is deleted";
		}
		else
		{
			throw new ResourceNotFoundException("No Appointment found ");
		}
		
	}

}
