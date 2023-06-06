package com.example.homeworke28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//   @NotEmpty(message = "name not null")
//   @Column(columnDefinition ="varchar(20) not null" )
    private String name;

//   @NotNull(message = "price not null")
//   @Column(columnDefinition = "int not null")
   private Integer price;

   private Integer userId;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Myorder> myorders;


}
