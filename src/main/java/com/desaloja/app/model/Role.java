package com.desaloja.app.model;

import javax.persistence.*;

/**
 * Created by geoMateoLol.
 */
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "key")
	private String key;

	public Role() {
	}

	public Role(String name, Boolean active, String key) {
		super();
		this.name = name;
		this.active = active;
		this.key = key;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}