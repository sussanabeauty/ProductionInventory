package com.sussanacode.productioninventory.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="product_name", nullable = false, length = 50,unique = true)
    private String name;

    @Column(name="product_maker", nullable = false, length = 50, unique = true)
    private String maker;

    private String aisle;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetails> productDetails = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductDetails> getProductDetails() { return productDetails; }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    public void setProductDetail(Integer id, String name, String value) {
        this.productDetails.add(new ProductDetails(id, name, value, this));
    }

    public void addProductDetail(String name, String value){
        this.productDetails.add(new ProductDetails(name, value, this));


    }


}
