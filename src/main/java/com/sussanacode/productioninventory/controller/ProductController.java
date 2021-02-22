package com.sussanacode.productioninventory.controller;

import com.sussanacode.productioninventory.configuration.CategoryRepository;
import com.sussanacode.productioninventory.configuration.ProductRepository;
import com.sussanacode.productioninventory.model.Category;
import com.sussanacode.productioninventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;


    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }


    @GetMapping("/products")
    public String productList(Model model) {
        List<Product> productList = productRepo.findAll();
        model.addAttribute("productList", productList);
        return "products";
    }

    //create new product
    @GetMapping("/product/new")
    public String productEntry(Model model){
        List<Category> listCategories = categoryRepo.findAll();

        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);

        return "productEntry";

    }

    @PostMapping("/product/save")
    public String saveProduct(Product product, HttpServletRequest request){
        String[] detailIDs = request.getParameterValues("detailId");
        String[] detailNames = request.getParameterValues("detailName");
        String[] detailValues = request.getParameterValues("detailValue");

        for(int i = 0; i < detailNames.length; i++){
            if(detailIDs != null && detailIDs.length > 0){
                product.setProductDetail(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);

            }else{
                product.addProductDetail(detailNames[i], detailValues[i]);
            }
        }

        productRepo.save(product);

        return "redirect:/products";

    }


    //edit product
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model){
       Product product = productRepo.findById(id).get();
       model.addAttribute("product", product);

       List<Category> listCategories = categoryRepo.findAll();
       model.addAttribute("listCategories", listCategories);

       return "productEntry";
    }


    //delete product
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model){

        productRepo.deleteById(id);

        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("listCategories", listCategories);

        return "redirect:/products";
    }


    @GetMapping("/accessdenied")
    public String error403() {
        return "accessdenied";
    }


}
