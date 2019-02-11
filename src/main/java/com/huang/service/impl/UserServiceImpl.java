package com.huang.service.impl;

import com.huang.aop.annoation.PageQuery;
import com.huang.base.BaseService;
import com.huang.entity.User;
import com.huang.entity.UserExample;
import com.huang.mapper.UserMapper;
import com.huang.service.RoleService;
import com.huang.service.UserService;
import com.huang.utils.MyUtils;
import com.huang.utils.ShiroUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huang on 2019/1/19.
 */
@Service
public class UserServiceImpl extends BaseService<UserServiceImpl> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public long count() {
        return userMapper.countByExample(new UserExample());
    }

    @Override
    public void addUser(User user) {
        String userPassword=ShiroUtils.getPassword(user.getPassword(),sysInfoBean.getSelfSalt()); //我知道在这里加入自定义盐不太好，我也不清楚在哪里加入
        user.setPassword(userPassword);
        userMapper.insertSelective(
                ShiroUtils.encrype(
                        user, sysInfoBean.getHashAlgorithmName(),
                        sysInfoBean.getHashIterations()));
    }

    @Override
    public void editUser(User user) {
        String userPassword=ShiroUtils.getPassword(user.getPassword(),sysInfoBean.getSelfSalt()); //我知道在这里加入自定义盐不太好，我也不清楚在哪里加入
        user.setPassword(userPassword);
        userMapper.updateByPrimaryKeySelective(
                ShiroUtils.encrype(
                        user, sysInfoBean.getHashAlgorithmName(),
                        sysInfoBean.getHashIterations()));
    }

    @Override
    public User findUserByName(String userName) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<User> users=userMapper.selectByExample(userExample);
        return MyUtils.fetch(users);
    }

    @Override
    public void delUser(List<Integer> userIds) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUseridIn(userIds);
        userMapper.deleteByExample(userExample);
    }

    /**
     * 使用PageQuery注解 自动实现分页
     * 但是参数位置不能传错，不能传反 第一个参数是页码，第二个参数是一页的条数
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    @PageQuery
    public Object userInfo(Integer pageNo, Integer pageSize) {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }


    /**
     * 用户修改密码
     * @param userId
     * @param password
     * @return
     */
    @Override
    public boolean passwordIsCorrectly(Integer userId, String password) {
        User user=findUserById(userId);
        String oldPassword=user.getPassword();
        String imencripePassword=ShiroUtils.getPassword(password,sysInfoBean.getSelfSalt());
        String confirmPassword=ShiroUtils.encrype(imencripePassword,user.getSalt(),sysInfoBean.getHashAlgorithmName(),sysInfoBean.getHashIterations());
        return oldPassword.equals(confirmPassword);
    }
}
