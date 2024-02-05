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
public class UserDto {

	@Column(name="first_name",length=30)
	@Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]*$",message="Please enter a valid first name")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper first name")
	private String fname;
	
	@Column(name="last_name",length=30)
	@Pattern(regexp = "^(?!\\s*$)[a-zA-Z\\s]*$",message="Please enter a valid first name")
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
	private String phonenumber;
	
	@Column(name="date_of_birth")
	@Past(message = "Date of birth must be in the past")
    @DateTimeFormat(pattern = "^\\d{4}-\\d{2}-\\d{2}$")
	private LocalDate dateofbirth;
	
	@NotNull(message = "Date of birth must be provided")
    @Min(value = 1, message = "Age must be at least 1")
	private int age;
	
	@Column(name="gender" ,length= 2)
	@Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
	private String gender;
	
	@Column(name="user_city",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid city")
	@NotBlank(message="This filed is requird")
	@Size(min=4,max = 30,message="Please enter a proper name of the city")
	private String city;
	
	@Column(name="user_Address",length=60)
	@Pattern(regexp = "^[a-zA-Z0-9\\\\s#.,-]*$",message="Please enter a valid address")
	@Size(min=4,max = 60,message="Please enter a proper address")
	private String address;
	

	@Column(name="user_bloodgroup",length=10)
	@Pattern(regexp = "^(A|B|AB|O)[+-]$",message="Please enter a valid blood group for eg. A+")
	@NotBlank(message="This filed is requird")
	private String bloodgroup;
}
