package com.dbma.thymeleaf.user.services;

import com.dbma.thymeleaf.user.Users;
import com.dbma.thymeleaf.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.thymeleaf.util.StringUtils.containsIgnoreCase;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;
    
    public List<String> getInitials(List<Users> usersList) {
        return usersList.stream().map(Users::getFullName).map((fullName) -> {
            String[] names = fullName.split(" ");
            String initials = "";
            for(String name : names) {
                initials = initials + name.charAt(0);
            }
            return initials;
        }).collect(Collectors.toList());
    }

    public long noGmailUsers(List<Users> usersList) {
        return usersList.stream().filter((user) -> user.getEmail().contains("@gmail.")).count();
    }

    public List<String> lastNamesList(List<Users> usersList) {
        return usersList.stream().map(Users::getFullName).map((fullName) -> {
            String[] names = fullName.split(" ");
            return names[names.length - 1];
        }).distinct().collect(Collectors.toList());
    }

    public Optional<String> initialsString (List<Users> usersList) {
        return getInitials(usersList).stream().reduce((acc, initials) -> acc + initials);
    }

    public long noUnder20Users (List<Users> usersList) {
        return usersList.stream().filter((user) -> user.getAge() < 20).map((user) -> {
            String[] names = user.getFullName().split(" ");
            return names[0];
        }).filter((firstName) -> firstName.toLowerCase(Locale.ROOT).contains("a")).count();
    }
}
