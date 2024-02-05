package com.anudip.yourcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anudip.yourcare.entity.Query;

public interface QueryRepository extends JpaRepository<Query,Integer>{

	public Query findByFname(String fname);
}
