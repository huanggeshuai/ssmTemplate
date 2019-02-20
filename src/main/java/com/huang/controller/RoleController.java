package com.huang.controller;

import com.huang.aop.annoation.ValidDo;
import com.huang.base.BaseController;
import com.huang.entity.Role;
import com.huang.entity.RoleFunction;
import com.huang.group.ValidEditGroup;
import com.huang.service.RoleFunctionService;
import com.huang.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by huang on 2019/2/4.
 */
@Controller
@RequestMapping("/sys/role")
@Validated
public class RoleController extends BaseController<RoleController> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleFunctionService roleFunctionService;
    @RequiresPermissions("role:main")
    @RequiresAuthentication
    @RequestMapping("/index.action")
    public String index(){
        return "sys/role/index";
    }

    @RequiresPermissions("role:main")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("info.action")
    public Object info( Integer page, Integer rows){
        return roleService.roleInfo(page,rows);
    }

    @RequiresPermissions("role:add")
    @RequiresAuthentication
    @RequestMapping("add.action")
    public String add(){
        return "sys/role/add";
    }

    @RequiresPermissions("role:add")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("addRole.action")
    @ValidDo
    public Object addRole(@Validated Role role, BindingResult bindingResult){
        roleService.add(role);
        return success(sysInfoBean.getSuccessInfo());
    }

    @RequiresPermissions("role:edit")
    @RequiresAuthentication
    @RequestMapping("edit.action")
    public String edit(HttpServletRequest request,@NotNull(message = "角色Id不能为空")Integer id){
        Role role=roleService.findById(id);
        request.setAttribute("role",role);
        return "sys/role/edit";
    }


    @RequiresPermissions("role:edit")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("editRole.action")
    @ValidDo
    public Object editRole(@Validated(ValidEditGroup.class) Role role, BindingResult bindingResult){
        roleService.edit(role);
        return success(sysInfoBean.getSuccessInfo());
    }


    @RequiresPermissions("role:delete")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("delRole.action")
    public Object delRole(@NotEmpty(message = "必须选中角色") @RequestBody List<Integer> ids){
        roleService.del(ids);
        return success(sysInfoBean.getSuccessInfo());
    }

    /**
     * 进入授权操作页面
     * @param request
     * @param id
     * @return
     */
    @RequiresPermissions("role:auth")
    @RequiresAuthentication
    @RequestMapping("authority.action")
    public String authority(HttpServletRequest request,@NotNull(message = "角色Id不能为空")Integer id){
        Role role=roleService.findById(id);
        List<RoleFunction> roleFunctions=roleFunctionService.rolefuns(id);
        request.setAttribute("role",role);
        request.setAttribute("rolefuns",roleFunctions);
        return "sys/role/authority";
    }

    /**
     * 授权操作
     * @param roleFunctions
     * @return
     */
    @RequiresPermissions("role:auth")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("authorityDo.action")
    public Object authorityDo(@RequestBody List<RoleFunction> roleFunctions){
        roleFunctionService.auth(roleFunctions);
        return success(sysInfoBean.getSuccessInfo());
    }
}
