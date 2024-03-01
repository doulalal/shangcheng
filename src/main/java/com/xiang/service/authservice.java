package com.xiang.service;

import com.xiang.dao.authmapper;
import com.xiang.pojo.authority;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class authservice {
@Autowired
 public authmapper authmapper;

public  void  insert(String username)
{
    authority authority = new authority(username,"ROLE_vip");
    authmapper.insert(authority);

}
}
