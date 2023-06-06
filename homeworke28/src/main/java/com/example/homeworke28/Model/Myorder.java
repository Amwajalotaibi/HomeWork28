package com.example.homeworke28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "myorder")
public class Myorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "quantity not null")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @NotNull(message = "total price not null")
    @Column(columnDefinition = "int not null")
    private Integer totalPrice;

    @NotEmpty(message = "date can't be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String dateReceived;

    @NotEmpty(message = "Status can't be empty")
    @Column(columnDefinition = "varchar(20) not null check(status ='new' or status='inprogress' or status='completed')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;


    @ManyToOne
    @JoinColumn(name = "product_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Product product;


}
