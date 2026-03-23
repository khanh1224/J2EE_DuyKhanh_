package com.example.bai4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bai4.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}