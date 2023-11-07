package com.mirsal.backendmirsal.controller;

import com.mirsal.backendmirsal.model.dto.UpdateUserReqDTO;
import com.mirsal.backendmirsal.model.dto.UserDTO;
import com.mirsal.backendmirsal.model.dto.UserReqDTO;
import com.mirsal.backendmirsal.model.dto.UserRespoDTO;
import com.mirsal.backendmirsal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; //bean

    //get user by id
    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(Long id) {
//        return ResponseEntity.ok(userService.get_user_by_id(id));
//    }
    public UserDTO get(@PathVariable Long id){
        return this.userService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.userService.delete(id);
    }


    @PostMapping("/create")
    public UserRespoDTO signup(UserReqDTO dto){

        return this.userService.signup(dto);
    }

    @PutMapping("/{id}")
    public UserRespoDTO update(Long id, UpdateUserReqDTO req){
        return this.userService.update(id, req);
    }



    // Register a new user with email and phone number.
    // Accepts user registration data (email, phone number) and creates a new user account.


//    @PostMapping("users/signup")
//    public ResponseEntity<String> signup(@RequestBody User user) {
//        return ResponseEntity.ok(userService.signup(user));
//    }
//
//    // Authenticate a user by email/phone number.
//    @PostMapping("users/signin")
//    public ResponseEntity<String> signin(@RequestBody User user) {
//        return ResponseEntity.ok(userService.signin(user));
//    }
//
//
//
//
//    @DeleteMapping("employee/{id}")
//    public void  delete(@PathVariable Long id){
//
//        this.employeeService.delete(id);
//    }



}
