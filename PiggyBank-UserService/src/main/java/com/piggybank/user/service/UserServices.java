package com.piggybank.user.service;

import java.util.List;

import com.piggybank.user.model.UserDTO;

public interface UserServices {
	UserDTO registerUser(UserDTO userDTO);
    UserDTO getUserByUserName(String userName);
    List<UserDTO> getAllUser();
    UserDTO getUserById(long id);
    UserDTO updateUser(long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO createUser(UserDTO userDTO);
	
}