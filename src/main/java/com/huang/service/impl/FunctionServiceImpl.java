package com.huang.service.impl;

import com.huang.base.BaseService;
import com.huang.entity.Function;
import com.huang.entity.FunctionExample;
import com.huang.mapper.FunctionMapper;
import com.huang.service.FunctionService;
import com.huang.service.RoleFunctionService;
import com.huang.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2019/1/29.
 */
@Service
public class FunctionServiceImpl extends BaseService<FunctionServiceImpl> implements FunctionService {
    @Autowired
    private FunctionMapper mapper;

    @Autowired
    private RoleFunctionService roleFunctionService;
    @Override
    public List<Function> treeMenu(Integer UserId) {
        //将查询出来的list转成tree结构
        return MyUtils.getTree(mapper.functionMenu(UserId));
    }

    @Override
    public List<Function> treeTable() {
        return mapper.selectByExample(new FunctionExample());
    }

    @Override
    public Function findById(Integer functionId) {
        return mapper.selectByPrimaryKey(functionId);
    }

    @Override
    public void addMenu(Function function) {
        mapper.insertSelective(function);
    }

    @Override
    public void editMenu(Function function) {
        mapper.updateByPrimaryKeySelective(function);
    }

    @Transactional//多表删除
    @Override
    public int deleteFunById(List<Integer> functionId) {
        FunctionExample functionExample=new FunctionExample();
        FunctionExample.Criteria criteria=functionExample.createCriteria();
        criteria.andFunctionIdIn(functionId);
        roleFunctionService.deleteByFunctionIds(functionId);
        return mapper.deleteByExample(functionExample);
    }

    @Override
    @Transactional
    public void deleteAllFunById(Integer functionId) {
        delChildrenId(getChildrenFunction(functionId));
    }

    /**
     * 获取当前菜单以及他所有的孩子菜单
     * @param functionId
     * @return
     */
    @Override
    public Function getChildrenFunction(Integer functionId) {
        return mapper.selectchildrenByid(functionId);
    }

    /**
     * 递归删除当前菜单及其以下所有的孩子菜单
     * @param function
     */
    private void delChildrenId(Function function){
        List<Integer> funids=new ArrayList<>();
        funids.add(function.getFunctionId());
        if(!ObjectUtils.isEmpty(function.getChildren())){
            for(int i=0;i<function.getChildren().size();i++){
                funids.add(function.getChildren().get(i).getFunctionId());
                delChildrenId(function.getChildren().get(i));
            }
        }
        deleteFunById(funids);
    }
}
