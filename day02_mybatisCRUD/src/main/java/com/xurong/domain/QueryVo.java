package com.xurong.domain;

/**
 * @auther xurong
 * @date 2021/11/10 - 19:50
 */
public class QueryVo {//使用在实际开发中，由多个对象组成一个查询条件，实现对数据的查询
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
