package com.anudip.yourcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anudip.yourcare.dto.QueryDto;
import com.anudip.yourcare.entity.Query;
import com.anudip.yourcare.exception.ResourceNotFoundException;
import com.anudip.yourcare.repository.QueryRepository;
import com.anudip.yourcare.service.QueryService;

@Service
public class QueryServiceImpl implements QueryService{

	@Autowired
	QueryRepository queryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	//Method to add the new query into DB
	@Override
	public Query createQuery(Query query) {
		
		return queryRepository.save(query);
	}
	
	//Method to get/print all the query available
	@Override
	public List<QueryDto> getAllQuery() {
		
		List<Query> queres = queryRepository.findAll();
		
		return queres.stream().map(query -> modelMapper.map(query, QueryDto.class)).collect(Collectors.toList());
		
	}

	//Method to find the query by query id
	@Override
	public QueryDto getQueryById(int id) throws ResourceNotFoundException {
		
		Optional<Query> query = queryRepository.findById(id);
		
		if(query.isPresent())
		{
			QueryDto queryDto = modelMapper.map(query, QueryDto.class);
			return queryDto;
		}
		else
		{
			throw new ResourceNotFoundException("Query not Found With Id : "+id);
		}
	}

	//Method to find the Query by name
	@Override
	public QueryDto getQueryByName(String name) throws ResourceNotFoundException {
		
		Optional<Query> query = Optional.ofNullable(queryRepository.findByFname(name));
		
		if(query.isPresent())
		{
			QueryDto queryDto = modelMapper.map(query, QueryDto.class);
			return queryDto;
		}
		else
		{
			throw new ResourceNotFoundException("Query not Found With name : "+name);
		}
	}

	@Override
	public Query updateQueryByName(Query query) throws ResourceNotFoundException {
		
		Query existingQuery = queryRepository.findByFname(query.getFname());
		
		if(existingQuery != null)
		{
			existingQuery.setLname(query.getLname());
			existingQuery.setEmail(query.getEmail());
			existingQuery.setPhoneno(query.getPhoneno());
			existingQuery.setSpeciality(query.getSpeciality());
			existingQuery.setMessage(query.getMessage());
			
			return queryRepository.save(existingQuery);
		}
		else
		{
			throw new ResourceNotFoundException("Query not Found ");
		}
	
		
	}

	@Override
	public String deleteQueryByName(String name) throws ResourceNotFoundException {
		
		Query checkquery = queryRepository.findByFname(name);
		
		if(checkquery !=  null)
		{
			queryRepository.deleteById(checkquery.getId());
			return "Data deleted successfully";
		}
		else
		{
			throw new ResourceNotFoundException("Query not Found ");
		}
		
	}

}
