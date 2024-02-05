package com.anudip.yourcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anudip.yourcare.dto.UserDto;
import com.anudip.yourcare.entity.User;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.repository.UserRepository;
import com.anudip.yourcare.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//Method to create new user into the DB
	@Override
	public User createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	//Method to print all the existing users from DB
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	
	//Method to print user by using Id
	@Override
	public UserDto getUserById(int id) throws ResourceNotFoundException {
		
		Optional<User> existingUser = userRepository.findById(id);
		UserDto userDto = modelMapper.map(existingUser, UserDto.class);
		
		if(existingUser.isPresent())
		{
			return userDto;
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With Id : "+id);
		}
		
	}

	//Method to get the existing user by using name
	@Override
	public UserDto getUserByFname(String fname) throws ResourceNotFoundException {
		
		Optional<User> existingUser = Optional.ofNullable((userRepository.findByFname(fname)));
		 
		UserDto userDto = modelMapper.map(existingUser, UserDto.class);

		if(existingUser.isPresent())
		{
			return userDto;
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+fname);
		}
	}

	
	//Method to Update the existing user on the basis of Fname in DB
	@Override
	public User updateUserByName(User user) throws ResourceNotFoundException {
		User existingUser = userRepository.findByFname(user.getFname());

		if(existingUser != null)
		{
			existingUser.setLname(user.getLname());
			existingUser.setAddress(user.getAddress());
			existingUser.setAge(user.getAge());
			existingUser.setBloodgroup(user.getBloodgroup());
			existingUser.setCity(user.getCity());
			existingUser.setDateofbirth(user.getDateofbirth());
			existingUser.setEmail(user.getEmail());
			existingUser.setGender(user.getGender());
			existingUser.setPhonenumber(user.getPhonenumber());
			return userRepository.save(existingUser);
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+user.getFname());
		}
	}

	@Override
	public String deleteUserByName(String name) throws ResourceNotFoundException {
		
		User checkUser = userRepository.findByFname(name);
		
		if(checkUser != null)
		{
			userRepository.deleteById(checkUser.getId());
			return "User deleted Successfully";
		}
		else
		{
			throw new ResourceNotFoundException("User not Found With name : "+name);
		}
		
		
	}

}
