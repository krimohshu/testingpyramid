package com.sdettest.service;

import com.sdettest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdettest.model.Product;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public ProductService() {

    }

    public ProductService(ProductRepository mockRepo) {
        this.productRepository=mockRepo;
    }

    public Product addProducts(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductByParameter(String name, String price) {
        List<Product> products = null;

        if (name!=null && price!=null) {
            products = productRepository.findAll();
        }
        else if(name!=null & price==null)
        {
            products = productRepository.findByName(name);
        }
        else if(price!=null & name==null){
            products = productRepository.findByPrice(price);
        }

        return products;

    }

    public double queryByNameAndPrice(String name) {
        return productRepository.queryByNameAndPrice(name);
    }

    public double queryForSumOfTheColumn() {
        return productRepository.queryForSumOfTheColumn();
    }
}
