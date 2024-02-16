package com.learn.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learn.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(name = "SELECT * FROM cust_role WHERE role_type = ?")
	Optional<Role> findByRoleType(String roleType);
}
