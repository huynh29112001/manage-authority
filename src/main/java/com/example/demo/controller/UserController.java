package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Integer> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDTO));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getAll());
    }

    @GetMapping("/user/")
    public ResponseEntity<List<UserDTO>> getInfo(@RequestParam Optional<Integer> id, @RequestParam Optional<Boolean> status, @RequestParam Optional<String> roleName, @RequestParam Optional<String> userName){
        List<UserDTO> resList = new ArrayList<>();
        if(id.isPresent()) resList = userService.getDetailUser(id.get());
        else if(status.isPresent()) resList = userService.getByStatus(status.get());
        else if(roleName.isPresent()) resList = userService.getUserByRole(roleName.get());
        else if(userName.isPresent())resList = userService.getUserByUserName(userName.get());
        else resList = userService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(resList);
    }
}
