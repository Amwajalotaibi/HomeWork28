package com.example.homeworke28.Service;

import com.example.homeworke28.Model.Myorder;
import com.example.homeworke28.Model.Product;
import com.example.homeworke28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getProduct(Integer userId) {
        return productRepository.findProductByUserId(userId);

    }
    public void addProduct(Product product) {

       productRepository.save(product);
    }

    public void deleteProduct(Integer userId, Integer productId){
        Product product=productRepository.findProductById(productId);

        if(product.getUserId()!=userId){
            throw new UsernameNotFoundException("Error,authentication");
        }
        productRepository.save(product);
    }

    public void updateProduct(Integer userId,Product product, Integer productId){
        Product oldProduct=productRepository.findProductById(productId);
        if (oldProduct==null) {
            throw new UsernameNotFoundException("Todo not found");
        } if(oldProduct.getUserId()!=userId){
            throw new UsernameNotFoundException("Error, authentication");
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        productRepository.save(oldProduct);
    }


    public Product getProductById(Integer id) {
        Product product=productRepository.findProductById(id);

        if (product == null) {
            throw new ArithmeticException("ID not found");

        }
        return product;
    }
}
