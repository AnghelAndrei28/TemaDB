package com.dbma.thymeleaf.user;

import com.dbma.thymeleaf.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        try {
            usersRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public List<Users> getUser(@PathVariable("id") Integer id) {
        List<Users> usersList = new ArrayList<>();
        for (Users o : usersRepository.findUsersById(id)) {
            usersList.add(o);
        }
        return usersList;
    }

    @GetMapping("/byName/{fullName}")
    public List<Users> getUserByName(@PathVariable("fullName") String fullName) {
        List<Users> usersList = new ArrayList<>();
        for (Users o : usersRepository.findUsersByFullName(fullName)) {
            usersList.add(o);
        }
        return usersList;
    }

    @GetMapping("/all")
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        for (Users o : usersRepository.findAll()) {
            usersList.add(o);
        }
        return usersList;
    }

    @GetMapping("/initials")
    public List<String>  getInitials () {
        return userService.getInitials(getAllUsers());
    }

    @GetMapping("/gmail")
    public long  gmailUsers () {
        return userService.noGmailUsers(getAllUsers());
    }

    @GetMapping("/lastname")
    public List<String>  distinctLastName () {
        return userService.lastNamesList(getAllUsers());
    }

    @GetMapping("/stringinitials")
    public Optional<String> stringInitials () {
        return userService.initialsString(getAllUsers());
    }

    @GetMapping("/under20")
    public long under20Users () {
        return userService.noUnder20Users(getAllUsers());
    }
}
