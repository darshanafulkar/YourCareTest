package com.anudip.yourcare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anudip.yourcare.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer>{

	
	//List<Appointment> findDoctorByDocId(String fname);
	
	Appointment findAppointmentByFname(String fname);
}
