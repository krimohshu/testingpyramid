package com.sdettest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sdettest.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByPrice(String price);

    @Query("select sum(p.price) from Product p where p.name=:name")
    double queryByNameAndPrice(@Param("name") String name);

    @Query("select sum(p.price) from Product p")
    double queryForSumOfTheColumn();

    @Query("select sum(p.price) from Product p")
    Double aggregatePriceForAllProduct();



}
