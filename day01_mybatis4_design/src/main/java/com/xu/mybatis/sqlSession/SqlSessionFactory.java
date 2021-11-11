package com.xu.mybatis.sqlSession;

/**
 * @auther xurong
 * @date 2021/10/23 - 20:40
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
