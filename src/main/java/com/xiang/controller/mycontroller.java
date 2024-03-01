package com.xiang.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundApplyModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.xiang.config.AliPayConfig;
import com.xiang.pojo.Order;
import com.xiang.pojo.car;
import com.xiang.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.AuthContext;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
//@DefaultProperties(defaultFallback = "b")//全局服务降级，识别有@HystrixCommand
@Controller
public class mycontroller
{

    @Autowired
    carservice carservice;
    @Autowired
   authservice authservice;
    @Autowired
    userservice userservice;
    @Autowired
    esservice esservice;
    @Autowired
    private eamil eamil;
    @Autowired
    private AliPayConfig aliPayConfig;


    @RequestMapping("/m")

    public String m()
    {
        return "mail";
    }

    @RequestMapping(value = "/return")
    String  returns() throws AlipayApiException {
        AlipayClient client = new DefaultAlipayClient(aliPayConfig.URL,
            aliPayConfig.APPID, aliPayConfig.RSA_PRIVATE_KEY,
            aliPayConfig.FORMAT, aliPayConfig.CHARSET,
            aliPayConfig.ALIPAY_PUBLIC_KEY, aliPayConfig.SIGNTYPE);
        AlipayTradeRefundRequest refundRequest=new AlipayTradeRefundRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        AlipayTradeRefundApplyModel refundApplyModel=new AlipayTradeRefundApplyModel();
        Order order = carservice.getorder(name+"111");
        refundApplyModel.setOutTradeNo(order.getOut_trade_no());
        refundApplyModel.setRefundAmount(order.getTotal_amount());
        refundApplyModel.setOutRequestNo(order.getOut_trade_no());
        carservice.deorder(name+"111");
        refundRequest.setBizModel(refundApplyModel);
        client.execute(refundRequest);

       return "index";
    }

    @RequestMapping(value = "/pay")
    @ResponseBody
    public String pay(HttpServletRequest httpRequest , HttpServletResponse httpResponse)  throws Exception {
        //1.封装Rsa签名方式

        AlipayClient client = new DefaultAlipayClient(aliPayConfig.URL,
                aliPayConfig.APPID, aliPayConfig.RSA_PRIVATE_KEY,
                aliPayConfig.FORMAT, aliPayConfig.CHARSET,
                aliPayConfig.ALIPAY_PUBLIC_KEY, aliPayConfig.SIGNTYPE);

        //2.创建Request请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<car> cars = carservice.get(username);
            float total=0;
        for ( car c:cars
             ) {
            total=total+Float.parseFloat(c.getTotal());
            carservice.delete(username,c.getTitle());
        }
        //封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

        String out_trade_no=new Date().getTime()+""+UUID.randomUUID();
        model.setOutTradeNo(out_trade_no); //订单号
        model.setProductCode("book"); //商品码

        model.setSubject("1");  //主题
        model.setTotalAmount(total+"");    //价格

        //设置请求参数
        request.setBizModel(model);
        //设置异步回调地址
        request.setNotifyUrl(aliPayConfig.notify_url);
        //设置同步回调地址
        request.setReturnUrl(aliPayConfig.return_url);


        //生成表单
        AlipayTradeWapPayResponse execute = client.pageExecute(request);


        String form=execute.getBody();

        //修改订单状态 ...

        return form;                                             //点击支付

    }

    @RequestMapping("/mail")
    public  String send(@RequestParam(value = "msg") String msg)
    {

        eamil.Send(msg);

        return  "index";
    }
    @RequestMapping("/index")
    String index()
    {

        return "index";
    }

    @RequestMapping("/case")
    String cse()
    {

        return "case";
    }
    @RequestMapping("/tologin")
    String toLogin()
    {

        return "login";
    }
    @RequestMapping("/register")
    String register(@RequestParam("username") String username, @RequestParam("password") String password)
    {


        System.out.println(username+"======"+password);
    authservice.insert(username);
    userservice.insert(username,password);

        return "login";
    }



    @RequestMapping("/sou")
    void search(@RequestParam("keyword") String keyword,HttpServletResponse response) throws IOException {
        System.out.println(keyword);
        ArrayList<Map<String, Object>> list = esservice.get(keyword, 1,10);

//        ArrayList<Map<String, Object>> list=new ArrayList<>();
//
//        HashMap<String, Object> map = new HashMap<>();
//        HashMap<String, Object> map1 = new HashMap<>();
//        map.put("title","电脑");
//        map.put("price","1000");
//        map1.put("title","笔记本");
//        map1.put("price","1020");
//        list.add(map);
//        list.add(map1);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(mapper.writeValueAsString(list));
    }
    @RequestMapping("/jiegou")
    void  jie(String title,
              String price,
              String number,
              String img)
    {

        price=price.substring(1);
        System.out.println(price+"111");

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        carservice.set(username,title,img,price,number,""+(Float.parseFloat(price)*Float.parseFloat(number)));


    }
    @RequestMapping("/car")
        String car(Model model) throws IOException {

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<car> cars = carservice.get(username);
         model.addAttribute("msg",cars);
         return  "car";
    }



}
