package com.desaloja.app.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.desaloja.app.model.Product;
import com.desaloja.app.model.ProductType;
import java.util.List;

/**
 * Created by geoMateoLol.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private com.desaloja.app.service.ProductService productService;

    @Autowired
    private com.desaloja.app.service.ProductTypeService productTypeService;

    private static final String ADD_EDIT_TEMPLATE="/admin/product/add-edit-product";
    private static final String LIST_TEMPLATE="/admin/product/list-product";
    private static final String LIST_REDIRECT="redirect:/product/list";
    private static final String PRODUCT_TYPES="productTypes";

    @GetMapping("/add")
    public String addProduct(Product product, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute(PRODUCT_TYPES,productTypes);

        return ADD_EDIT_TEMPLATE;
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute(PRODUCT_TYPES,productTypes);

        return ADD_EDIT_TEMPLATE;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute(PRODUCT_TYPES,productTypes);

        if(result.hasErrors()){
            return ADD_EDIT_TEMPLATE;
        }

        productService.save(product);
        return LIST_REDIRECT+"?success";
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.delete(id);

        return LIST_REDIRECT+"?deleted";
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute(PRODUCT_TYPES,productTypes);

        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);

        return LIST_TEMPLATE;
    }
}