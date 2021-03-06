package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    // get product by name ignore case
    Optional<Product> findProductByNameIgnoreCase(String name);

//    List<Product> findByNameContainingIgnoreCase(String name);
//
//    @Query("SELECT DISTINCT brand FROM Product")
//    List<String> findAllBrandsDistincts();

    // get product by name containing ignore case
    List<Product> findAllByNameContainingIgnoreCase(String name);

    // get product by sku
    Optional<Product> findAllByProductDetailSku(String sku);

    // get product by price between
    List<Product> findAllByPriceBetween(BigDecimal priceMin, BigDecimal priceMax);

    // get product by name containing and price between
    List<Product> findAllByNameContainingAndPriceBetween(String name, BigDecimal priceMin, BigDecimal priceMax);

    // get product by category id
    List<Product> findAllByCategoryId(String categoryId);

    // get product by supplier id
    List<Product> findAllBySuppliersId(String supplierId);
}
