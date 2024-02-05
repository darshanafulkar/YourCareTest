package com.anudip.yourcare.controller;


import java.util.List;
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

import com.anudip.yourcare.dto.QueryDto;
import com.anudip.yourcare.entity.Query;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.service.impl.QueryServiceImpl;

import jakarta.validation.Valid;

@RestController
public class QueryController {

	@Autowired
	QueryServiceImpl queryServiceImpl;
	
	//This will get the request for creating a new query
	@PostMapping("/query/create")
	public ResponseEntity<Query> addQuery(@RequestBody @Valid Query query)
	{
		return new ResponseEntity<>(queryServiceImpl.createQuery(query),HttpStatus.CREATED);
	}
	
	//This will get the request to print all the available query
	@GetMapping("/queries")
	public ResponseEntity<List<QueryDto>> findAllQuery(@RequestBody QueryDto query)
	{
		return ResponseEntity.ok(queryServiceImpl.getAllQuery());
	}

	//This will get the request to print the query by id
	@GetMapping("/queries/{id}")
	public ResponseEntity<QueryDto> findQueryById(@PathVariable int id) throws ResourceNotFoundException
	{
		
		QueryDto queryDto = queryServiceImpl.getQueryById(id);
		return new ResponseEntity<>(queryDto,HttpStatus.OK);
	}
	
	//This will get the request to print the query by name
	@GetMapping("/queries/{name}")
	public ResponseEntity<QueryDto> findQueryByName(@PathVariable String name) throws ResourceNotFoundException
	{
		QueryDto queryDto = queryServiceImpl.getQueryByName(name);
		return new ResponseEntity<>(queryDto,HttpStatus.OK);
	}
	
	//This will get the request to update the query into database according to name 
	@PutMapping("/queries/update")
	public ResponseEntity<Query> updateQueryByname(@RequestBody Query query) throws ResourceNotFoundException
	{
		return ResponseEntity.ok(queryServiceImpl.updateQueryByName(query));	
	}
	
	//This will get the request to delete the existing Doctor record in DB
	@DeleteMapping("/queries/{name}")
	public String deleteQueryByName(@PathVariable String name) throws ResourceNotFoundException
	{
		String msg = queryServiceImpl.deleteQueryByName(name);
		
		return msg;
	}
}
