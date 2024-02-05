package com.anudip.yourcare.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;



import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {


	@Column(name="first_name",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Accept only alphabets")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper first name")
	private String fname;
	
	@Column(name="last_name",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Accept only alphabets")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper last name")
	private String lname;
	
	@Column(name="user_contact",unique=true,length=10)
	@NotBlank(message="This filed is requird")
	@Pattern(regexp = "^[0-9]{10}$",message="Please enter a valid Phone Number")
	private String phonenumber;
	
	@Column(name="user_Address",length=60)
	@Pattern(regexp = "^[a-zA-Z0-9\\s#.,-]*$",message="Please enter a valid address")
	@Size(min=4,max = 60,message="Please enter a proper address")
	private String address;
	

	@NotNull(message = "Date of birth must be provided")
    @Min(value = 1, message = "Age must be at least 1")
	private int age;
	
	@Column(name="gender" ,length= 2)
	@Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
	private String gender;
	
	@Column(name="Deseases",length=30)
	@Pattern(regexp = "^[a-zA-Z0-9\\s#.,-]*$",message="Please enter a valid Deseases")
	@Size(min=4,max = 60,message="Please enter a proper Deseases")
	private String deseases;
	
	@Column(name="Symptoms",length=60)
	@Pattern(regexp = "^[a-zA-Z0-9 ,.()-]*$",message="Please enter a valid symptoms")
	@Size(min=4,max = 60,message="Please enter a proper symptoms")
	private String symptoms;
	

	@Column(name="appointment_date")
	@NotBlank(message="This filed is requird")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate appointmentdate;
	
	@Column(name="appointment_time")
	@NotBlank(message="This filed is requird")
	private String appointmenttime;
	
	@Column(name="specialization",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid specialization")
	@NotBlank(message="This filed is requird")
	@Size(min=2,max = 20,message="Please enter a proper specialization")
	private String specialization;
	
}
