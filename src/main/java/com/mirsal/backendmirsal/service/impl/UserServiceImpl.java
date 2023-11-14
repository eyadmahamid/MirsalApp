package com.mirsal.backendmirsal.service.impl;

import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.exceptions.UserNotFoundException;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.mapper.Impl.UserMapper;
import com.mirsal.backendmirsal.repository.UserRepo;
import com.mirsal.backendmirsal.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final  UserMapper userMapper;

    @Override
    public void delete(Long id )throws UnauthorizedException {
        Optional<User> userById = this.userRepo.findById(id);
        if (userById.isEmpty()) {
            throw new UnauthorizedException("User Not Found with ID: " + id);
        }
        User user = userById.get();
        if (!user.isActive()) {
            throw new UnauthorizedException("User is not active and cannot be deleted.");
        }
        user.setDeletedAt(LocalDateTime.now());
        userRepo.deleteById(id) ;
    }

    @Override
    public UserDTO get(Long id) throws UnauthorizedException {
        Optional<User> userById = this.userRepo.findById(id);
        if(userById.isPresent()){
            User user = userById.get();
            return this.userMapper.toDTO(user);
        }
        throw new UnauthorizedException("user Not Found with ID: " + id);
    }


    @Override
    public UserRespoDTO signup(UserReqDTO req) throws UserNotFoundException {
        String email = req.getEmail();
        Optional<User> userByEmail = this.userRepo.findByEmail(email);
        if(userByEmail.isPresent()){
            throw new UserNotFoundException("this E-mail is already exists" + email);
        }
        User user = this.userMapper.toEntity(req);
        user.setActive(true);
        User userToCreate = this.userRepo.save(user);
        return this.userMapper.toRespDTO(userToCreate);
    }

    @Override
    public UserRespoDTO signin(UserSigninReqDTO req) throws UserNotFoundException {
        req.setEmailOrUsername();
        String password = req.getPassword();

        User user = this.userRepo.findByEmailOrUsername(req.getEmail(), req.getUsername());

        if (user == null || !password.equals(user.getPassword())) {
            throw new UserNotFoundException("Invalid E-mail/username or password");
        }

        user.setActive(true);
        return this.userMapper.toRespDTO(user);
    }


    @Override
    public UserRespoDTO update(Long id, UpdateUserReqDTO req) {
        Optional<User> checkIfUserExists = this.userRepo.findById(id);
        if (checkIfUserExists.isEmpty()) {
            // throw an exception or return an appropriate response indicating the error.
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        User user = this.userMapper.toEntity(req);
        User userToCreate = this.userRepo.save(user);
        return this.userMapper.toRespDTO(userToCreate);
    }

}


/**
 *  @UserServiceImpl (Service Implementation):
 * The UserServiceImpl class is the concrete implementation of the @UserService interface.
 * It provides the actual logic and code to fulfill the requirements defined in the interface.
 * It contains the method implementations that carry out the actual business logic for managing users.
 *
 * Inside UserServiceImpl, you would write the code to perform tasks like creating users, updating user data
 * , fetching user information, and any other user-related operations.
 * This is where you put the application's business logic for user management.
 */
