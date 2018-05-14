package com.xmr.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WXController {

    @RequestMapping("/")
    @ResponseBody
    public String weixin(HttpServletRequest req){
        //return "KbLSyBOgqztadMaU8fXmZSe1ElqZjKz39jxCTg3ajzt";
        String echostr = req.getParameter("echostr").toString();
        return echostr;
    }

    @RequestMapping("weixin")
    @ResponseBody
    public String weixi(){
        return "weixin";
    }

}
