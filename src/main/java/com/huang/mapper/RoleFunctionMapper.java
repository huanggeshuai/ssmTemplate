/**
* @author huang
*
* @data 2019-01-27
*/
package com.huang.mapper;

import com.huang.entity.RoleFunction;
import com.huang.entity.RoleFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* Created by huang on 2019/01/27
*/
@Repository
public interface RoleFunctionMapper {
    long countByExample(RoleFunctionExample example);

    int deleteByExample(RoleFunctionExample example);

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("functionId") Integer functionId);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    List<RoleFunction> selectByExample(RoleFunctionExample example);

    int updateByExampleSelective(@Param("record") RoleFunction record, @Param("example") RoleFunctionExample example);

    int updateByExample(@Param("record") RoleFunction record, @Param("example") RoleFunctionExample example);

    /**
     *批量插入
     * @param lists
     */
    void insertBatch(List<RoleFunction> lists);
}