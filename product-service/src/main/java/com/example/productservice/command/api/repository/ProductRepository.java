package com.example.productservice.command.api.repository;

import com.example.productservice.command.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
