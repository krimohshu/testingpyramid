package com.sdettest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sdettest.model.Product;
import com.sdettest.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createproduct")
    public Product createProduct(@RequestBody Product product){
        return productService.addProducts(product);
    }

    @RequestMapping(value = "/getproduct", method = RequestMethod.GET)
    public List<Product> getSearchedProductPrice(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "price", required = false) String price
                                 ) {
        List<Product> products = productService.getProductByParameter(name, price);
        productService.queryByNameAndPrice(name);
        return products;
    }

    @RequestMapping(value = "/getallproductprice", method = RequestMethod.GET)
    public double getSumOfAllProductPrice() {
        double sumOfAllProductPrice = productService.queryForSumOfTheColumn();

        return sumOfAllProductPrice;
    }

    @RequestMapping(value = "/getsearchedproductprice", method = RequestMethod.GET)
    public double gatSumOfSearchedProductPrice(@RequestParam(value = "name", required = false) String name
                                    ) {
        double sumOfProductPrice = productService.queryByNameAndPrice(name);

        return sumOfProductPrice;
    }

}
