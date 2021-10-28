package com.desaloja.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desaloja.app.model.Product;

import java.util.List;

/**
 * Created by geoMateoLol.
 */
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            //"join p.productType pt " +
            "WHERE 1=1" +
            "	AND ( upper(p.name) like concat('%', upper(?1), '%') " +
            "   OR upper(p.brand) like concat('%', upper(?1), '%') " +
            "   OR upper(p.madein) like concat('%', upper(?1), '%')" +
            //"       OR upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Product> searchProduct(String criteria);
}