package com.xu.dao.Impl;

import com.xu.dao.IUserDao;
import com.xu.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @auther xurong
 * @date 2021/10/23 - 9:35
 */
public class IUserImpl implements IUserDao {
    private SqlSessionFactory factory;
    /*
    将默认构造函数覆盖掉，当我们在用的时候，就一定会给我们传一个工厂进来
    使用工厂的好处，使用工厂创将SqlSession对象
     */
   public IUserImpl(SqlSessionFactory factory){//将默认构造函数覆盖掉
       this.factory = factory;
   }
   public List<User> findAll() {
       //使用工厂创将SqlSession对象
       SqlSession sqlSession = factory.openSession();
       //使用SqlSession对象调用方法
       List<User> userList = sqlSession.selectList("com.xu.dao.IUserDao.findAll");//namespace+id--保证唯一性
       //IUserDao.xml文件中namespace起作用，用于指定是哪一个Dao下的findAll方法（
       // 保证findAll方法的唯一性，因为有多个映射Dao的xml文件时，findAll不唯一 ）
        sqlSession.close();//释放对象所占资源
        return userList;
    }
}
