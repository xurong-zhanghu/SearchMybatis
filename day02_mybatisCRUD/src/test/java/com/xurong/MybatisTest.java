package com.xurong;

import com.xurong.dao.IUserDao;
import com.xurong.domain.QueryVo;
import com.xurong.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @auther xurong
 * @date 2021/11/3 - 20:05
 * 测试mybatis的CRUD操作
 */
public class MybatisTest {
    InputStream in;
    SqlSession sqlSession;
    IUserDao userDao;

    /**
     *
     * @throws Exception
     * 利用java封装的特性，将数据库连接等步骤抽取出来，方便后面直接调用
     */
    @Before
    public void init() throws Exception{

        //1.读取配置文件，生成字节流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.通过SqlSession对象生成UserDao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     *
     * @throws Exception
     * 释放占有的物理资源
     */
    @After
    public void destroy() throws Exception{
        //调用SqlSession中的提交事物的方法，方能将数据成功提交到数据库
        sqlSession.commit();//将其放置在资源释放之前，不用每都调用该方法
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {

        //5.执行查询所有方法
        List<User> userList = userDao.findAll();
        for (User user:userList) {
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("xuorng mybatis last insert");
        user.setUserSex("男");
        user.setUserAddress("长沙");
        user.setUserBirthday(new Date());

        //执行保存方法之前其id取值
        System.out.println("保存操作之前："+user);

        //5.执行保存操作
        userDao.saveUser(user);

        //执行保存方法之后其id取值
        System.out.println("保存操作之后："+user);

    }


    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        //由于还没有创建查询id的方法，所以先命名一个ID
        user.setUserId(56);
        user.setUserName("updateUser Test");
        user.setUserSex("男");
        user.setUserAddress("长沙");
        user.setUserBirthday(new Date());

        //5.执行更新操作
        userDao.updateUser(user);

    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
//        User user = new User();
//        //由于还没有创建查询id的方法，所以先命名一个ID
//        user.setId(55);
//        //5.执行更新操作
//        userDao.deleteUser(user);
        userDao.deleteUser(57);

    }

    /**
     * 测试查询某一个对象
     */
    @Test
    public void testFindById() {

        System.out.println(userDao.findById(43));

    }

    /**
     * 通过名字完成对用户的模糊查询
     */
    @Test
    public void testFindByName() {

        List<User> users = userDao.findByName("%王%");//开发中采用次设计方法
//        List<User> users = userDao.findByName("王");
        for(User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 利用聚合函数查找有多少个用户
     */
    @Test
    public void testFinTotal() {

        int num = userDao.findTotal();
        System.out.println("user表中总共有"+num+"位user");
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void test_QueryVo_FindByName() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        //执行查询一个方法
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users) {
            System.out.println(u);
        }
    }
}
