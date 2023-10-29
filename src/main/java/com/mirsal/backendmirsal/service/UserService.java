package com.mirsal.backendmirsal.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
}


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
