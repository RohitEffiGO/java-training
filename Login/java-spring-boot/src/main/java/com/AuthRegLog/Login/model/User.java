package com.AuthRegLog.Login.model;

import java.util.Collection;

import jakarta.persistence.*;

@Entity
@Table(name="custom_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "custom_user_roles",
			joinColumns = @JoinColumn(
					name = "custom_user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "custom_role_id",referencedColumnName = "id"))
	private Collection<Role> roles;
	
	public User(String name, String email, String password, Collection<Role> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public User(String email,String password)
	{
		super();
		this.email = email;
		this.password = password;
	}
	
	public User()
	{
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
}
