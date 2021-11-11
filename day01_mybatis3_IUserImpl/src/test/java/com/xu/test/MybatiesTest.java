package com.xu.test;

import com.xu.dao.Impl.IUserImpl;
import com.xu.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @auther xurong
 * @date 2021/10/20 - 13:55
 */
public class MybatiesTest {
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");//需要抛出异常，可能会出现I/O异常
        //2.创建SqlSessionFactory工厂
        /*我们创建factory就是为了产生SqlSession对象，Mybatis提供给我们这个工厂的时候，把Mybatis实现细节给省略了
        * 他提供了SqlSessionFactoryBuilder类，可以通过new创建一个实例化对象,通过该实例化对象调用build(),从而创建一个工厂
        * 怎么解析配置文件，怎么封装都与我们无关，都交给了Mybatis,体现了框架的好处，封装细节，用极简的方式实现功能
        */
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);//该对象为接口，接口不能new
//        //3.使用工厂生产SqlSession对象
//        /*这个对象就可以操作数据库了，但是我们不是用sqlSession操作，我们是用Dao操作，但是我们只有Dao接口，所以
//        *我们是用Dao接口的代理对象操作。
//        */
//        SqlSession sqlSession = factory.openSession();
//        //4.使用SqlSesssion创建Dao接口的代理对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        //因为IUserImpl.java已经生成了SqlSession对象，并且IUserImpl.java不是接口，无需使用代理对象
        IUserImpl userDao = new IUserImpl(factory);

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
//        sqlSession.close();//没有创建该对象，无需释放资源
        inputStream.close();
    }
}
