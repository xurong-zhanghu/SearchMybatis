package com.xu.mybatis.io;

import java.io.InputStream;

/**
 * @auther xurong
 * @date 2021/10/23 - 20:28
 * 使用类加载器读取配置文件的类
 *
 */
public class Resources {
    /**
     * 根据传入的参数，获取一个字节流
     * Resources.class:拿到当前类的字节码
     * getClassLoader()：根据字节码获取这个类的类加载器
     * getResourceAsStream(filePath)：根据类加载器读取这个SqlMapConfig.xml配置文件
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
