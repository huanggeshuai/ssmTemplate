package com.huang.sys.info;

import lombok.Data;

/**
 * Created by huang on 2019/1/27.
 */
@Data
public class SysInfoBean {
    private String hashAlgorithmName;  //加密算法名称

    private int hashIterations; //加密次数

    private String selfSalt; //自定义盐

    private String defaltUserName;//默认用户名

    private String defaltUserPassword;//默认密码

    private String unknowName;//用户名不存在

    private String successInfo;//操作成功

    private String failureInfo;//操作失败

    private String lockInfo;//账户锁定信息

    private String lockCount;//账户剩余次数

    private Integer pageNo;//默认显示第一页

    private Integer pageRow;//一页显示页数

    private String apiName;//api名称

    private String Name;//api名称

    private String url;//api名称

    private String email;//api名称

    private String description;

    private String vesion;

    private boolean isDev;//是否是开发环

}
