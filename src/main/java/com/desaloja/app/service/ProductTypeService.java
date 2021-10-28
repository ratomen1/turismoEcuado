package com.desaloja.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.desaloja.app.model.ProductType;
import com.desaloja.app.repository.IProductTypeRepository;
import java.util.List;

/**
 * Created by geoMateoLol.
 */
@Service
@Transactional
public class ProductTypeService {

    @Autowired
    private IProductTypeRepository repo;

    public List<ProductType> listAll() {
        return (List<ProductType>) repo.findAll();
    }

    public void save(ProductType product) {
        repo.save(product);
    }

    public ProductType get(long  id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}