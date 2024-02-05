package com.anudip.yourcare.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

	@Column(name="first_name",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Accept only alphabets ")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper first name")
	private String fname;
	
	@Column(name="last_name",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Accept only alphabets")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper last name")
	private String lname;
	
	@Column(name="doctors_email",unique=true,length=50)
	@NotBlank(message="This filed is requird")
	@Email
	private String email;
	
	@Column(name="doctors_contact",unique=true,length=10)
	@NotBlank(message="This filed is requird")
	@Pattern(regexp = "^[0-9]{10}$",message="Please enter a valid Phone Number")
	private String phonenumber;
	

	@Column(name="date_of_birth")
	@Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "^\\d{4}-\\d{2}-\\d{2}$")
	private LocalDate birthofdate;
	
	@NotNull(message = "Date of birth must be provided")
    @Min(value = 1, message = "Age must be at least 1")
	private int age;
	
	@Column(name="gender" ,length= 2)
	@Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
	private String gender;
	
	@Column(name="doctors_city",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid city")
	@NotBlank(message="This filed is requird")
	@Size(min=4,max = 30,message="Please enter a proper name of the city")
	private String city;
	
	@Column(name="Qualification",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid qulification")
	@NotBlank(message="This filed is requird")
	@Size(min=2,max = 20,message="Please enter a proper qualification")
	private String qualification;
	
	@Column(name="specialization",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid specialization")
	@NotBlank(message="This filed is requird")
	@Size(min=2,max = 20,message="Please enter a proper specialization")
	private String specialization;
}
