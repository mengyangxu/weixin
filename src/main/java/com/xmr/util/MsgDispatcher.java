package com.xmr.util;

import com.xmr.messageResp.TextMessage;

import java.util.Date;
import java.util.Map;


/**
 * ClassName: MsgDispatcher
 * @Description: 消息业务处理分发器
 * @author dapengniao
 * @date 2016 年 3 月 7 日 下午 4:04:21
 */
public class MsgDispatcher {
    public static String processMessage(Map<String, String> map) {
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");

            String openid=map.get("FromUserName"); //用户 openid
            String mpid=map.get("ToUserName");   //公众号原始 ID

            //普通文本消息

            TextMessage txtmsg=new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
                txtmsg.setContent("你好，这里是崔用志个人账号！");
                return MessageUtil.textMessageToXml(txtmsg);
            }

        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }

        return null;
    }
}