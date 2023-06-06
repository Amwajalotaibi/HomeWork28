package com.example.homeworke28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "username not Empty")
//    @Column(columnDefinition = "varchar(20) not null")
    private  String username;
//
//   @NotEmpty(message = "password not Empty")
//   @Column(columnDefinition = "varchar(10) not null")
    private String password;

//    @NotEmpty(message = "role can't be empty")
    @Column(columnDefinition = "varchar(20) not null check(role ='customer' or role='admin')")
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "myUser")
    private Set<Myorder> myorders;
   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {

       return true;
    }

    @Override
    public boolean isAccountNonLocked() {

       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

       return true;
    }

    @Override
    public boolean isEnabled() {

       return true;
    }

}
