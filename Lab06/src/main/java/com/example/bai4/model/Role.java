package com.example.bai4.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}