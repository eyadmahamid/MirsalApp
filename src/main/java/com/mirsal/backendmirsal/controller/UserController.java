package com.mirsal.backendmirsal.controller;

import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.exceptions.UserNotFoundException;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; //bean

    //get user by id

//    public ResponseEntity<User> getUser(Long id) {
//        return ResponseEntity.ok(userService.get_user_by_id(id));
//    }
@GetMapping(value = "/user/{id}")
public ResponseEntity<?> get(@PathVariable Long id) {

    try {
        UserDTO userDTO = this.userService.get(id);
        return ok(userDTO);

    } catch (UnauthorizedException e) {
        throw new RuntimeException(e);
    }
}


    @DeleteMapping("user/{id}")
    public void delete(@PathVariable Long id) {
        try {

            this.userService.delete(id);


        }catch (UnauthorizedException e){


        }


    }




//    @PutMapping("user/{id}")
//    public UserRespoDTO update(@Valid Long id, UpdateUserReqDTO req){
////        return this.userService.update(id, req);
//    }
//
//

//     Register a new user with email and phone number.
//     Accepts user registration data (email, phone number) and creates a new user account.


    @PostMapping(value = "user/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserReqDTO user) {
        try {
            UserRespoDTO addAcount = this.userService.signup(user);
            return ok(addAcount);
        } catch (UserNotFoundException e) {
            return badRequest().body(e.getMessage());
        }
    }



    // Authenticate a user by email/phone number.
    @PostMapping("user/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody UserSigninReqDTO user) throws UserNotFoundException {
            String checkEmailOrUsername = user.getEmailOrUsername();
            String password = user.getPassword();
        try{

          UserRespoDTO login =  userService.signin(user);
            return ResponseEntity.ok(login);
        }catch (UserNotFoundException e){
                return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}
