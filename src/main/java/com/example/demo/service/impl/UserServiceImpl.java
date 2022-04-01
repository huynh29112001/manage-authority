package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Auth;
import com.example.demo.entity.User;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthRepository authRepository;

    @Override
    public Integer save(UserDTO userDTO) {
        User user = new User();
        try{
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setFullname(user.getFullname());
            user.setStatus(userDTO.getStatus());
            user.setPassword(userDTO.getPassword());
            List<Auth> listAuth = new ArrayList<>();
            for (Integer index:
                 userDTO.getAuth_id()) {
                listAuth.add(authRepository.findById(index).orElse(null));
            }
            user.setListAuth(listAuth);
        }
        catch (DataNotFoundException dataNotFoundException){
            System.out.println("error");
        }
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDTO> getDetailUser(Integer id) {
        return userRepository.findById(id).map(userMapper::toDTO).stream().collect(Collectors.toList());
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getUserByUserName(String userName) {

        return userRepository.findByUsername(userName).stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getByStatus(Boolean status) {
        return userRepository.findByStatus(status).stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUserByRole(String roleName) {
        return userRepository.getUserByRole(roleName).stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
}
