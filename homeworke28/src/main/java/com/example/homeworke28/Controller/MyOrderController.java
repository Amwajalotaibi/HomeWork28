package com.example.homeworke28.Controller;

import com.example.homeworke28.Model.MyUser;
import com.example.homeworke28.Model.Myorder;
import com.example.homeworke28.Service.MyorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/myorder")
@RequiredArgsConstructor
public class MyOrderController {
    private final MyorderService myorderService;

    @GetMapping("/get")
    public ResponseEntity getMyorder(@AuthenticationPrincipal MyUser myUser) {
        List<Myorder> myorderList=myorderService.getAllMyorder(myUser);
        return ResponseEntity.status(200).body(myorderList);
    }

    @PostMapping("/add")
    public ResponseEntity addMyorder(@AuthenticationPrincipal MyUser myUser, @RequestBody Myorder myorder,@RequestBody Integer productId , @RequestBody Integer userId){
        myorderService.addMyorder(productId, userId, myorder);
        return ResponseEntity.status(200).body("My Order Added");
    }

    @DeleteMapping("/delete/{myorderId}")
    public ResponseEntity deletemyorder(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer myorderId){
       myorderService.deleteMyorder(myUser,myorderId);

        return ResponseEntity.status(200).body(" My order delete");
    }

    @PutMapping ("/update/{todoId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser,@RequestBody Myorder  myorder,@PathVariable Integer myorderId){
        myorderService.updateMyorder(myorderId,myorder);
        return ResponseEntity.status(200).body("My Order Update ");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getMyorderById(@PathVariable Integer id){
        return ResponseEntity.status(200).body("get My Order By Id"+myorderService.getMyorderById(id));
    }

    @GetMapping("/chang/{orderId}/{status}")
    public ResponseEntity changStatus(@AuthenticationPrincipal MyUser  myUser,@PathVariable Integer orderId,@PathVariable String status){
        myorderService.changeorder(myUser.getId(),orderId,status);
        return ResponseEntity.status(200).body("Chang Status");
    }
}
