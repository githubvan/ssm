package com.tenjak.service.impl;

import com.tenjak.dao.UserDao;
import com.tenjak.pojo.User;
import com.tenjak.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional()
public class userServiceImpl implements userService{

    @Autowired
    private UserDao userDao;

    //新增用户
    public void add(User users) {
        try {
            userDao.add(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除用户
    public void delete(String uid) {
        try {
            userDao.delete(uid);
          /*  int i=0;
            int x=1/i;*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findOne(String uid) {
        User one = userDao.findOne(uid);
        return one;
    }

    public List<User> findAll() {
        List<User> all = userDao.findAll();
        return all;
    }


}
