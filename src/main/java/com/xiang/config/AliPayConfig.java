package com.xiang.config;

import org.springframework.stereotype.Component;

@Component
public class AliPayConfig
{
    //商品appId
    public static String APPID="2021000117626663";
    //生成的私钥
    public static String RSA_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEdTPfkIlvBMrXz03oefX+md6pX66ui8imFwM5t3cRBtDQswzd34ovv5tqxF3NamTHeCPFelAmhXYLulKECnc2htRepK9AtKp1AUm7eG2WSLrPnGwa/ZKF62IHXzJHitk6dSDuzFpxs6lOmTYbhxhRYJMG1HHoEb2L+pTqSLf9Pz8o2nLUvBPoxEUU7jBmjyuuXpatmwaUnoo/4V+fFttngaBldZkxPaZCcPpsPDEvOllm2LHIE0CNRyq1sVmehSJ4IHGrfw8Wa41a/Xl97RAw6oJD2gViIaqyIfQsLJlD9gxYmLZ6iwYQuxzuVKPVkPsi6adky7MHLYTor8CJpXsZAgMBAAECggEAESEC60dKroOlI5FYVtbmu9z0EV0hPssu0GOrdBxIlngWKtbstAiDWzdhBsaMBtNaYv0jSxdNJB4gLNYUc6TS08dN0Utkqm5ymJzpBwhEy6luFwj4ZekiuJS+4Ysq+lt3Sfr5nkfo7kk4fMSNXbPzrhpaCQkSDdu1clOeTEnJGUOTHP+CehiVrQD3fmVw1wFCthlMQadaVvUbOBt5DoVaU+OFIdpoX2QHxSMmWb0RlBklJJHodvhKPdShWlpojXJv6rtsuyn+y+6qrUd0oqLgh0coAwOM/VXELpb3m2Unhq7ilLGX+y+f83MYzZfSSJf8+hid0H/alqpssswDoZp+gQKBgQDdWC30BJzhbJFMnMJr0wENrqG02CTpXIzNB3e/aXG2tJNkkcI3m0ZTOVoIkPJbw7/g96ruGsYiprzLtGKdFqPeC8yHw284powxYW/Irb72IqOvRnyo+MXpkrH/foJGlIADMh2HWqgGxjmDGtt9X2fT9oBwOFmVMPHkgtS6PBGnaQKBgQCZMlAE2eBvXRjjKbJM6j6nCPNVb37+uzZf0MDTtjjEXPewDN2DLnds22O+wCZ7hTFF2NbrbrDKtU7MYiBnDQARIJ64nzZKrHuhFsJX2jFzIUk7YNFS1uy62/tSlFRMGvQcEoJpys6S0TnK3E4Mn1qsp0XoJmJgVfnLrVUyaoLwMQKBgQCJv0Ox1WEQRLFT3UZ9Na41JpWJ5u1psOgK/HfATk2vJ0eG0dLtKhuk1U87f4DkLLjvtxHP5ieiDuP1XYf1PM6z8+HQJ4bC2ZrrbDjVyZLKuJnnDVWtDkyUZG+TkdZfODFGS9zmjDt/I8NM3Nz1c/c4iPI7ZEYTu4b975GVaKaP6QKBgBKmJ0uP4lxsLt1odAYc9eeLlWOvIPyNeb8MQI0FP7/XuWc7CbQwceVUh73u0kBzJF5mWTNr1CW5EJCc2WjyEjIDkI7VAtzwRlSD2sCrFR34zVtnG9RDyamtodiZqfFhzpmkPy/1ulPxGGHzk6TBuV4PNtld4tfrX29F+HscsDfhAoGARFn/ZmuaZUiqMhme/JTDMVntvBYbb9p6zN8hn1a1xQA30drfl5Ia8A4BeL4BcAHFttXaiTV2LpQfH1wO1cJgDhT6q2b3KdmKJ7XfddCUXowlBU2fV3PobsDd+s4LGv3FGy+OB91jS87BTNLKe4CbHO2pm+O8srQ186JYfOJrYJA=";
    //服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，且需要公网地址(这里以百度为例)
    public static String notify_url="http://169.254.217.225:8080/notify";
    public static String return_url="http://localhost:8080/payreturn";
    //请求网关地址
    public static String URL="https://openapi.alipaydev.com/gateway.do";
    //编码
    public static String CHARSET="UTF-8";
    public static String FORMAT="json";
    //支付宝公钥
    public static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnQRVXiaa8kUCLBlddvlY25LqacgsNjMPbExMe9+Vpac7k5RwTKoaURLXkB4/3SkB++2RG4UmmE9kHGFuJqWUYEOJNb4htAtKAsEm50VSQRRyLp+AZbtuFdv1G2WHHe1385nDNy81z+wUDM3n7TSEJC6dCxgtHrdGA2z4w9nu9XmsOyQMqthvvid6NwF0K5XcA5f3Att867Y3n6i/Eal8TT5CZ9CUFQbXE9QS6dVw2/mkRD1dIsaJ/wukl6KQ5yrZzF1lo5kIFlpeZtpZlLl1i5IGkj0NbTaK73Pw6CyUAo6SXtIqQkdmYJIuIULioNimTy9D9vamifhLM05cuFuLxwIDAQAB";
    //RSA2
    public static String SIGNTYPE="RSA2";
}

