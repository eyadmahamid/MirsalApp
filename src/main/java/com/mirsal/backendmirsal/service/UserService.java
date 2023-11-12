package com.mirsal.backendmirsal.service;

import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.model.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO get(Long id) throws UnauthorizedException;
    void delete(Long id , UserDeletionReqDTO userDeleteReqDTO ) throws UnauthorizedException;
    UserRespoDTO signup(UserReqDTO req) throws NotFoundException;
    UserRespoDTO signin(UserSigninReqDTO req) throws NotFoundException;

    UserRespoDTO update(Long id, UpdateUserReqDTO req) throws NotFoundException;
}




/* return user data of a specific user by his ID */
// get_user_by_id(Long id){}


    /* register new user: validate user input (valid email, username, phone-number)
    if all valid, then add user to users list. */
// signup(User user){}

// *check if user information is valid (email, username and phone number)
// public boolean isValidInput(User user){}

// *register the new user and add to users_list
// create_user(User user){}


/* Accepts user information (email/phone number) and returns a token after successful authentication. */
// signin(User user){}

// get_all_users(){}

/**
 * @UserService is an interface that defines a contract for the services related to user management.
 * It declares the methods that need to be implemented in the service layer.
 * It contains method signatures that represent the various operations you want to perform on user data,
 * such as creating users, updating user information, retrieving user details, or deleting users.
 *
 * This interface is used to establish a clear separation of concerns in your application.
 * The controllers (or other parts of your application) interact with the UserService interface rather than directly with the service implementation.
 * This decouples the controller from the specific implementation, making it easier to switch implementations or perform unit testing.
 */
