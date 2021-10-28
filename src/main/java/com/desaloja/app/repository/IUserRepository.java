package com.desaloja.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desaloja.app.model.User;

/**
 * Created by geoMateoLol.
 */
public interface IUserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.active = ?1")
	Page<User> findByActivePaged(Boolean activo, Pageable pageable);

}