package com.dbma.thymeleaf.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    List<Users> findUsersById(int id);

    List<Users> findUsersByFullName(String fullName);
}