
package com.anudip.yourcare.entity;


import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="review_id")
	private int reviewid;
	
	@Column(name="first_name",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Accept only alphabets ")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper first name")
	private String fname;
	
	@Column(name="last_name",length=30)
	@Pattern(regexp = "^[a-zA-Z\\s]*$",message="Please enter a valid first name")
	@NotBlank(message="This filed is requird")
	@Size(min=3,max = 20,message="Please enter a proper last name")
	private String lname;
	
	@Min(1)
    @Max(5)
    private float rating;
	
	@Size(max=500, message="Comment should be atmost 500 charecters")
	private String comments;
	
	@ManyToOne
	private Doctor doctor;

	//public void setDoctor(Optional<Doctor> doc) {
		// TODO Auto-generated method stub
		
	//}

	//public void setDoctor(Doctor doctor2) {
		// TODO Auto-generated method stub
		
	//}
	
}
