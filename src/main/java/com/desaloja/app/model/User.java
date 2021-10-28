package com.desaloja.app.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by geoMateoLol.
 */
@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	// @NotEmpty(message = "First name can't be empty!")
	@Column(name = "first_name")
	private String firstName;

	// @NotEmpty(message = "Last name can't be empty!")
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	// @Email(message = "*Please provide a valid Email")
	// @NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "password")
	// @Length(min = 5, message = "*Your password must have at least 5 characters")
	// @NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "active")
	private Boolean active;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))

	private Collection<Role> roles;

	protected User() {
	}

	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Role> getRoles() {
		return this.roles;
	}
	
	public void addRole(Role role) {
		if(Objects.isNull(this.roles)) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", roles=" + roles + "]";
	}
	
	// other getters and setters are hidden for brevity
}