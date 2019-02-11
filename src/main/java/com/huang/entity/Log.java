/**
* @author huang
*
* @data 2019-01-31
*/
package com.huang.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by huang on 2019/01/31
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    /**
     * 主键
     */
    private Integer logid;

    /**
     * 目标
     */
    private String target;

    /**
     * msg
     */
    private String msg;

    /**
     * 存储异常信息
     */
    private String exception;

    /**
     * 访问的方法名称
     */
    private String methodName;

    /**
     * 操作人
     */
    private String user;

    /**
     * 操作时间
     */
    private Date operateTime;
}