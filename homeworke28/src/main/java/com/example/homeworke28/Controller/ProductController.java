package com.example.homeworke28.Controller;

import com.example.homeworke28.Model.MyUser;
import com.example.homeworke28.Model.Product;
import com.example.homeworke28.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(@AuthenticationPrincipal MyUser myUser){ //هذي عشان نقدر نوصل للايدي بشكل كامل
        List<Product> productList=productService.getProduct(myUser.getId());
        return ResponseEntity.status(200).body(productList);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        productService.addProduct( product);
        return ResponseEntity.status(200).body("Product Added");
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer productId){
        productService.deleteProduct(myUser.getId(), productId);
        return ResponseEntity.status(200).body(" Product delete");
    }

    @PutMapping ("/update/{productId}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal MyUser myUser,@RequestBody Product product,@PathVariable Integer productId){
        productService.updateProduct(myUser.getId(), product,productId);
        return ResponseEntity.status(200).body("Product Update ");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getPriductById(@PathVariable Integer id){
        return ResponseEntity.status(200).body("get Bolg By Id"+productService.getProductById(id));
    }
}
