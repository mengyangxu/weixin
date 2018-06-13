package com.xmr.util;

import java.util.Properties;

public class GlobalConstants {

    public static Properties interfaceUrlProperties;

/**
 * 
 * @Description: TODO
 * @param @param key
 * @param @return   
 * @author dapengniao
 * @date 2015 年 10 月 13 日 下午 4:59:14
 */
    public static String getInterfaceUrl(String key) {
        return (String) interfaceUrlProperties.get(key);
    }

}