package com.desaloja.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desaloja.app.model.Role;
import com.desaloja.app.repository.IRoleRepository;

/**
 * Created by geoMateoLol.
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role findByName(String name) {
		roleRepository.findByName(name);
		return null;
	}

	@Override
	public List<Role> findByNombre(String nombre) {
		return roleRepository.findByNombre(nombre);
	}

	@Override
	public List<Role> findByNameLikeIgnoreCase(String nombre) {
		return roleRepository.findByNameLikeIgnoreCase("%" + nombre + "%");
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

}
