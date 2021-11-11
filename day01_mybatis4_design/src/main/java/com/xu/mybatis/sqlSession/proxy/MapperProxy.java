package com.xu.mybatis.sqlSession.proxy;

import com.xu.mybatis.cfg.Mapper;
import com.xu.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @auther xurong
 * @date 2021/10/27 - 14:46
 */
public class MapperProxy implements InvocationHandler {
    //map的key是全限定类名+方法名
    private Map<String, Mapper> mappers;
    private Connection connection;
    public MapperProxy(Map<String,Mapper> mappers,Connection connection){
        this.mappers = mappers;
        this.connection = connection;
    }
    /**
     * 用于对方法进行增强的，我们的增强其实就是调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();
        //2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //3.组合key
        String key = className+"."+methodName;
        //4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
        //判断是否有mapper
        if (mapper == null) {
            throw new IllegalArgumentException("传入参数有误");
        }
        //调用工具类执行查询所有
        return new Executor().selectList(mapper,connection);//需要建立连接，建立connection对象
    }
}
