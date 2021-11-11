package com.xurong.dao;

import com.xurong.domain.QueryVo;
import com.xurong.domain.User;

import java.util.List;

/**
 * @auther xurong
 * @date 2021/11/3 - 19:54
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     *
     * @return
     * 进行查询所有操作
     */
    List<User> findAll();

    /**
     * @param user
     * 进行插入操作
     */
    void saveUser(User user);

    /**
     *
     * @param user
     * 对表中的每一个对象进行插入操作
     */
    void updateUser (User user);

//    void deleteUser(User user);//自己写的
    void deleteUser(Integer userId);

    /**
     *
     * @param userId 所要查找的userId
     *
     * @return 返回的是一个User对象
     */
    User findById(Integer userId);

    /**
     * 根据用户的名字进行模糊查询
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 利用聚合函数查找有多少个用户
     * @return
     */
    int findTotal();

    /**
     *根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

}
