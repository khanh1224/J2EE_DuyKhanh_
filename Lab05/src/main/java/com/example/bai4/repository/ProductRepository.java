package com.example.bai4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bai4.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}