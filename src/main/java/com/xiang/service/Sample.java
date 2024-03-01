package com.xiang.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.stereotype.Service;

@Service
public class Sample {
    com.aliyun.dysmsapi20170525.Client client;
    public Sample() throws Exception {
        this.client = createClient("accessKeyId", "accessKeySecret");
    }
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
 public void Sendsms(String number,String code) throws Exception {
      SendSmsRequest sendSmsRequest = new SendSmsRequest();
      sendSmsRequest.setPhoneNumbers(number);//手机号
      sendSmsRequest.setSignName("");//签名
      sendSmsRequest.setTemplateCode("");//模板
      sendSmsRequest.setTemplateParam("{\"code\":\""+code+"\"}");//模板参数内容
      // 复制代码运行请自行打印 API 的返回值
      SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);


  }

}
