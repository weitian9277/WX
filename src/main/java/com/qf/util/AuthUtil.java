package com.qf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Classname AuthUtil
 * @Description TODO
 * @Date 2020/10/16 8:07
 * @Created by lenovo
 */
public class AuthUtil {
    public static final String APPID = "wxac0c200afd2b12f4";
    public static final String APPSECRET = "eca0ccd3a0e44ba537c72570926cb1c8";

    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 把返回的结果转换为JSON对象
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSON.parseObject(result);
        }
        return jsonObject;
    }

}
