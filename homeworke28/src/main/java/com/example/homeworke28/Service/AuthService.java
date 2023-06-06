package com.example.homeworke28.Service;

import com.example.homeworke28.Model.MyUser;
import com.example.homeworke28.Model.Product;
import com.example.homeworke28.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    public List<MyUser> getAllUser(){

        return authRepository.findAll();
    }


    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("CUSTOMER");
        authRepository.save(myUser);
    }

    public MyUser getMyuserById(Integer id) {
        MyUser myUser=authRepository.findMyUserById(id);
        if (myUser == null) {
            throw new ArithmeticException("ID not found");

        }
        return myUser;
    }

}
