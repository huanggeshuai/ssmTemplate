package com.huang.controller;

import com.huang.base.BaseController;
import com.huang.entity.User;
import com.huang.service.RoleService;
import com.huang.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by huang on 2019/2/7.
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController<UserController> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("user:main")
    @RequiresAuthentication
    @RequestMapping("/index.action")
    public String index(){
        return "sys/user/index";
    }

    @RequiresPermissions("user:main")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/userInfo.action")
    public Object info(Integer page, Integer rows){
        return userService.userInfo(page,rows);
    }

    @RequiresPermissions("user:add")
    @RequiresAuthentication
    @RequestMapping("/add.action")
    public String add(HttpServletRequest request){
        request.setAttribute("roles",roleService.roleList());
        return "sys/user/add";
    }

    @RequiresPermissions("user:add")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/addUser.action")
    public Object addUser(User user){
        userService.addUser(user);
        return success(sysInfoBean.getSuccessInfo());
    }

    @RequiresPermissions(value = {"user:edit","user:resetpassword"},logical = Logical.OR)
    @RequiresAuthentication
    @RequestMapping("/edit.action")
    public String edit(Integer userId,HttpServletRequest request,String type){
        User user=userService.findUserById(userId);
        request.setAttribute("roles",roleService.roleList());
        request.setAttribute("user",user);
        if ("edit".equals(type)){
            return "sys/user/edit";
        }else {
            return "sys/user/resetPassword";
        }

    }

    @RequiresPermissions(value = {"user:edit","user:resetpassword"},logical = Logical.OR)
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/editUser.action")
    public Object editUser(User user){
        userService.editUser(user);
        return success(sysInfoBean.getSuccessInfo());
    }

    @RequiresPermissions("user:delete")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/deleteUser.action")
    public Object delUser(@RequestBody List<Integer> userIds){
        userService.delUser(userIds);
        return success(sysInfoBean.getSuccessInfo());
    }

    /**
     * 防止用户名重复
     * @param userName
     * @return
     */
    @RequiresPermissions("user:add")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/repetName.action")
    public Object repetName(@RequestParam("username") String userName){
      User user = userService.findUserByName(userName);
        if(ObjectUtils.isEmpty(user)){
            return vaildTrue();

        }else {
            return vaildFailure();
        }
    }


}
