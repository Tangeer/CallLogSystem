package com.longrise.loggen;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author ：Tanger.
 * @Date ：Created in 21:21 2019/10/9
 */

public class PropertiesUtil {
    static Properties prop;
    static {
        try {
            InputStream in = ClassLoader.getSystemResourceAsStream("gendata.conf");
            prop = new Properties();
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProp(String key){
        return prop.getProperty(key);
    }

    public static String getString(String key){
        return prop.getProperty(key);
    }

    public static int getInt(String key){
        return Integer.parseInt(prop.getProperty(key));
    }






}
