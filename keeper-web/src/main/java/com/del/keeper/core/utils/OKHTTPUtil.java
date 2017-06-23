package com.del.keeper.core.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 发送HTTP、HTTPS请求 Created by xie
 */
@Component
@Scope("prototype")
@SuppressWarnings("unused")
public class OKHTTPUtil {

    private static final Logger log = LoggerFactory.getLogger(OKHTTPUtil.class);
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * get
     *
     * @param url
     * @return
     * @throws IOException
     */
    String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            log.warn(response.toString());
            throw new IOException("HTTP请求失败：" + response);
        }
    }
}
