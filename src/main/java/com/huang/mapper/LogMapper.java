/**
* @author huang
*
* @data 2019-04-14
*/
package com.huang.mapper;

import com.huang.entity.Log;
import com.huang.entity.LogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* Created by huang on 2019/04/14
*/
@Repository
public interface LogMapper {
    long countByExample(LogExample example);

    int deleteByExample(LogExample example);

    int deleteByPrimaryKey(Integer logid);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogExample example);

    Log selectByPrimaryKey(Integer logid);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

    int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}