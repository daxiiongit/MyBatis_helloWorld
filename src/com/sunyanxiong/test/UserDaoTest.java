package com.sunyanxiong.test;

import com.sunyanxiong.dao.UserDao;
import com.sunyanxiong.dao.impl.UserDaoImpl;
import com.sunyanxiong.mybatis.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Description: 代码
 * <p>
 * Created by daxiongit on 2016/5/9 0009.
 */
public class UserDaoTest {

    // 在进行测试之前，想把 SqlSessionFactory 创建出来
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void testBefore(){
        String resource = "SqlMapConfig.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据用户id查找用户信息
    @Test
    public void testFindUserById() throws Exception {
        // 调用UserDao接口
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(1);

        System.out.println(user);
    }

    // 根据用户名模糊查找用户信息
    @Test
    public void testFindUserByName() throws Exception{
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> list = userDao.findUserByName("张三");

        System.out.println(list);
    }


    // 根据用户id删除用户信息
    @Test
    public void testDeleteUserById() throws Exception{
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        userDao.deleteUserById(26);

    }

    // 根据用户id更新用户信息
    @Test
    public void testUpdateUserById() throws Exception{

        // 必须设置 主键 id
        User user = new User();
        user.setId(1);
        user.setUsername("大熊");
        user.setBirthday(new Date());

        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        userDao.updateUserById(user);

    }

    // 新增用户信息
    @Test
    public void testInsertUser() throws Exception{

        User user = new User();
        user.setUsername("哒哒哒");
        user.setSex("女");
        user.setAddress("111111");

        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        userDao.insertUser(user);

    }

}
