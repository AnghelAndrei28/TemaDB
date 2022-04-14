package com.dbma.thymeleaf.account;

import com.dbma.thymeleaf.user.Users;
import com.dbma.thymeleaf.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byUserId/{userId}")
    public List<AccountWithUser> getAccountByUserId(@PathVariable("userId") int userId) {
        List<Account> accounts = new ArrayList<>();
        List<AccountWithUser> accountWithUsers = new ArrayList<>();
        for (Account o : accountRepository.findAccountById(userId)) {
            List<Account> full = accountRepository.findAccountByAccountId(o.getAccountId());
            List<Users> users = usersRepository.findUsersById(o.getId());
            accountWithUsers.add(new AccountWithUser(users.get(0), full.get(0)));
        }
        return accountWithUsers;
    }
}
