/**
* @author huang
*
* @data 2019-01-27
*/
package com.huang.mapper;

import com.huang.entity.Function;
import com.huang.entity.FunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* Created by huang on 2019/01/27
*/
@Repository
public interface FunctionMapper {
    long countByExample(FunctionExample example);

    int deleteByExample(FunctionExample example);

    int deleteByPrimaryKey(Integer functionId);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionExample example);

    Function selectByPrimaryKey(Integer functionId);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);


    List<Function> selectchildbypartent(@Param("parent_id")Integer partentId,@Param("roleId")Integer roleId);

    Function selectchildrenByid(Integer functionId);

    List<Function> functionMenu(@Param("userid") Integer UserId);
}