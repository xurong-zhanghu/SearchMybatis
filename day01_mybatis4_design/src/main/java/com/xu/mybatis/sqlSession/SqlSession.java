package com.xu.mybatis.sqlSession;

/**
 * @auther xurong
 * @date 2021/10/23 - 20:51
 * 自定义Mybatis中和数据库交互的核心类
 * 它里面可以创建dao接口的代理对象
 */
public interface SqlSession {
    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);//泛型先声明再使用，<T>是泛型的声明，后面所跟T是泛型的使用
    /**
     * 释放资源
     */
    void close();
}
