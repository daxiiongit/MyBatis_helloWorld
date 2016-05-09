package com.sunyanxiong.test;

import com.sunyanxiong.mapper.UserMapper;
import com.sunyanxiong.mybatis.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description: 代码
 * <p>
 * Created by daxiongit on 2016/5/9 0009.
 */
public class UserMapperTest {

    // 在进行测试之前，想把 SqlSessionFactory 创建出来
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void testBefore() {
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

        // 生成回话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过调用 sqlSession中的方法来自动生成实现,返回的还是 mapper 代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(1);

        sqlSession.close();
        System.out.println(user);
    }

    // 根据用户名模糊查找用户信息
    @Test
    public void testFindUserByName() throws Exception {
        // 创建回话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 使用sqlSession 中的方法来自动生成实现
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = userMapper.findUserByName("小");

        // 关闭回话
        sqlSession.close();

        System.out.println(list);
    }


    // 根据用户id删除用户信息
    @Test
    public void testDeleteUserById() throws Exception {
        // 创建回话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 使用mapper代理对象自动生成实现
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 执行删除操作
        userMapper.deleteUserById(20);

        // 提交事务
        sqlSession.commit();

        // 关闭回话
        sqlSession.close();
    }

    // 根据用户id更新用户信息
    @Test
    public void testUpdateUserById() throws Exception {

        // 创建要更新的用户（必须设置主键id）
        User user = new User();

        // id = 9 不存在数据库中，这里没有报错，数据库中也没有
        user.setId(9);
        user.setUsername("hello");
        user.setSex("女");
        user.setAddress("121212");

        // 创建回话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 调用代理对象的方法
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 执行更新
        userMapper.updateUser(user);

        // 提交事务
        sqlSession.commit();

        // 关闭回话
        sqlSession.close();

    }

    // 新增用户信息
    @Test
    public void testInsertUser() throws Exception {

        // 创建新对象，不需要设置主键id
        User user = new User();
        user.setUsername("insert");

        // 创建回话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 执行操作
        userMapper.insertUser(user);

        // 提交事务
        sqlSession.commit();

        // 关闭回话
        sqlSession.close();

    }


}
