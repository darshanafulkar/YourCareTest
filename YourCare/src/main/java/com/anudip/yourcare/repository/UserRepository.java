package com.anudip.yourcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anudip.yourcare.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	public User findByFname(String fname);

	public User findByEmail(String email);
}
