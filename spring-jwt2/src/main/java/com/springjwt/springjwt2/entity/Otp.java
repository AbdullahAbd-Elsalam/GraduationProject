package com.springjwt.springjwt2.entity;

import com.springjwt.springjwt2.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Otp")
@ApiModel( value = "this is documentaiton for Otp details     ")
@Validated
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "this is the  id for otp  ")
    private int id;

    @Column(nullable = false)
    @ApiModelProperty(value = "this is the   otp value which check verification by it  ")
    private int otp;

    @Column(nullable = false)
    @ApiModelProperty(value = "this is the  expirationtime to check validaty for otp  ")
    private Date expirationTime;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "this is the  user_id to which is forienkey of user table  ")
    private User user;


}
