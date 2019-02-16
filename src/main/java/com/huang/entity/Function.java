/**
* @author huang
*
* @data 2019-01-27
*/
package com.huang.entity;

import com.huang.group.ValidAddGroup;
import com.huang.group.ValidDeleteGroup;
import com.huang.group.ValidEditGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.regex.Matcher;

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
    @NotNull(groups = {ValidEditGroup.class,ValidDeleteGroup.class},message = "功能id不能为空")
    private Integer functionId;

    /**
     * 系统功能父id
     */
    @NotNull(groups = {ValidAddGroup.class},message = "父id不能为空")
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
    @NotBlank(message = "菜单名称不能为空")
    private String text;

    /**
     * 
     */
    private String state;

    /**
     * 权限代码
     */
    @NotBlank(message = "权限标识不能为空")
    @Pattern(regexp = "[A-Za-z0-9:\\-]*",message = "权限标识不能含有中文")
    private String code;

    /**
     * 图标名称
     */
    private String icon;

    /**
     * 0代表按钮'button' 1代表页面'page'
     */
    @NotNull(message = "权限类型不能为空")
    private Integer type;


    private List<Function> children;

}