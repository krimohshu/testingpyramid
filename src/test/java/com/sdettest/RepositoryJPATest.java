package com.sdettest;

import com.sdettest.model.Product;
import com.sdettest.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
//Please take com.h2database H2 dependency with testing scope and enable autoconfig
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryJPATest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findProductByName(){
        Product avacado = new Product(10.0 , "avacado");
        testEntityManager.persist(avacado);
        testEntityManager.flush();

        List<Product> foundedProduct = productRepository.findByName(avacado.getName());
        foundedProduct.stream().forEach(product -> System.out.println( product.getName()));
        Assert.assertEquals("avacado" , foundedProduct.get(0).getName());
    }

}
