package com.example.demo.service;

import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {
    Integer save(UserDTO userDTO);

    List<UserDTO> getDetailUser(Integer id);

    void deleteUser(int id);

    List<UserDTO> getUserByUserName(String userName);

    List<UserDTO> getAll();

    List<UserDTO> getByStatus(Boolean status);

    List<UserDTO> getUserByRole(String roleName);


}
