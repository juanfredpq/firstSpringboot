package com.example.tarea.controller;


import com.example.tarea.entity.User;
import com.example.tarea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/v1/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable  Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path="/v1/add")
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String lastName) {

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        userRepository.save(user);
        return "Saved";
    }


    @GetMapping("/v1/users")
    public ResponseEntity<List<User>> getExistingUsers(){

        Iterable<User> users = userRepository.findAll();
        List<User> result = StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
        if(users == null ){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PutMapping("/v1/user/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user , @PathVariable long id) {

        Optional<User> studentOptional = userRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        user.setId(id);

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/v1/user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

        Optional<User> studentOptional = userRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();


        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
