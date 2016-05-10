package com.sunyanxiong.mapper;

import com.sunyanxiong.mybatis.User;
import com.sunyanxiong.mybatis.UserCustom;
import com.sunyanxiong.mybatis.UserQueryVo;

import java.util.List;

/**
 * Description: 代码
 * <p>
 * Created by daxiongit on 2016/5/9 0009.
 */
public interface UserMapper {

    // 按照 mapper 代理对象的规范来开发接口

    // 根据用户id查找用户信息
    public User findUserById (int id) throws Exception;

    // 根据用户名查找用户信息
    public List<User> findUserByName(String username) throws Exception;

    // 根据用户id删除用户信息
    public void deleteUserById(int id) throws Exception;

    // 更新用户信息
    public void updateUser(User user) throws Exception;

    // 新增用户信息
    public void insertUser(User user) throws Exception;

    // 用户综合查询
    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    // 查询综合查询总数
    public int findUserCount(UserQueryVo userQueryVo)throws Exception;

    // 输出结果为resultMap
    public User findUserByIdResultMap(int id) throws Exception;

    // 遍历
    public List<UserCustom> findUserListForeach(UserQueryVo userQueryVo) throws Exception;

}
