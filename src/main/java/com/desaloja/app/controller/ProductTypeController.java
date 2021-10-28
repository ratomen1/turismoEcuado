package com.desaloja.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.desaloja.app.model.ProductType;

import java.util.List;

/**
 * Created by geoMateoLol.
 */
@Controller
@RequestMapping("/producttype")
public class ProductTypeController {

    @Autowired
    private com.desaloja.app.service.ProductTypeService productTypeService;

    private static final String ADD_EDIT_TEMPLATE="/admin/producttype/add-edit-producttype";
    private static final String LIST_TEMPLATE="/admin/producttype/list-producttype";
    private static final String LIST_REDIRECT="redirect:/producttype/list";
    private static final String PRODUCT_TYPE="producttype";

    @GetMapping("/add")
    public String addProductType(ProductType producttype, Model model){
        model.addAttribute(PRODUCT_TYPE, producttype);
        return ADD_EDIT_TEMPLATE;
    }

    @GetMapping("/edit/{id}")
    public String editProductType(@PathVariable("id") int id, Model model){
        ProductType producttype = productTypeService.get(id);

        model.addAttribute("producttype", producttype);

        return ADD_EDIT_TEMPLATE;
    }

    @PostMapping("/save")
    public String saveProductType(@Valid @ModelAttribute(PRODUCT_TYPE) ProductType producttype, BindingResult result, Model model){
        model.addAttribute(PRODUCT_TYPE, producttype);

        if(result.hasErrors()){
            return ADD_EDIT_TEMPLATE;
        }
        productTypeService.save(producttype);

        return LIST_REDIRECT+"?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductType(@PathVariable("id") int id, Model model) {
        productTypeService.delete(id);

        return LIST_REDIRECT+"?deleted";
    }

    @GetMapping("/list")
    public String listProductType(Model model) {
        List<ProductType> listProductTypes = productTypeService.listAll();
        model.addAttribute("listProductTypes", listProductTypes);

        return LIST_TEMPLATE;
    }
}