/**
* @author huang
*
* @data 2019-01-27
*/
package com.huang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
* Created by huang on 2019/01/27
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户id 主键
     */
    private Integer userid;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码 加密的
     */
    @JsonIgnore//不将敏感信息序列化
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 盐 混淆加密
     */
    @JsonIgnore  //不将敏感信息序列化
    private String salt;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户颜色
     */
    private String userColor;

    private Role role;
}