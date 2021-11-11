package com.xu.mybatis.sqlSession;

import com.xu.mybatis.cfg.Configuration;
import com.xu.mybatis.sqlSession.daefault.DefaultSqlSessionFactory;
import com.xu.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建SqlSessionFactoryBuilder对象
 * @auther xurong
 * @date 2021/10/23 - 20:37
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);//将得到的cfg作为参数传给DefaultSqlSessionFactory对象
    }
}
