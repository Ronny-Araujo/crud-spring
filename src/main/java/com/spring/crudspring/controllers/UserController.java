package com.spring.crudspring.controllers;

import java.util.List;
import java.util.Optional;

import com.spring.crudspring.model.User;
import com.spring.crudspring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    public String primeiraUrl(@PathVariable String name) {

        User user = new User();
        user.setName(name);
        user.setAge(31);
        userRepository.save(user);

        return "crud-spring salvo user " + name;
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            System.out.println(user);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<User> save(@RequestBody User user) {

        User userSave = userRepository.save(user);

        return new ResponseEntity<User>(userSave, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long id) {

        userRepository.deleteById(id);

        return new ResponseEntity<String>("User deleted", HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid")
    @ResponseBody
    public ResponseEntity<User> findById(@RequestParam Long id) {

        Optional<User> user = userRepository.findById(id);

        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<User> update(@RequestBody User user) {

        User userUpdate = userRepository.save(user);

        return new ResponseEntity<User>(userUpdate, HttpStatus.OK);
    }

    @GetMapping(value = "/findbyname")
    @ResponseBody
    public ResponseEntity<List<User>> findByName(@RequestParam String name) {

        List<User> users = userRepository.findByName(name.trim().toUpperCase());

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
