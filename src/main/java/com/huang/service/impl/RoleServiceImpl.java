package com.huang.service.impl;

import com.huang.aop.annoation.PageQuery;
import com.huang.base.BaseService;
import com.huang.entity.Role;
import com.huang.entity.RoleExample;
import com.huang.mapper.RoleMapper;
import com.huang.service.RoleFunctionService;
import com.huang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huang on 2019/2/4.
 */
@Service
public class RoleServiceImpl extends BaseService<RoleServiceImpl> implements RoleService {
   @Autowired
    private RoleMapper roleMapper;

   @Autowired
    private RoleFunctionService roleFunctionService;


    /**
     * 自定义Pagequery查询分页注解
     * 返回类型一定要object
     * 第一个参数写页码
     * 第二个参数写显示条数
     */
    @Override
    @PageQuery
    public Object roleInfo(Integer pageNo,Integer pageSize) {

        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<Role> roleList() {
        return roleMapper.selectByExample(new RoleExample());
    }

    @Override
    public void add(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void edit(Role role) {
        roleMapper.updateByPrimaryKey(role);

    }

    @Transactional
    @Override
    public int del(List<Integer> ids) {
        RoleExample roleExample=new RoleExample();
        RoleExample.Criteria criteria=roleExample.createCriteria();
        criteria.andRoleIdIn(ids);
        roleFunctionService.del(ids);
        return roleMapper.deleteByExample(roleExample);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<String> userFunctions(Integer roleId) {
        return roleMapper.findFunByRoleId(roleId);
    }


}
