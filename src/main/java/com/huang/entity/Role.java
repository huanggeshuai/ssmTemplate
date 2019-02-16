/**
* @author huang
*
* @data 2019-01-27
*/
package com.huang.entity;

import com.huang.group.ValidEditGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.regex.Matcher;

/**
* Created by huang on 2019/01/27
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /**
     * 角色id
     */
    @NotNull(groups = ValidEditGroup.class,message = "角色主键不能为空")
    private Integer roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roldName;

    /**
     * 描述
     */
    private String des;

    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空")
    @Pattern(regexp = "[A-Za-z0-9\\-]*",message = "角色标识不能含有中文")
    private String roleCode;
}

