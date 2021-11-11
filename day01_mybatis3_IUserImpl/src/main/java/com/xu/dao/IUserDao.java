package com.xu.dao;

import com.xu.domain.User;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @auther xurong
 * @date 2021/10/16 - 10:07
 * 用户的持久层接口
 */
public interface IUserDao {
    //定义一个方法，返回值都是User对象的List集合
    List<User> findAll();
}
