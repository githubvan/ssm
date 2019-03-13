package com.tenjak.service;

import com.tenjak.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface userService {
    public void add(User users);
    public void delete(String uid);
    public void update(User user);
    public User findOne(String uid);
    public List<User> findAll();

}
