/**
* @author huang
*
* @data 2019-01-27
*/
package com.huang.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* Created by huang on 2019/01/27
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Function {
    /**
     * 系统功能id
     */
    private Integer functionId;

    /**
     * 系统功能父id
     */
    private Integer parentId;

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 功能描述
     */
    private String functionDes;

    /**
     * 路径
     */
    private String url;

    /**
     * 功能说明
     */
    private String text;

    /**
     * 
     */
    private String state;

    /**
     * 权限代码
     */
    private String code;

    /**
     * 图标名称
     */
    private String icon;

    /**
     * 0代表按钮'button' 1代表页面'page'
     */
    private Integer type;


    private List<Function> children;

}