package com.sussanacode.productioninventory.controller;

import com.sussanacode.productioninventory.configuration.CategoryRepository;
import com.sussanacode.productioninventory.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/productCategory")
    public String listCategories(Model model){
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("listCategories", listCategories);

        return "productCategory";
    }

    @GetMapping("/productCategory/new")
    public String createNewCategory(Model model){
        model.addAttribute("category", new Category());

        return "categoryForm";
    }

    @PostMapping("/productCategory/save")
    public String saveCategory(Category category){
        categoryRepo.save(category);

        return "redirect:/productCategory";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id) {
        categoryRepo.deleteById(id);

        return "redirect:/productCategory";
    }


//    @GetMapping("/accessdenied")
//    public String error403() {
//        return "accessdenied";
//    }



}
