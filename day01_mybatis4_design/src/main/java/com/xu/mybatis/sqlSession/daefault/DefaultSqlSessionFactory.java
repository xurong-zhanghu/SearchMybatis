package com.xu.mybatis.sqlSession.daefault;

import com.xu.mybatis.cfg.Configuration;
import com.xu.mybatis.sqlSession.SqlSession;
import com.xu.mybatis.sqlSession.SqlSessionFactory;

/**
 * @auther xurong
 * @date 2021/10/27 - 14:15
 *SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;//用于接收cfg

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库的对象
     *
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}