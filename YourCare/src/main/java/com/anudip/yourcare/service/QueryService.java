package com.anudip.yourcare.service;

import java.util.List;

import com.anudip.yourcare.dto.QueryDto;
import com.anudip.yourcare.entity.Query;
import com.anudip.yourcare.exception.ResourceNotFoundException;


public interface QueryService {

	//Method for creating the Query
		public Query createQuery(Query query);
		
		//Methods for getting Query
		public List<QueryDto> getAllQuery();
		
		public QueryDto getQueryById(int id) throws ResourceNotFoundException;
		
		public QueryDto getQueryByName(String name) throws ResourceNotFoundException;
		
		//Method for Updating Query
		public Query updateQueryByName(Query query) throws ResourceNotFoundException;
		
		
		//Method for Deleting the Query
		public String deleteQueryByName(String name) throws ResourceNotFoundException;
		
		
}
