package com.xiang.service;


import com.xiang.dao.usermapper;
import com.xiang.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userservice {
    @Autowired
    public usermapper usermapper;
    public  void  insert(String username,String password)
    {
        String encode = new BCryptPasswordEncoder().encode(password);
        usermapper.insert(new user(username,encode,"1"));

    }
}
