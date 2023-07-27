package com.javaguides.com.springbootrestapi.controller;

import com.javaguides.com.springbootrestapi.dto.UserDto;
import com.javaguides.com.springbootrestapi.entity.User;
import com.javaguides.com.springbootrestapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name="CRUD REST APIs FOR USER RESOURCE",
        description ="CRUD REST APIs - Create User,Update User,Get User,Get All Users,Delete User"
)
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create User Rest Api",
            description = "Create User Rest Api is used to save user in a database"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, BindingResult result){
        UserDto userSaved=userService.createUser(userDto);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get UserById Rest Api",
            description = "Get UserById Rest Api is used to get a single user from  database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )

    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUserById( @PathVariable("id")long id){
       UserDto user= userService.getUserByid(id);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get ALL Users Rest Api",
            description = "Get ALL Users Rest Api is used to get ALL THE USERS  from  database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )

    @GetMapping
    public ResponseEntity<List<UserDto>>getAllUsers(){
        List<UserDto>users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(
            summary = "Update User Rest Api",
            description = "Update User Rest Api is used to update a  particular User in the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )

    @PutMapping("/{id}")
    public ResponseEntity<UserDto>updateUser(@Valid @PathVariable ("id")long id,@RequestBody UserDto user){
         UserDto dto= userService.updateUser(id,user);
         return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User Rest Api",
            description = "Delete User Rest Api is used to update a  particular User in the database"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable ("id")long id){
       userService.deleteUser(id);
       return new ResponseEntity<>("UserEntity Deleted !!", HttpStatus.OK);
    }
}
