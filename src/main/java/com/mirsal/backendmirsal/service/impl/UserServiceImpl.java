package com.mirsal.backendmirsal.service.impl;

import com.mirsal.backendmirsal.model.dto.UserReqDTO;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.UpdateUserReqDTO;
import com.mirsal.backendmirsal.model.dto.UserDTO;
import com.mirsal.backendmirsal.model.dto.UserRespoDTO;
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
    private UserRepo userRepo;
    private UserMapper userMapper;

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO get(Long id) {
        Optional<User> userById = this.userRepo.findById(id);
        return this.userMapper.toDTO(userById.get());
    }


    @Override
    public UserRespoDTO signup(UserReqDTO req) {
        Optional<User> userByEmail = this.userRepo.findByEmail("fsf");
        if(userByEmail.isPresent()){
            throw new EntityExistsException("User found with email: ");
        }
        User user = this.userMapper.toEntity(req);
        User userToCreate = this.userRepo.save(user);
        return this.userMapper.toRespDTO(userToCreate);
    }

    @Override
    public boolean signin(UserReqDTO req) {
//        Optional<User> userByEmail = this.userRepo.findByEmail(email);
//        if(userByEmail.isEmpty()){
//            throw new EntityNotFoundException("User not found with email: " + email);
//        }
////        User user = userByEmail.get();
////        User toCheck = this.userMapper.toEntity(req);
//        return true;
        return true;
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
