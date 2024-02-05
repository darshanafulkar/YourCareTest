package com.anudip.yourcare.controller;



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

import com.anudip.yourcare.dto.UserDto;
import com.anudip.yourcare.entity.User;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
public class AdminController {

	
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	//This will get the request to create the new user
		
		@PostMapping("/admin/create")
		public ResponseEntity<User> addAdmin(@RequestBody @Valid User adminUser)
		{
			return new ResponseEntity<>(userServiceImpl.createUser(adminUser),HttpStatus.CREATED);
		}
		
//		//This will get the request to print all the existing users
//		@GetMapping("/admin/all")
//		public ResponseEntity<List<AdminDto>> getAllAdmin()
//		{
//			return ResponseEntity.ok(adminServiceImpl.getAllAdmins());
//		}
//		
		
		//This will get the request to print the user by using id
		@GetMapping("/admin/ById/{id}")
		public ResponseEntity<UserDto> findAdminById(@PathVariable int id) throws ResourceNotFoundException
		{
			
			UserDto admin = userServiceImpl.getUserById(id);
	         return new ResponseEntity<>(admin, HttpStatus.OK);
			
		}
		
		//This will get the request to usdate the exixting user 
		@GetMapping("/admin/adminByName/{name}")
		public ResponseEntity<UserDto> findByName(@PathVariable String name) throws ResourceNotFoundException
		{
			
			UserDto admin = userServiceImpl.getUserByFname(name);
	        return new ResponseEntity<>(admin, HttpStatus.OK);
		}
		
		//This will Get the request to update the existing user in the DB
		@PutMapping("/admin/update")
		public ResponseEntity<User> updateUser(@RequestBody User adminUser) throws ResourceNotFoundException
		{
			return ResponseEntity.ok(userServiceImpl.updateUserByName(adminUser));
		}
		
		@DeleteMapping("/admin/delete/{fname}")
		public String deleteByName(@PathVariable String fname) throws ResourceNotFoundException
		{
			String msg = userServiceImpl.deleteUserByName(fname);
			return msg;
		}
}
