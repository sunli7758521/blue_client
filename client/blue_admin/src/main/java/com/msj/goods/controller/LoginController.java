package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.common.web.base.AjaxResult;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.SysRole;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.SysUserRole;
import com.msj.goods.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun li @Date 2018/11/5 16:59 @Description
 */
@RestController
@RequestMapping("/user")
@Api(description = "员工信息")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private SysUserPostService sysUserPostService;

    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @PostMapping(value = "/login")
    @Log(title = "员工登录")
    @ApiOperation(value = "员工登录", notes = "员工登录系统")
    @ApiImplicitParam(name = "LoginVo", value = "员工json对象", required = true)
    public AjaxResult userLogin(
            @RequestParam("phone") String phone, @RequestParam("password") String password) {
        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        String msg = "用户或密码错误";
        try {
            // 设置session 永远session
            /** ShiroUtils.setSession(); */
//            int count = sysUserService.selectCount(new EntityWrapper<SysUser>());
//            boolean time = DateUtils.beforeDateTime("2019-03-20 12:10:50");
//            if (count > 30 || time == false) {
//                return AjaxResult.error(msg);
//            }
            subject.login(token);
            SysUser user = ShiroUtils.getUserEntity();
            EntityWrapper<SysUserRole> ew = new EntityWrapper<SysUserRole>();
            ew.eq("user_id", user.getUserId());
            SysUserRole userRole = sysUserRoleService.selectOne(ew);
            SysRole role = sysRoleService.selectById(userRole.getRoleId());
            String rolekey = role.getRoleKey();
            List<String> list = new ArrayList<String>();
            list.add("admin");
            list.add("superAdmin");
            list.add("manager");
            list.add("generalManager");
            // list.add("competentLevel");
            list.add("director");
            list.add("function");
            if (list.contains(rolekey)) {
                return AjaxResult.success(rolekey + "=" + user.getUserId());
            } else if (rolekey.equalsIgnoreCase("competentLevel")) {
                return AjaxResult.success("competentLevel" + "=" + user.getUserId());
            } else {
                return AjaxResult.success("common" + "=" + user.getUserId());
            }

        } catch (AuthenticationException e) {
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return AjaxResult.error(msg);
        }
    }

    @GetMapping("unauth")
    public JsonResult unAuth() {
        return JsonResult.failure(1001, "登录超时，请重新登录");
    }

    @GetMapping("/logout")
    @ResponseBody
    @ApiOperation(value = "退出系统", notes = "用户退出系统")
    public JsonResult logout() {
        ShiroUtils.logout();
        return JsonResult.success("退出！");
  }
}
