package com.springjwt.springjwt2.contorller;

import com.springjwt.springjwt2.auth.AuthentectionResponse;
import com.springjwt.springjwt2.auth.AuthenticationRequest;
import com.springjwt.springjwt2.auth.AuthenticationService;
import com.springjwt.springjwt2.auth.RegisterRequest;
import com.springjwt.springjwt2.exception.NotFoundEmailException;
import com.springjwt.springjwt2.exception.NotFoundVerification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Api(tags = "this documentation to make user authentication")
public class AuthenticationController {  // to create or manage new account and authenticate an existeing user


    private final AuthenticationService service;
    // make api register
    @PostMapping("/register")
    @ApiOperation(value = "this operation to make user register in toda app  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<Integer> register(  // authenticationResponse return a token
                                                            @RequestBody RegisterRequest request
    ) throws MessagingException {
      return ResponseEntity.ok(service.register(request));
    }



    // make api register
    @PostMapping("/authenticate")
    @ApiOperation(value = "this operation  to make user login in app after verification your account  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<AuthentectionResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws NotFoundEmailException, NotFoundVerification {
        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/activate/{otp}/{email}")
    @ApiOperation(value = "this operation   to verify user account ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<ResponseEntity<String>> activate(@PathVariable int otp, @PathVariable String email) {



        return  ResponseEntity.ok(service.activate(email,otp));
    }


//    @PutMapping("/verify-account")
//    public ResponseEntity<String> verifyAccount(@RequestParam String email,
//                                                @RequestParam String otp) {
//        return new ResponseEntity<>(service.verifyAccount(email, otp), HttpStatus.OK);
//    }
//    @PutMapping("/regenerate-otp")
//    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
//        return new ResponseEntity<>(service.regenerateOtp(email), HttpStatus.OK);
//    }
}


