package com.xiang.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.xiang.service.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class ssmcontoller {
@Autowired
    Sample sample;
@Autowired
  RedisTemplate redisTemplate;

@RequestMapping("/yanzheng")
    void getyanzheng(@PathVariable("number") String number) throws Exception {

  if (!redisTemplate.hasKey(number))
  {
      Random random=new Random();
      String code=""+(random.nextInt(8999)+1000);
      redisTemplate.opsForValue().set(number,code,1,TimeUnit.SECONDS);
      sample.Sendsms(number,code);
  }

}
    @RequestMapping("/yan")
    void yanzheng(@PathVariable("number") String number,String code) throws Exception {

       String o = (String)redisTemplate.opsForValue().get(number);

          if (o==code){
              //跳转
          }
         else {
             //失败
          }

    }

}
