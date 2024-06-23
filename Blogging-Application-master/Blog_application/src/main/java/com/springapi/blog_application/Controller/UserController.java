package com.springapi.blog_application.Controller;

import com.springapi.blog_application.ApiResponse.Apiresponse;
import com.springapi.blog_application.Payloads.UserDto;
import com.springapi.blog_application.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
        UserDto newUser = userService.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        UserDto  userUpdate = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

     @DeleteMapping("/{userId}")
    public ResponseEntity<Apiresponse> deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity(new Apiresponse("User deleted Successfully", true), HttpStatus.OK);
     }

     @GetMapping("/allUser")
     public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> alluser = userService.getAllUsers();
        return new ResponseEntity<>(alluser, HttpStatus.OK);
     }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId){
        UserDto singleUser = userService.getUserById(userId);
        return new ResponseEntity<>(singleUser, HttpStatus.OK);
    }
}
