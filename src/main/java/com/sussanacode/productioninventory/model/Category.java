package com.sussanacode.productioninventory.model;

import javax.persistence.*;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="category_name", nullable = false, length = 50, unique = true)
    private String name;


    public Category() {}

    public Category(Integer id) { this.id = id; }

    public Category(String name) { this.name = name; }


    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;

    }
}


