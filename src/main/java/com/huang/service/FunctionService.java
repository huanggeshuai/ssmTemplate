package com.huang.service;

import com.huang.entity.Function;

import java.util.List;

/**
 * Created by huang on 2019/1/29.
 */
public interface FunctionService {
    /**
     * 生成json树
     * @return
     */
    List<Function> treeMenu(Integer UserId);

    /**
     * 生成json表格
     * @return
     */
    List<Function> treeTable();

    Function findById(Integer functionId);

    void addMenu(Function function);

    void editMenu(Function function);

    /**
     * 批量删除function
     * @param functionId
     * @return
     */
    int deleteFunById(List<Integer> functionId);


    /**
     * 删除当前菜单及其孩子所有菜单
     * @param functionId
     */
    void deleteAllFunById(Integer functionId);

    /**
     * 查询当前菜单及其孩子的所有菜单
     * @param functionId
     * @return
     */
    Function getChildrenFunction(Integer functionId);

}
