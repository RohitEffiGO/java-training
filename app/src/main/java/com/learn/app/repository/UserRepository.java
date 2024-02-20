package com.learn.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learn.app.model.Courses;
import com.learn.app.model.Role;
import com.learn.app.model.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(name = "SELECT * FROM cust_user WHERE email = ?", nativeQuery = true)
	User findByEmail(String email);

	@Query(value = "SELECT DISTINCT cu.id,cu.email, cu.name, cu.password FROM cust_user cu "
			+ "JOIN cust_user_role cur ON cu.id = cur.cust_user_id "
			+ "JOIN cust_role cr ON cur.cust_role_id = cr.id WHERE cr.role_type = :roleType", nativeQuery = true)
	List<User> getAllByRole(@Param("roleType") String roleType);

	List<User> findByRoles(Role role);

	@Query(value = "select cs.id,cs.author,cs.name,cs.date_added from courses cs"
			+ " JOIN user_subscribed us ON cs.id = us.cust_user_id where course_id = :id", nativeQuery = true)
	Optional<Courses> findBySubscribed(@Param("id") Long id);

	@Query(value = "select count(*) from user_subscribed where cust_user_id = :id", nativeQuery = true)
	Optional<Long> countSubbedCourses(@Param("id") Long id);

	@Transactional
	@Modifying
	@Query(value = "CALL escalate_role(:email)", nativeQuery = true)
	void escalateRole(@Param("email") String email);
}
