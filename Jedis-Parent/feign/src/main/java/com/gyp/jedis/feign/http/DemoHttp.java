package com.gyp.jedis.feign.http;

import io.github.admin4j.http.HttpRequest;
import io.github.admin4j.http.util.HttpUtil;
import okhttp3.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DemoHttp {

    public void get() {
        HttpUtil.get("https://baidu.com");

        HttpRequest.get("").queryMap("key", "value")
                .header("key", "value")
                .execute();
    }

    public void post() {
        // JSON 格式的body
        HttpUtil.post("", new HashMap<>());

        HttpRequest.post("").body("");

    }

    public void postForm() {
        Response response = HttpUtil.postForm("", new HashMap<>());

    }

    public void down() {
        HttpUtil.down("https://gitee.com/admin4j/common-http,path/");
    }

    public void upload() {
        File file = new File("C:\\\\Users\\\\andanyang\\\\Downloads\\\\Sql.txt");
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("file", file);
        Response response = HttpUtil.upload("https://upload.qiniup.com/", formParams);
        System.out.println(response);
    }

}
