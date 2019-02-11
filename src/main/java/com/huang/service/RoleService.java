package com.huang.service;


import com.huang.entity.Role;

import java.util.List;

/**
 * Created by huang on 2019/2/4.
 */
public interface RoleService {
    Object roleInfo(Integer pageNo,Integer pageSize) ;

    List<Role> roleList();

    void add(Role role);

    void edit(Role role);

    int del(List<Integer> ids);

    Role findById(Integer roleId);

    /**
     * 根据角色id查找对应的function
     * @param roleId
     * @return
     */
    List<String> userFunctions(Integer roleId);


}
