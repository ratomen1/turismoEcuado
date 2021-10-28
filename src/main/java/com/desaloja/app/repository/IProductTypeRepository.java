package com.desaloja.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desaloja.app.model.ProductType;

/**
 * Created by geoMateoLol.
 */
public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {

}