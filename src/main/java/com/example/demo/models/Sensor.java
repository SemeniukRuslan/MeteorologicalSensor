package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Sensor")
@Getter
@Setter
public class Sensor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "Should be not empty")
    @Size(min = 3, max = 30, message = "The name must be between 3 and 30 characters")
    private String name;
}