package com.huang.service.impl;

import com.huang.base.BaseService;
import com.huang.entity.RoleFunction;
import com.huang.entity.RoleFunctionExample;
import com.huang.exception.myexception.RoleFunctionException;
import com.huang.mapper.RoleFunctionMapper;
import com.huang.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by huang on 2019/2/6.
 */
@Service
public class RoleFunctionServiceImpl extends BaseService<RoleFunctionServiceImpl> implements RoleFunctionService {
    @Autowired
    private RoleFunctionMapper roleFunctionMapper;



    @Override
    @Transactional
    public void auth(List<RoleFunction> roleFunctions) {
        if(ObjectUtils.isEmpty(roleFunctions)){
            throw new RoleFunctionException("所选的权限不能为空");
        }
        del(roleFunctions.get(0).getRoleId());
        roleFunctionMapper.insertBatch(roleFunctions);

    }

    @Override
    public void del(Integer roleId) {
        RoleFunctionExample roleFunctionExample=new RoleFunctionExample();
        RoleFunctionExample.Criteria criteria=roleFunctionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        roleFunctionMapper.deleteByExample(roleFunctionExample);
    }

    @Override
    public void del(List<Integer> roleIds) {
        RoleFunctionExample roleFunctionExample=new RoleFunctionExample();
        RoleFunctionExample.Criteria criteria=roleFunctionExample.createCriteria();
        criteria.andRoleIdIn(roleIds);
        roleFunctionMapper.deleteByExample(roleFunctionExample);
    }

    @Override
    public void deleteByFunctionIds(List<Integer> functionIds) {
        RoleFunctionExample roleFunctionExample=new RoleFunctionExample();
        RoleFunctionExample.Criteria criteria=roleFunctionExample.createCriteria();
        criteria.andFunctionIdIn(functionIds);
        roleFunctionMapper.deleteByExample(roleFunctionExample);
    }

    /**
     * 根据角色id查找相关function
     * @param roleId
     * @return
     */
    @Override
    public List<RoleFunction> rolefuns(Integer roleId) {
        RoleFunctionExample roleFunctionExample=new RoleFunctionExample();
        RoleFunctionExample.Criteria criteria=roleFunctionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        return roleFunctionMapper.selectByExample(roleFunctionExample);
    }
}
