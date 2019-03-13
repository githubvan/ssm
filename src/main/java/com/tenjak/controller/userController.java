package com.tenjak.controller;

import com.tenjak.pojo.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface userController {
    public void add(User user);
    public void delete(String userId);
    public void update(User user);
    public User findOne(String uid);
}
