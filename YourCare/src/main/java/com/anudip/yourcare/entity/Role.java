package com.anudip.yourcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Roles")
public class Role {

	@Id
	@Column(name = "Role_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="Role_Name")
	private String role;

	
}
