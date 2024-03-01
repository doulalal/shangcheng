package com.xiang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class eamil {
    @Autowired
    JavaMailSenderImpl javaMailSender;
    public  void Send(String msg)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("bug-bug-bug");
        message.setText(msg);
        message.setFrom("2362910750@qq.com");
        message.setTo("2362910750@qq.com");
        javaMailSender.send(message);

    }
}
