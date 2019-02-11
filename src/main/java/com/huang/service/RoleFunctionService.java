package com.huang.service;

import com.huang.entity.RoleFunction;

import java.util.List;

/**
 * Created by huang on 2019/2/6.
 */
public interface RoleFunctionService {
    void auth(List<RoleFunction> roleFunctions);

    void del(Integer roleId);

    void del(List<Integer> roleIds);

    void deleteByFunctionIds(List<Integer> functionIds);

    List<RoleFunction> rolefuns(Integer roleId);
}
