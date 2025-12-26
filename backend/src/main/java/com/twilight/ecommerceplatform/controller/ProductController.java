package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.DataToObjects.ProductDTO;
import com.twilight.ecommerceplatform.entities.Product;
import com.twilight.ecommerceplatform.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    private static final String UPLOAD_DIRECTORY="/"; //Path to be declared
    @PostMapping("/products")//Path to be declared
    public ResponseEntity<?>  createProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/viewitem")
    public String getAllProducts(Model model){
        List<Product> product = productRepo.findAll();
        model.addAttribute("product",product);
        return "viewitem";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productRepo.deleteById(id);
        return "redirect:/viewitem";
    }

    @RequestMapping("/find/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model){
        Product product = productRepo.findById(id).orElseThrow();
        model.addAttribute("product",product);
        return "updateForm";

    }

    @PostMapping ("/update")
    public ResponseEntity<?>  updateProduct(@RequestBody ProductDTO product){
        return ResponseEntity.ok().build();
    }



}
