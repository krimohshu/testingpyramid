package com.sdettest.service;

import com.sdettest.dto.ProductDTO;
import com.sdettest.exceptions.ProductPriceCalculationException;
import com.sdettest.model.Product;
import com.sdettest.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductPriceService implements IProductPriceService{
    Logger logger = LoggerFactory.getLogger(ProductPriceService.class);

    @Autowired
    private ProductRepository repo;

    public ProductPriceService(){}
    public ProductPriceService(ProductRepository repo){
        this.repo = repo;
    }

    public Double getPriceForAllProduct() throws ProductPriceCalculationException {
        Double aggregatedPrice = repo.aggregatePriceForAllProduct();
        if(aggregatedPrice == null){
            logger.warn("Aggregated Price is null");
            throw new ProductPriceCalculationException("Product price is null");
        }

        //**************************
        // The value should come from database but for example we will continue with test data with overloader method
        // *************************
        return repo.aggregatePriceForAllProduct();

    }

    public Double calculatePrice(List<ProductDTO> products, Optional<String> filterCriteria) {

        Double totalPrice= null;
        if(!filterCriteria.isPresent()) {
            try {
                totalPrice = getPriceForAllProduct(products);
            } catch (ProductPriceCalculationException e) {
                e.printStackTrace();
            }
            finally {
                logger.info("No product Filter option has been provided by user");
            }
        }
        else {
            totalPrice =   products.stream()
                    .filter(product -> product.getName().equalsIgnoreCase(filterCriteria.get()))
                    .map(productPrices -> productPrices.getPrice())
                    .collect(Collectors.toList()).stream()
                      .mapToDouble(Double::doubleValue).sum();
            System.out.println(totalPrice + " is totalPrice");
        }
        return totalPrice;
    }

    public Double getPriceForAllProduct(List<ProductDTO> products) throws ProductPriceCalculationException {

        if(products == null){
            logger.warn("products list is null");
            throw new ProductPriceCalculationException("Product price is null");
        }
        return products.stream() .map(productPrices -> productPrices.getPrice()).mapToDouble(f -> f).sum();

    }
}
