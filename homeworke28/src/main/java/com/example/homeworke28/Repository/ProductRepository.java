package com.example.homeworke28.Repository;

import com.example.homeworke28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findProductByUserId(Integer Id);
    Product findProductById(Integer id);
}
