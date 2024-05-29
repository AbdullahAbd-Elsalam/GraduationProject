package com.springjwt.springjwt2.entity;

import com.springjwt.springjwt2.token.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@ApiModel( value = "this is documentaiton for user details ")
@Validated
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "this is the user id for user  ")
    private int id;


    @Column(nullable = false)
    @ApiModelProperty(value = "this is the enabled column to check verified account user or not")
    private boolean enabledd=false;

    @Email(message = "pease enter email")
    @NotBlank(message = "the user filed could not be blank")
    @ApiModelProperty(value = "this is the email for user  ")
    private String email;

    @ApiModelProperty(value = "this is the password for user  ")
    private String password;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "this is the role depend on user     ")
    private Role role;


    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Otp otp;

//    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "user")
//    public Token token;

    // this is return list of role authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
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
