package com.anudip.yourcare.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {

	@Column(name="first_name",length=30)
	@Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]*$",message="Accept only alphabets")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper first name")
	private String fname;
	
	@Column(name="last_name",length=30)
	@Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]*$",message="Accept only alphabets")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper last name")
	private String lname;
	
	@Column(name="user_email",unique=true,length=50)
	@NotBlank(message="This filed is requird")
	@Email
	private String email;
	
	@Column(name="user_contact",unique=true,length=10)
	@NotBlank(message="This filed is requird")
	@Pattern(regexp = "^[0-9]{10}$",message="Please enter a valid Phone Number")
	private String phoneno;

	
	@Column(name="specialization",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid specialization")
	@NotBlank(message="This filed is requird")
	@Size(min=2,max = 20,message="Please enter a proper specialization")
	private String speciality;
	
	@Size(max=500, message="Message should be atmost 500 charecters")
	private String message;
}
