package com.xiang;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xiang.service.authservice;
import com.xiang.service.userservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;

@SpringBootTest
class ProviderApplicationTests {
@Autowired
 authservice authservice;
@Autowired
RedisTemplate redisTemplate;



static  int a=1;
@Test
void  b()
{

    ProviderApplicationTests tests = new ProviderApplicationTests();
    ProviderApplicationTests tests1 = new ProviderApplicationTests();
    tests.a=20;
    tests1.a=30;
    System.out.println(ProviderApplicationTests.a);


}
    @Test
    void contextLoads() {
  redisTemplate.opsForValue().set("name","向禄");
        System.out.println(redisTemplate.opsForValue().get("name"));
//        redisTemplate.getConnectionFactory().getConnection(); 客户端
    }
@Test
    void a() throws WriterException, IOException {
String url="https://www.baidu.com";
    QRCodeWriter qrCodeWriter=new QRCodeWriter();
    //现在是一个图片了 第一个参数是内容，第二个参数生成的格式比如二维码，条形码
    BitMatrix encode = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 500, 500);

    File a=new File("d:");
    Path path=a.toPath();
//    System.out.println(path);
//    Path path1 = FileSystems.getDefault().getPath("d:");
//    System.out.println(path1);

    //把图片写到硬盘
    MatrixToImageWriter.writeToPath(encode,"png",path);


}
}
