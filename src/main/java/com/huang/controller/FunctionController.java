package com.huang.controller;

import com.huang.aop.annoation.LogCollect;
import com.huang.aop.annoation.ValidDo;
import com.huang.base.BaseController;
import com.huang.entity.Function;
import com.huang.group.ValidAddGroup;
import com.huang.group.ValidEditGroup;
import com.huang.service.FunctionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;


/**
 * Created by huang on 2019/1/30.
 */
@Controller
@RequestMapping("/sys/fun")
@Validated
public class FunctionController extends BaseController<FunctionController> {
    @Autowired
    private FunctionService functionService;
    @RequiresPermissions("fun:main")
    @RequiresAuthentication
    @RequestMapping("/index.action")
    public String index(){
        return "sys/function/index";
    }

    //当拥有fun:main 或者role:auth 才能执行
    @RequiresPermissions(value = {"fun:main","role:auth"},logical = Logical.OR)
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/menuTable.action")
    public Object menuTable(){
        return functionService.treeTable();
    }


    @LogCollect(msg = "访问权限增加页面")
    @RequiresPermissions("fun:add")
    @RequiresAuthentication
    @RequestMapping("/add.action")
    public String add(HttpServletRequest request,@NotNull(message = "functionId不能为空") Integer id){
        request.setAttribute("menu",functionService.findById(id));
        return "sys/function/add";
    }

    @RequiresPermissions("fun:add")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/addfunction.action")
    @ValidDo
    public Object addfunction(@Validated(ValidAddGroup.class) Function function, BindingResult bindingResult){
        functionService.addMenu(function);
        return success(sysInfoBean.getSuccessInfo());
    }

    @RequiresPermissions("fun:edit")
    @RequiresAuthentication
    @RequestMapping("/edit.action")
    public String edit(HttpServletRequest request,@NotNull(message = "functionId不能为空")Integer id){
        request.setAttribute("menu",functionService.findById(id));
        return "sys/function/edit";
    }

    @RequiresPermissions("fun:edit")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/editfunction.action")
    @ValidDo
    public Object editfunction(@Validated(ValidEditGroup.class) Function function, BindingResult bindingResult){
        functionService.editMenu(function);
        return success(sysInfoBean.getSuccessInfo());
    }

    @RequiresPermissions("fun:del")
    @ResponseBody
    @RequiresAuthentication
    @RequestMapping("/delfunction.action")
    public Object delfunction(@NotNull(message = "functionId不能为空")Integer functionId){
        functionService.deleteAllFunById(functionId);
        return success(sysInfoBean.getSuccessInfo());
    }

}
