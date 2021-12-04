```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xurong.dao.IUserDao"><!--确定该方法是在哪一个Dao接口，写的是resources下的全限定类名-->

   <!--对于数据库表中列名与实体类中属性名不对应的解决办法2:弄一个实体类与数据库中的表中的一个映射
   缺点：相对于办法1多了一段解析xml文件的步骤，也就是相对于办法1执行效率慢一些，但相对于办法1其开发的效率变快了
   配置查询结果的列名和实体类的属性名的对应关系
   type="com.xurong.domain.User":指明需要映射的实体类的类名
   -->
    <resultMap id="userMap" type="com.xurong.domain.User">
        <!-- 主键字段的对应-->
        <id property="userId" column="id"></id>
        <!--非主键字段的对应-->
        <result property="userName" column="username"></result>
        <result property="userAddress" column="address"></result>
        <result property="userSex" column="sex"></result>
        <result property="userBirthday" column="birthday"></result>
    </resultMap>
e
    <!--
    配置查询所有
    -->
    <!-- <select id="findAll" resultType="com.xurong.domain.User">-->
    <select id="findAll" resultMap="userMap">
        select * from user;
        <!--
        1.一定是用户持久层接口中定义的方法，namespace+id就可以确定是哪一个Dao中的方法.resultType表明将该方法封装到哪里去
        2.对于数据库表中列名与实体类中属性名不对应的解决办法1：取别名
        3.select username as userName,id as userId,birthday as userBirthday,sex as userSex,address as userAddress from user;
        -->

    </select>

    <!--
     用来保存用户,也就是插入用户
     -->
    <insert id="saveUser" parameterType="com.xurong.domain.User">
        <selectKey keyProperty="userId" keyColumn="id" resultType="java.lang.Integer" order="AFTER">
            select last_insert_id();
        </selectKey>
        insert into user(username, address, sex, birthday) values (#{userName},#{userAddress},#{userSex},#{userBirthday});
    </insert>

    <!--
    用来对用户进行更新
    -->
    <update id="updateUser" parameterType="com.xurong.domain.User">
        update user set username = #{userName}, address = #{userName}, sex = #{userSex}, birthday = #{userBirthday} where id = #{userId};
    </update>

    <!--
    <用来对用户进行删除
    <delete id="deleteUser" parameterType="com.xurong.domain.User">
        < 利用id进行删除
        delete from user where id = #{id};
    </delete>
    -->

    <!--
    用来对用户进行删除
    因为传入的参数是唯一的，那么通配符 #{id};里面的参数可以随便取
    -->
    <delete id="deleteUser" parameterType="java.lang.Integer">
        delete from user where id = #{userId};
    </delete>

    <!--
    用来查找一个用户的信息
    resultType="com.xurong.domain.User":明确返回值是User类型,就是明确你要将数据封装到哪里去
    -->
    <select id="findById" parameterType="java.lang.Integer" resultMap="userMap">
        select * from user where id = #{userId};
    </select>

    <!--
    通过用户的名字完成对用户的模糊查询
    注意：模糊查询使用like关键字,但是此处没有使用%%
    -->
    <select id="findByName" parameterType="String" resultMap="userMap">
        select * from user where username like #{userName};<!--开发中最常使用该种方法-->
    <!--select * from user where username like '%${value}%';-->
    </select>

    <!-- 利用聚合函数查找有多少个用户-->
    <select id="findTotal" resultType="com.xurong.domain.User">
        select count(id) from user;
    </select>

    <!--
   改进版：相对模糊查询
   resultType="com.xurong.domain.QuerVo...提供类名
   #{user.username}...user是提供的属性名，通过属性名调用得到...username
   -->
    <select id="findUserByVo" parameterType="com.xurong.domain.QueryVo" resultMap="userMap">
        select * from user where username like #{user.userName};
    </select>

</mapper>
```

