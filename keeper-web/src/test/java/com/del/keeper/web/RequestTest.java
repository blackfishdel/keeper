package com.del.keeper.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:http2.properties")
public class RequestTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    
    String cookie;
    String tracecode1;
    String tracecode2;

    public void request1() {
        String url = "https://passport.baidu.com/passApi/js/wrapper.js";
        OkHttpClient okHttpClient = new OkHttpClient();
        
        String time = sdf.format(new Date(System.currentTimeMillis()));
        RequestBody body = new FormBody.Builder().add("cdnversion", time).build();
        
        Request request = new Request.Builder().url(url)
                .header("Accept","*/*")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.8")
                .header("Connection","keep-alive")
                .header("Host","passport.baidu.com")
                .header("Referer","http://baijiahao.baidu.com/builder/app/login")
                .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36")
                .post(body)
                .build();
        
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.headers().toString());
            System.out.println(response.body().toString());
            cookie = response.header("Set-Cookie");
            String[] s = cookie.split(";");
            cookie = s[0];
            tracecode1 = response.header("Tracecode");
            tracecode2 = response.header("Tracecode");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void request2() {
        String url = "https://passport.baidu.com/static/passpc-account/js/module/fingerprint.js";
        OkHttpClient okHttpClient = new OkHttpClient();
        
        RequestBody body = new FormBody.Builder().add("cdnversion", "1498462697482").build();
        
        Request request = new Request.Builder().url(url)
                .header("Accept","*/*")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.8")
                .header("Connection","keep-alive")
                .header("Host","passport.baidu.com")
                .header("Referer","http://baijiahao.baidu.com/builder/app/login")
                .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36")
                .post(body)
                .build();
        
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.headers().toString());
            System.out.println(response.body().toString());
            cookie = response.header("Set-Cookie");
            String[] s = cookie.split(";");
            cookie = s[0];
            tracecode1 = response.header("Tracecode");
            tracecode2 = response.header("Tracecode");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request3() {
        String url = "https://passport.baidu.com/v2/api/";
        OkHttpClient okHttpClient = new OkHttpClient();
        
        RequestBody body = new FormBody.Builder().add("cdnversion", "201706261448").build();
        
        Request request = new Request.Builder().url(url)
                .header("Accept", "*/*")
                .header("Accept","*/*")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.8")
                .header("Connection","keep-alive")
                .header("Cookie",cookie)
                .header("Host","passport.baidu.com")
                .header("Referer","http://baijiahao.baidu.com/builder/app/login")
                .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36")
                .post(body).build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.headers().toString());
            System.out.println(response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request4() {
        String url = "https://passport.baidu.com/passApi/js/wrapper.js";
        OkHttpClient okHttpClient = new OkHttpClient();
        
        RequestBody body = new FormBody.Builder().add("cdnversion", "201706261448").build();
        
        Request request = new Request.Builder().url(url)
                .post(body).build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.headers().toString());
            System.out.println(response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void requests() {
        request1();
        request2();
        request3();
        request4();
    }
    
    public static void main(String[] args) {
        long time = (System.currentTimeMillis()/36000)*315;
        System.out.println(time);
    }
}
