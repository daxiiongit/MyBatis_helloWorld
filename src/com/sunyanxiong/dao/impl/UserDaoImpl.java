package com.sunyanxiong.dao.impl;

import com.sunyanxiong.dao.UserDao;
import com.sunyanxiong.mybatis.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Description: 代码
 * <p>
 * Created by daxiongit on 2016/5/9 0009.
 */
public class UserDaoImpl implements UserDao {

    // 将SqlSessionFactory 注入到构造器中
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("test.findUserById",id);

        sqlSession.close();

        return user;
    }

    @Override
    public List<User> findUserByName(String username) throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("test.findUserByName",username);

        sqlSession.close();

        return list;
    }

    @Override
    public void deleteUserById(int id) throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.delete("test.deleteUserById",id);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void updateUserById(User user) throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("test.updateUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void insertUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("test.insertUser",user);
        sqlSession.commit();
        sqlSession.close();
    }
}
