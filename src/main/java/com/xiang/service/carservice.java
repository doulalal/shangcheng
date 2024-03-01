package com.xiang.service;

import com.xiang.pojo.Order;
import com.xiang.pojo.car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class carservice {
    @Autowired
    RedisTemplate redisTemplate;


    public  void  set(String name,String title,String img,String price,String number,String total){
       redisTemplate.opsForHash().put(name,title,new car(img,title,price,number,total));

    }
   public List<car> get(String name)
    {
        return (List<car>)redisTemplate.opsForHash().values(name);
    }

    public  void  delete(String name,String title)
    {
        redisTemplate.opsForHash().delete(name,title);
    }

    public void setorder(String name, Order order){
        redisTemplate.opsForValue().set(name,order);
    }
    public Order getorder(String name)
    {
        return (Order) redisTemplate.opsForValue().get(name);
    }
    public  void  deorder(String name)
    {
        redisTemplate.delete(name);
    }

}
