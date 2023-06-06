package com.example.homeworke28.Service;

import com.example.homeworke28.ApiException.ApiException;
import com.example.homeworke28.Model.MyUser;
import com.example.homeworke28.Model.Myorder;
import com.example.homeworke28.Model.Product;
import com.example.homeworke28.Repository.AuthRepository;
import com.example.homeworke28.Repository.MyorderRepository;
import com.example.homeworke28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyorderService {
    private final MyorderRepository myorderRepository;
    private final ProductRepository productRepository;
    private final AuthRepository authRepository;
    public List<Myorder> getAllMyorder(MyUser myUser){

        return myorderRepository.findMyorderByMyUser(myUser);
    }


    public void addMyorder(Integer productId, Integer userId, Myorder myorder){

        MyUser myUser =authRepository.findMyUserById(userId);
        Product product=productRepository.findProductById(productId);
        if(myUser==null || product==null){
            throw new ApiException("not found");

        }
        myorder.setTotalPrice(product.getPrice()*myorder.getQuantity());
        myorder.setStatus("new");
        myorder.setProduct(product);
        myorder.setMyUser(myUser);

        myorderRepository.save(myorder);

    }



    public void updateMyorder(Integer id,Myorder myorder){
        Myorder A=myorderRepository.findMyorderById(id);
        if (A== null)
            throw new ApiException("Not found");
        A.setDateReceived(myorder.getDateReceived());
        A.setQuantity(myorder.getQuantity());
        A.setStatus(myorder.getStatus());
        A.setTotalPrice(myorder.getTotalPrice());
       myorderRepository.save(A);
    }

    public void deleteMyorder(MyUser myUser,Integer id){
        Myorder A=myorderRepository.findMyorderById(id);
        if (A == null||  A.getStatus().equals("inprogress"))
            throw new ApiException("Not found");
        myorderRepository.delete(A);
    }


    public Myorder getMyorderById(Integer id) {
        Myorder myorder=myorderRepository.findMyorderById(id);
        if (myorder == null) {
            throw new ArithmeticException("ID not found");

        }
        return myorder;
    }

    public void changeorder(Integer userId , Integer MyorderId,String newstatus){
        Myorder  myorder=myorderRepository.findMyorderById(MyorderId);
        MyUser myUser =authRepository.findMyUserById(userId);
        if (myorder==null || myUser==null || myUser.getRole().equalsIgnoreCase("customer")){
            throw new ApiException("not found");
        }
        myorder.setStatus(newstatus);
        myorderRepository.save(myorder);


    }




}
