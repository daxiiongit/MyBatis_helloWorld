package com.sunyanxiong.test;

import com.sunyanxiong.mybatis.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
public class UserTest {

    @Test
    public void testFindUserById(){

        // 查看项目当前目录
        System.out.println(System.getProperty("user.dir"));

        // 1.创建回话工厂
        // 1.1 创建配置文件文件名
        String resource = "SqlMapConfig.xml";
        // 1.2 创建输入流
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 2.创建回话
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 3.执行sql(拿到配置文件中statement的id),查找id=1的用户信息
            User user = sqlSession.selectOne("test.findUserById",1);

            // 打印信息
            System.out.println(user);

            // 关闭 sqlSession
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试通过用户名来模糊查询用户信息
    @Test
    public void testFindUserByName(){
        // 拿到配置文件
        String resource = "SqlMapConfig.xml";
        try {
            // 创建输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 创建 回话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建回话
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 执行sql并返回结果集(模糊查找出来的结果集可能有多条，使用List来保存结果集),查找的时候不能只查找单条数据
            // list 中的User表示的在映射文件中配置的输出结果集类型
            List<User> list = sqlSession.selectList("test.findUserByName","小");
            // 打印结果
            System.out.println(list);

            // 关闭回话
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试添加用户信息
    @Test
    public void testInsertUser(){
        // 获取配置文件资源
        String resource = "SqlMapConfig.xml";
        try {
            // 创建输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 创建回话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建回话
            SqlSession sqlSession = sqlSessionFactory.openSession();

            // 在插入数据执行，需要设置插入数据对象（因为在配置文件中配置输入类型为pojo对象）,在数据库中设置id为自动递增，所以这里不需要设置id值
            User user = new User();
            // 这里设置的 id 值和数据库没有关系，因为这里使用的是系统自动设置的，所以这里设置的id值没有任何意义
           // user.setId(28);

            user.setUsername("sunyanxiong");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setAddress("300387");

            //执行Sql，这里是插入数据，没有返回值
            sqlSession.insert("test.insertUser",user);

            // 因为是插入数据，所以需要提交事务(如果不提交事务，数据将不会保存到数据库中)
            sqlSession.commit();

            // 获取插入数据的id值
            System.out.println(user.getId());

            // 关闭回话
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 删除用户信息
    @Test
    public void testDeleteUserById(){
        // 获取配置文件目录
        String resource = "SqlMapConfig.xml";

        try {
            // 创建输入流对象
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 创建回话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建回话
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 执行删除操作
            sqlSession.delete("test.deleteUserById",27);
            // 提交事务
            sqlSession.commit();
            // 关闭回话
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 更新用户信息
    @Test
    public void testUpdateUserById(){
        // 拿到配置文件目录
        String resource = "SqlMapConfig.xml";

        try {
            // 创建输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 创建回话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建回话
            SqlSession sqlSession = sqlSessionFactory.openSession();
            // 创建更新数据
            User user = new User();
            // 必须设置 id 主键
            user.setId(10);
            user.setUsername("hsfhsk");
            user.setBirthday(new Date());

            // 其他没有设置的数默认为空？？？

            // 更新数据
            sqlSession.update("test.updateUserById",user);

            // 提交事务
            sqlSession.commit();

            // 关闭回话
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
