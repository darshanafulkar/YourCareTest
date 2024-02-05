package com.anudip.yourcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anudip.yourcare.dto.DoctorDto;
import com.anudip.yourcare.entity.Doctor;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.repository.DoctorRepository;
import com.anudip.yourcare.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Doctor createDoctor(Doctor doctor) {
		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		return doctorRepository.save(doctor);
	}

	@Override
	public List<DoctorDto> getAllDoctor() {
		
		List<Doctor> doctors = doctorRepository.findAll();
		
		return  doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorDto.class)).collect(Collectors.toList());
	}

	@Override
	public Doctor getDoctorById(int id) throws ResourceNotFoundException {
		
		Doctor existingDoctor = doctorRepository.findById(id).get();
		
		if(existingDoctor != null)
		{
			return existingDoctor;
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With Id : "+id);	
		}
		
	}

	@Override
	public Doctor getDoctorByName(String name) throws ResourceNotFoundException {
		Doctor existingDoctor = doctorRepository.findByFname(name);
		
		if(existingDoctor != null)
		{
			return existingDoctor;
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+name);	
		}
		
	}

	@Override
	public Doctor updateDoctorByName(Doctor doctor) throws ResourceNotFoundException {
		
		Doctor existingDoctor = doctorRepository.findByFname(doctor.getFname());
		if(existingDoctor != null)
		{
			existingDoctor.setLname(doctor.getLname());
			existingDoctor.setAddress(doctor.getAddress());
			existingDoctor.setAge(doctor.getAge());
			existingDoctor.setDateofbirth(doctor.getDateofbirth());
			existingDoctor.setCity(doctor.getCity());
			existingDoctor.setEmail(doctor.getEmail());
			existingDoctor.setGender(doctor.getGender());
			existingDoctor.setPhonenumber(doctor.getPhonenumber());
			existingDoctor.setQualification(doctor.getQualification());
			existingDoctor.setSpecialization(doctor.getSpecialization());
			
			return doctorRepository.save(existingDoctor);
		}
		else
		{
			throw new ResourceNotFoundException("User not Found");	
		}
		
	}

	@Override
	public String deleteDoctorByName(String name) throws ResourceNotFoundException {
		
		Doctor checkDoctor = doctorRepository.findByFname(name);
		
		if(checkDoctor != null)
		{
			doctorRepository.deleteById(checkDoctor.getId());
			return "The Record is deleted successfully";
		}
		else
		{
			throw new ResourceNotFoundException("User not Found");	
		}
		
	}

	
	

}
