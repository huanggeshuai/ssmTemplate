package com.huang.controller;

import com.alibaba.fastjson.JSON;
import com.huang.aop.annoation.ValidDo;
import com.huang.base.BaseController;
import com.huang.entity.User;
import com.huang.group.ValidLoginGroup;
import com.huang.service.FunctionService;
import com.huang.service.UserService;
import com.huang.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2019/1/18.
 */
@Controller
public class LoginController extends BaseController<LoginController> {
    @Autowired
    private UserService userService;

    @Autowired
    private FunctionService functionService;

    @RequiresAuthentication
    @RequestMapping(value = "/index.action")
    public String index(HttpServletRequest request) {
        User user=ShiroUtils.getUser();
        List menuList= functionService.treeMenu(user.getUserid());
        request.setAttribute("menus",menuList);
        logger.error(JSON.toJSON(menuList));
        return "index/index";
    }

    @RequestMapping(value = "/login.action")
    public String login() {
        return "login/login";
    }



    @ResponseBody
    @ValidDo
    @RequestMapping(value = "/logindo.action")
    public Map logindo(@Validated(ValidLoginGroup.class) User user, BindingResult bindingResult) {
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=
                new UsernamePasswordToken(user.getUsername(),
                        ShiroUtils.getPassword(user.getPassword(),sysInfoBean.getSelfSalt()));
        subject.login(token);


        return success("登入成功");
    }

    //用户退出
    @RequiresAuthentication
    @RequestMapping(value = "/loginout.action")
    public String loginout() throws Exception{
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        return "login/login";
    }

    //进入修改密码页码
    @RequiresAuthentication
    @RequestMapping(value = "/changePassword.action")
    public String changePassword() {
        return "index/changePassword";
    }

    //对前端传来的旧密码与新密码进行对比
    @RequiresAuthentication
    @ResponseBody
    @RequestMapping(value = "/confirmPassword.action")
    public Object confirmPassword(@RequestParam("oldpassword") String password, Integer userId) {
        if(userService.passwordIsCorrectly(userId,password)){
            return vaildTrue();
        }else{
            return vaildFailure();
        }
    }

    //修改密码
    @RequiresAuthentication
    @ResponseBody
    @RequestMapping(value = "/changePasswordDo.action")
    public Object confirmPasswordDo(User user) {
        userService.editUser(user);
        return success("操作成功");
    }

    /**
     * 处理404异常
     * @return
     */
    @RequestMapping(value = "/404.action")
    public ModelAndView handler404Exception() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("sys/error/404");
        modelAndView.addObject("info","你所访问的资源不存在，请重新输入url链接");
        return modelAndView;
    }
}
