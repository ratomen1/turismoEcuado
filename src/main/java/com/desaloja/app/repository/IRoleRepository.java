package com.desaloja.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.desaloja.app.model.Role;

/**
 * Created by geoMateoLol.
 */
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    
    @Query("SELECT r FROM Role r WHERE upper(r.name) LIKE concat('%', upper(?1), '%')")
	public List<Role> findByNombre(String nombre);
	
	public List<Role> findByNameLikeIgnoreCase(String nombre);

}