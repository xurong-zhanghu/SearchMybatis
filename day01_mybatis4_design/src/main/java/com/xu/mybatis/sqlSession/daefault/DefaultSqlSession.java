package com.xu.mybatis.sqlSession.daefault;

import com.xu.mybatis.cfg.Configuration;
import com.xu.mybatis.cfg.Mapper;
import com.xu.mybatis.sqlSession.SqlSession;
import com.xu.mybatis.sqlSession.proxy.MapperProxy;
import com.xu.mybatis.utils.DataSourceUtil;


import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @auther xurong
 * @date 2021/10/27 - 14:26
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;
    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        //代理谁，就用谁的类加载器
        //你代理谁就要实现相同的接口，new一个Class数组把这个接口传进去
        //如何代理，创建自己的代理方式
        //拿出来sql语句，拿出来映射信息,需要将cfg.getMappers()传过去（传给Mapperproxy对象）


        return  (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (connection != null){
            try{
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
