package com.nimai.inventory_management_system.repository;


import com.nimai.inventory_management_system.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
