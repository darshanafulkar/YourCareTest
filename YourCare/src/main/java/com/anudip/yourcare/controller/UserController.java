package com.anudip.yourcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	//This will get the request to create the new user
	@PostMapping("/user/create")
	public ResponseEntity<User> addUser(@RequestBody @Valid User user)
	{
		return new ResponseEntity<>(userServiceImpl.createUser(user),HttpStatus.CREATED);
	}
	
	//This will get the request to print all the existing users
	@GetMapping("/user/all")
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return ResponseEntity.ok(userServiceImpl.getAllUsers());
	}
	
	
	//This will get the request to print the user by using id
	@GetMapping("/user/userById/{id}")
	public ResponseEntity<UserDto> findUserById(@PathVariable int id) throws ResourceNotFoundException
	{
		//return ResponseEntity.ok(userServiceImpl.getUserById(id));
		
		 UserDto user = userServiceImpl.getUserById(id);
         return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
	
	//This will get the request to usdate the exixting user 
	@GetMapping("/user/userByName/{name}")
	public ResponseEntity<UserDto> findByName(@PathVariable String name) throws ResourceNotFoundException
	{
		//return ResponseEntity.ok(userServiceImpl.getUserByFname(name));
		
		UserDto user = userServiceImpl.getUserByFname(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	//This will Get the request to update the existing user in the DB
	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ResourceNotFoundException
	{
		return ResponseEntity.ok(userServiceImpl.updateUserByName(user));
	}
	
	@DeleteMapping("/user/delete/{fname}")
	public String deleteByName(@PathVariable String fname) throws ResourceNotFoundException
	{
		String msg = userServiceImpl.deleteUserByName(fname);
		return msg;
	}
}
