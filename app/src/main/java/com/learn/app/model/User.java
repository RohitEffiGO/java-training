package com.learn.app.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "cust_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	String name;

	@Column(name = "email", unique = true)
	@Email
	String email;

	@Column(name = "password")
	String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cust_user_role", joinColumns = @JoinColumn(name = "cust_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cust_role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_fav", joinColumns = @JoinColumn(name = "cust_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "courses_id", referencedColumnName = "id"))
	private Set<Courses> fav = new HashSet<>();
}
