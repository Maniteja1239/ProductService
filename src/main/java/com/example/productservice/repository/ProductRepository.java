package com.example.productservice.repository;

import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductWithTitleAndImage;
import com.example.productservice.projections.ProductWithTitleAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    void deleteAll();

    @Override
    Product save(Product p);

    //Product updateById();

    @Query("select p.title as title, p.price as price from Product p where p.id=:id")
    ProductWithTitleAndPrice findTitleAndPriceById(@Param("id") Long id);

    @Query(value="select title,image from product where id= :id",nativeQuery = true)
    ProductWithTitleAndImage findTitleAndImageById(@Param("id") Long id);

}
