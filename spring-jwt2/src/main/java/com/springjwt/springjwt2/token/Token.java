package com.springjwt.springjwt2.token;


import com.springjwt.springjwt2.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
@Validated
@ApiModel("this documentation for token details")
public class Token {

  @Id
  @GeneratedValue
  @ApiModelProperty("this is token id")
  public Integer id;

  @Column(unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REMOVE})
  @JoinColumn(name = "user_id")
  public User user;
}
