package com.sdettest;

import com.sdettest.dto.ProductDTO;
import com.sdettest.exceptions.ProductPriceCalculationException;
import com.sdettest.repository.ProductRepository;
import com.sdettest.service.IProductPriceService;
import com.sdettest.service.ProductPriceService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ProductMockedTest {

    @Test
    public void calculatePriceWithFilterCriteria(){

        List<ProductDTO> listOfProducts = new ArrayList<>();
        ProductDTO product1 =  new ProductDTO.ProductBuilder().withName("Cheese").withPrice(1.29d).build();
        listOfProducts.add(product1);
        ProductDTO product2 =  new ProductDTO.ProductBuilder().withName("Cheese").withPrice(1.29d).build();
        listOfProducts.add(product2);
        ProductDTO product3 =  new ProductDTO.ProductBuilder().withName("Bread").withPrice(1.00d).build();
        listOfProducts.add(product3);
        ProductDTO product4 =  new ProductDTO.ProductBuilder().withName("Jam").withPrice(0.99d).build();
        listOfProducts.add(product4);

        //Assert value with filter
        assertThat(new ProductPriceService().calculatePrice(listOfProducts , Optional.of("Cheese")))
                .isEqualTo(2.58);
        assertThat(new ProductPriceService().calculatePrice(listOfProducts , Optional.of("Bread")))
                .isEqualTo(1.00);
        assertThat(new ProductPriceService().calculatePrice(listOfProducts , Optional.of("Jam")))
                .isEqualTo(0.99);
    }

    @Test
    public void calculatePriceWithOutFilterCriteria(){

        List<ProductDTO> listOfProducts = new ArrayList<>();
        ProductDTO product1 =  new ProductDTO.ProductBuilder().withName("Cheese").withPrice(1.29d).build();
        listOfProducts.add(product1);
        ProductDTO product2 =  new ProductDTO.ProductBuilder().withName("Cheese").withPrice(1.29d).build();
        listOfProducts.add(product2);
        ProductDTO product3 =  new ProductDTO.ProductBuilder().withName("Bread").withPrice(1.00d).build();
        listOfProducts.add(product3);
        ProductDTO product4 =  new ProductDTO.ProductBuilder().withName("Jam").withPrice(0.99d).build();
        listOfProducts.add(product4);

        //Assert value with filter
        assertThat(new ProductPriceService().calculatePrice(listOfProducts , Optional.empty()))
                .isEqualTo(4.57);

    }

    // These are Mocked Test for TDD
    @Test
    public void priceWithoutFilter() throws ProductPriceCalculationException {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.aggregatePriceForAllProduct()).thenReturn(1D);
        IProductPriceService productService = new ProductPriceService(productRepository);
        Double productPrice = productService.getPriceForAllProduct();
        assertTrue(productPrice==1D);
    }
    @Test
    public void exceptionPriceWithoutFilter()  {
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.aggregatePriceForAllProduct()).thenReturn(null);
        IProductPriceService productService = new ProductPriceService(productRepository);

        try{
            Double productPrice = productService.getPriceForAllProduct();
            fail("Product price is null");
        }
        catch (Exception ex){
            assertEquals("Product price is null" , ex.getMessage());
        }
    }





}
