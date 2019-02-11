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
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roldName;

    /**
     * 描述
     */
    private String des;

    /**
     * 角色标识
     */
    private String roleCode;
}