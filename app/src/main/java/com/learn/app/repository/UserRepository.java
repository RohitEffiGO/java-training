package com.learn.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(name = "SELECT * FROM cust_user WHERE email = ?")
	User findByEmail(String email);
}
