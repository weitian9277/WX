package com.qf.controller;

import com.qf.util.CheckoutUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname WeChatActionController
 * @Description TODO
 * @Date 2020/10/16 23:00
 * @Created by lenovo
 */
@Controller
@RequestMapping("/wxGzh")
public class WeChatActionController {
    /**
     * 微信消息接收和token验证
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/weChatToken")
    public void weChat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    PrintWriter print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
