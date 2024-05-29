package com.springjwt.springjwt2.contorller;

import com.springjwt.springjwt2.entity.User;
import com.springjwt.springjwt2.exception.NotFoundEmailException;
import com.springjwt.springjwt2.exception.TypeNotMatchException;
import com.springjwt.springjwt2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-account")
@Api(tags = "this documentaion to manage user account by updating and deleting")
public class UserController {

    @Autowired
   private UserService userService;


    @ApiIgnore
    @GetMapping("/hi")
    public String getname(){
        return "hello";
    }

    // make delete user
    @DeleteMapping("/user/{email}")
    @ApiOperation(value = "this operation  to make user to delete account  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email) throws NotFoundEmailException {

        userService.deleteUserByEmail(email);
      return   ResponseEntity.ok("acount delete successfuly say");
    }



    // make update user
    @PutMapping("/user/{id}")
    @ApiOperation(value = "this operation  to make user to update your account ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id, @RequestBody User userUpdated) throws TypeNotMatchException {

        userService.updateUserById(id,userUpdated);
        return   ResponseEntity.ok("acount  successfuly updated say");
    }


    // check validate token
    @GetMapping("/checkToken")
    public Boolean checkValidateToken(){

        return true;
    }
}
