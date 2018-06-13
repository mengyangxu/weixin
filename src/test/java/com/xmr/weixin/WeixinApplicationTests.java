package com.xmr.weixin;

import com.alibaba.fastjson.JSONObject;
import com.xmr.util.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeixinApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void getTicket() throws  Exception{
        //获取 token
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "client_credential");
        params.put("appid", "wx75ccd5e549c2adf1");
        params.put("secret", "3e19695ab0ab8c3b25715f250938212c");
        String jstoken = HttpUtils.sendGet(
                "https://api.weixin.qq.com/cgi-bin/token", params);
        String access_token =  JSONObject.parseObject(jstoken).getString(
                "access_token");

        Map<String, String> params2 = new HashMap<String, String>();
        //获取 token 执行体
        params2.put("grant_type", "client_credential");
        params2.put("appid", "wx75ccd5e549c2adf1");
        params2.put("secret", "3e19695ab0ab8c3b25715f250938212c");
        params2.put("access_token", access_token);
        params2.put("type", "jsapi");
        String jsticket = HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", params2);
        System.out.println(access_token);
        System.out.println(jsticket);
    }

}
