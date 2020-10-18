package com.qf.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wxXCX")
public class WXLoginController {

    // 小程序 AppID
    private static final String appid = "wx5cee72417a5f0ee4";
    // 小程序 AppSecret
    private static final String secret = "6f049c9a6be1d2aa90b0d7a3e0ceccfb";

    /**
     * 小程序授权登录
     */
    @RequestMapping(value="/wxLogin")
    @ResponseBody
    public  Map<String,Object> miniLogin(String code){
        Map<String,Object> map=new HashMap<>();

        String params = "appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        String s= null;
        try {
            s = get("https://api.weixin.qq.com/sns/jscode2session?"+params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject(s);

        map.put("msg",jsonObject);
        return map;
    }

    //发起get 请求
    public  static String get(String url) throws Exception {
        String content = null;
        URLConnection urlConnection = new URL(url).openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        connection.setRequestMethod("GET");
        //连接
        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder bs = new StringBuilder();
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                bs.append(l).append("\n");
            }
            content = bs.toString();
        }
        return content;
    }
}