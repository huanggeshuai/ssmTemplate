/**
* @author huang
*
* @data 2019-04-14
*/
package com.huang.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by huang on 2019/04/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
    /**
     * 主键
     */
    private Integer logid;

    /**
     * msg 操作信息
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
     * 操作人id
     */
    private Integer userid;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 存储ip地址
     */
    private String ip;

    /**
     * log
     */
    private static final long serialVersionUID = 1L;
}