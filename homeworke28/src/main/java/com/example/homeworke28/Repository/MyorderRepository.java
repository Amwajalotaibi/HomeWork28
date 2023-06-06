package com.example.homeworke28.Repository;

import com.example.homeworke28.Model.MyUser;
import com.example.homeworke28.Model.Myorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MyorderRepository extends JpaRepository<Myorder,Integer> {
    Myorder findMyorderById(Integer id);

    List<Myorder> findMyorderByMyUser(MyUser  myUser);
}
