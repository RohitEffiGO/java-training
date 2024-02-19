package com.learn.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learn.app.model.Role;
import com.learn.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(name = "SELECT * FROM cust_user WHERE email = ?", nativeQuery = true)
	User findByEmail(String email);

	@Query(value = "SELECT DISTINCT cu.id,cu.email, cu.name, cu.password FROM cust_user cu "
			+ "JOIN cust_user_role cur ON cu.id = cur.cust_user_id "
			+ "JOIN cust_role cr ON cur.cust_role_id = cr.id WHERE cr.role_type = :roleType", nativeQuery = true)
	List<User> getAllByRole(@Param("roleType") String roleType);

	List<User> findByRoles(Role role);
}
