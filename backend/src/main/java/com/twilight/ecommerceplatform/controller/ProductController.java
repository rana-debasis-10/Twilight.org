package com.twilight.ecommerceplatform.controller;

import com.twilight.ecommerceplatform.entities.Product;
import com.twilight.ecommerceplatform.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    private static final String UPLOAD_DIRECTORY="/"; //Path to be declared
    @PostMapping("/")//Path to be declared
    public String addProduct(@RequestParam("productName")String prodName,
                             @RequestParam("prouctCategory")String prodCat, @RequestParam("Price")int price,
                             @RequestParam("productImage")MultipartFile file, Model model) {
        Product product = new Product();
        if(file.isEmpty()){
            model.addAttribute("message", "Please select a file");
            return "error";}
        try {
            File dir = new File(UPLOAD_DIRECTORY);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Path destPath= Paths.get(UPLOAD_DIRECTORY).resolve(Paths.get(file.getOriginalFilename()).normalize().toAbsolutePath());

            Files.copy(file.getInputStream(), destPath, StandardCopyOption.REPLACE_EXISTING);
            product.setFileName(file.getOriginalFilename());
            product.setFilePath("/" + file.getOriginalFilename());
            return "redirect:/"+prodCat+"/"+price; //Path to be declared
        }catch (IOException e){
            e.printStackTrace();
            return "error";
        }
    }

}
