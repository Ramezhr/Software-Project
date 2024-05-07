package com.ecomrse.projectsw.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomrse.projectsw.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
