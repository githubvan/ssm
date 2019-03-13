package com.tenjak.dao;

import com.tenjak.pojo.User;
import org.springframework.stereotype.Repository;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.PriorityQueue;
public interface UserDao {
     void add(User user);
     void delete(String uid);
     void update(User user);
     User findOne(String uid);
      List<User> findAll();


}
