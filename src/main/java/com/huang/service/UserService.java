package com.huang.service;

import com.huang.entity.User;

import java.util.List;

/**
 * Created by huang on 2019/1/19.
 */
public interface UserService {
    long count();

    void addUser(User user);

    void editUser(User user);

    User findUserByName(String userName);

    void delUser(List<Integer> userIds);

    Object userInfo(Integer pageNo,Integer pageSize);

    User findUserById(Integer userId);

    boolean passwordIsCorrectly(Integer userId,String password);
}
