package com.xu.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther xurong
 * @date 2021/10/23 - 23:41
 * 自定义mybatis的配置类
 */
public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;


    private Map<String,Mapper> mappers = new HashMap<String, Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
//        this.mappers = mappers;
/*
当有多个mappers时，XMLConfigBuilder.java文件中的语句cfg.setMappers(mappers);调用该方法时，后一个mappers会将前一个
mappers覆盖掉，从而导致永远只保留最后一个mappers,这是不愿看到的，希望map集合中保留多个mappers
 */
        this.mappers.putAll(mappers);//追加：将后边的mappers追加进来，而不是覆盖掉
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
