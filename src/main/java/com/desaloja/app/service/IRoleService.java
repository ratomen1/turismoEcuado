package com.desaloja.app.service;

import java.util.List;

import com.desaloja.app.model.Role;

public interface IRoleService {
	List<Role> findAll();
	void save(Role role);
	Role findByName(String name);
	List<Role> findByNombre(String nombre);
	List<Role> findByNameLikeIgnoreCase(String nombre);
	Role findById(Long id);
}
