package com.del.keeper.web;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:http2.properties")
public class RequestTest2 {

    @Value("${Accept}")
    private String Accept;

    @Value("${Accept-Encoding}")
    private String acceptEncoding;

    @Value("${Accept-Language}")
    private String acceptLanguage;

    @Value("${Connection}")
    private String connection;

    @Value("${Host}")
    private String host;

    @Value("${Referer}")
    private String referer;

    @Value("${User-Agent}")
    private String userAgent;

    String setCookie;
    String tracecode1;
    String tracecode2;

    @Test
    public void requestCdnversion() {
        String url = "https://passport.baidu.com/passApi/js/wrapper.js";
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("cdnversion", "201706261448").build();
        Request request = new Request.Builder().url(url).header("Accept", Accept)
                .header("Accept-Encoding", acceptEncoding).header("Accept-Language", acceptLanguage)
                .header("Connection", connection).header("Host", host).header("Referer", host)
                .header("User-Agent", userAgent).post(body).build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.headers().toString());
            System.out.println(response.body().toString());

            setCookie = response.header("Set-Cookie");
            tracecode1 = response.header("Tracecode");
            tracecode2 = response.header("Tracecode");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
