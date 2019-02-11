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
public class RoleFunction {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 功能id
     */
    private Integer functionId;
}