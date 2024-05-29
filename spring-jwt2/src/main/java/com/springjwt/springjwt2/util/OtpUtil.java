package com.springjwt.springjwt2.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {

  public Integer generateOtp() {
    Random random = new Random();
    return random.nextInt(100_000,999_999);


  }
}
