package com.ecomrse.projectsw.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecomrse.projectsw.Aspect.RequiredRole;
import com.ecomrse.projectsw.Models.Product;
import com.ecomrse.projectsw.Repository.CategorieRepo;
import com.ecomrse.projectsw.Repository.ProductRepository;
import com.ecomrse.projectsw.Services.ProductService;
import com.ecomrse.projectsw.Services.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategorieRepo categorieRepo;

    @CrossOrigin(origins = "*")
    @GetMapping("/get")
    @RequiredRole("ALL")
    public List<Product> getAllProducts(@RequestParam("token") String token) {
        return productService.getproducts();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/manage/create_product")
    @RequiredRole("ADMIN")
    public ResponseEntity<String> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("amount") int amount,
            @RequestParam("categorie") long categorie,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file, @RequestParam("token") String token) {
        try {
            byte[] imageData = file.getBytes();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImage(imageData);
            product.setAmount(amount);
            product.setCategory(categorieRepo.getById(categorie));
            return productService.addproduct(product);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/manage/{id}/update")
    @RequiredRole("ADMIN")
    public ResponseEntity<String> updateProduct(@PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("amount") int amount,
            @RequestParam("categorie") long categorie,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file, @RequestParam("token") String token) {
        try {
            byte[] imageData = file.getBytes();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setId(id);
            product.setDescription(description);
            product.setImage(imageData);
            product.setAmount(amount);
            product.setCategory(categorieRepo.getById(categorie));
            return productService.updateProduct(product);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/manage/{id}/delete")
    @RequiredRole("ADMIN")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId,
            @RequestParam("token") String token) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        productService.deleteproduct(productId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/count")
    @RequiredRole("ADMIN")
    public int count(@RequestParam("token") String token) {
        return productService.countUserUsers();
    }

}
