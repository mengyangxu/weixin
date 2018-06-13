package com.xmr.control;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xmr.jssdk.JSSDK_Config;
import com.xmr.util.EventDispatcher;
import com.xmr.util.MessageUtil;
import com.xmr.util.MsgDispatcher;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xmr.util.SignUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wechat")
public class WechatSecurity {
    //private static Logger logger = Logger.getLogger(WechatSecurity.class);
    static String timestamp = "";
    /**
     * 
     * @Description: 用于接收 get 参数，返回验证参数
     * @param @param request
     * @param @param response
     * @param @param signature
     * @param @param timestamp
     * @param @param nonce
     * @param @param echostr
     * @author dapengniao
     * @date 2016 年 3 月 4 日 下午 6:20:00
     */
    @RequestMapping(value = "security", method = RequestMethod.GET)
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                //logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            //logger.error(e, e);
        }
    }

    @RequestMapping(value = "security", method = RequestMethod.POST)
    @ResponseBody
    // post 方法用于接收微信服务端消息
    public String DoPost(HttpServletRequest request) {
        timestamp = request.getParameter("timestamp").toString();
        try{
            Map<String, String> map=MessageUtil.parseXml(request);
            String msgtype=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                return EventDispatcher.processEvent(map); //进入事件处理
            }else{
                return MsgDispatcher.processMessage(map); //进入消息处理
            }
        }catch(Exception e){
            //logger.error(e,e);
        }
        return null;
    }

    @RequestMapping("jssdk")
    @ResponseBody
    public Map<String,Object> JSSDK_config(
            @RequestParam(value = "url", required = true) String url) {
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println(url);
            Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url,timestamp);
            map.put("data",configMap);
            return map;
        } catch (Exception e) {
            return map;
        }

    }
}