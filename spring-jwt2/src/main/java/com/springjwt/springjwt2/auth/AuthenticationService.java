package com.springjwt.springjwt2.auth;

import com.springjwt.springjwt2.dao.MailBody;
import com.springjwt.springjwt2.dao.OTPRepository;
import com.springjwt.springjwt2.dao.TokenRepository;
import com.springjwt.springjwt2.dao.UserRepository;
import com.springjwt.springjwt2.entity.Otp;
import com.springjwt.springjwt2.entity.User;
import com.springjwt.springjwt2.exception.NotFoundEmailException;
import com.springjwt.springjwt2.exception.NotFoundVerification;
import com.springjwt.springjwt2.service.EmailService;
import com.springjwt.springjwt2.service.JwtService;
import com.springjwt.springjwt2.service.OtpService;
import com.springjwt.springjwt2.token.Token;
import com.springjwt.springjwt2.token.TokenType;
import com.springjwt.springjwt2.util.EmailUtil;
import com.springjwt.springjwt2.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final OtpService otpService;
    private final EmailService emailService;
    private final OTPRepository otpRepository;

    public Integer register(RegisterRequest request) throws MessagingException {

        var user= User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                                 .build();
        var savedUser = userRepository.save(user);
        // create my token
//        var jwtToken= jwtService.generateToken(savedUser);
//        saveUserToken(user, jwtToken);
//
//        return AuthentectionResponse.builder().token(jwtToken).build();

        int otp =  otpUtil.generateOtp(); // Implement OTP generation logic
        MailBody  mailBody=MailBody.builder()
                .to(request.getEmail())
                 .text("this is otp for verification")
                .subject("User registered successfully. Please check your email for OTP")
                .build();
       // emailService.sendSimpleMessage(mailBody);
        saveUserOtp(savedUser,otp);
          sendOtp(request.getEmail(),otp);


        return  otp;

    }


// make login method
    public AuthentectionResponse authenticate(AuthenticationRequest request) throws NotFoundEmailException, NotFoundVerification {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );



        var user = userRepository.findByEmail(request.getEmail());
             if(user.isEnabledd())
             {
                 var jwtToken=jwtService.generateToken(user);
                 saveUserToken(user, jwtToken);
                 return AuthentectionResponse.builder().token(jwtToken).build();
             }

            else  {
                 int otp =  otpUtil.generateOtp(); // Implement OTP generation logic

                 sendOtp(request.getEmail(),otp);
                 throw  new NotFoundVerification("please activate email verify") ;

             }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void saveUserOtp(User user, int otp){
        Otp otpEntity =  Otp.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis()+70*1000))
                .user(user)
                .build();

         otpEntity.setUser(user);
        otpService.saveOTP(otpEntity);
    }

    public void sendOtp(String email, int otp) {

        try {
            emailService.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            // Handle the exception appropriately
            e.printStackTrace();
        }
    }


    public ResponseEntity<String> activate(  String email,  int otp) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Otp> otpOptional = otpRepository.findByOtpAndUserId(otp, user.getId());
            if (otpOptional.isPresent() && otpOptional.get().getExpirationTime().after(Date.from(Instant.now()))) {
                user.setEnabledd(true);
                userRepository.save(user);
                return ResponseEntity.ok("User activated successfully.");
            }
        }
        return ResponseEntity.status(400).body("Invalid OTP or user not found.");

    }




}
