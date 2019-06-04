package com.msj.goods.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.URLConstant;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.*;
import com.msj.goods.service.*;
import com.msj.goods.utils.AccessTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sun li
 * @Date 2018/11/5 9:02
 * @Description
 */

@RestController
@RequestMapping("/empp")
@Api(description = "员工信息")
public class EmpController {

   // @Autowired

    @Resource
    private SysUserService sysUserService;

    @Resource
    private IntegralService integralService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserPostService sysUserPostService;

    @Resource
    private SysRoleService sysRoleService;




    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param requestAuthCode 免登临时code
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestParam(value = "authCode") String requestAuthCode) {
        //获取accessToken,注意正是代码要有异常流处理
        String accessToken = AccessTokenUtil.getToken();

        //获取用户信息
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;

        String userId = null;
        try {
            response = client.execute(request, accessToken);
            if (response.getUserid() == null) {
                String json = response.getBody();
                JSONObject jsStr = JSONObject.parseObject(json);

                return JsonResult.failureString(jsStr.get("errcode") + "", jsStr.get("errmsg") + "");
            }
            userId = response.getUserid();
            OapiUserGetResponse userResponse = getUserName(accessToken, userId);
            SysUser sysUser = sysUserService.selectPhoneUser(userResponse.getMobile());
            if (sysUser == null) {
                SysUser user = new SysUser();
                user.setAvatar(userResponse.getAvatar());
                user.setJiChuIntegral(400L);
                user.setBiaoIntegral(0L);
                user.setIsApprover(3);
                user.setIntegralStatus(1);
                user.setLoveIntegral(0L);
                user.setUserType("00");

                user.setPhonenumber(userResponse.getMobile());
                user.setUserName(userResponse.getName());
                user.setLoginName(userResponse.getName());
                user.setCreateTime(DateUtils.getNowDate());
                user.setSex(null);
                user.setDeptId(157);
                int  row = insertUser(user);
                    if(row>1){
                        System.out.println("成功");
                    }
               Integral integral = new Integral();
                integral.setUserId(user.getUserId());
                integral.setCountIntegral(400);
                integral.setAddIntegral(0);
                integral.setDelIntegral(0);
                integral.setUserName(userResponse.getName());
                integral.setUserPhone(userResponse.getMobile());
                integralService.insert(integral);
                /** 添加用户对用的职位 默认职位 */
                SysUserPost userPost = new SysUserPost();
                userPost.setUserId(user.getUserId());
                userPost.setPostId(7);
                boolean row1 = sysUserPostService.insert(userPost);

                /** 添加 shiro 框架 */
            }
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userResponse.getName(), userResponse.getMobile());
            try {
                //设置session 永远session
                ShiroUtils.setSession();
                subject.login(token);
                 return JsonResult.success("成功");
            }
            catch (AuthenticationException e) {
                String msg = "用户或密码错误";
                if (StringUtils.isNotEmpty(e.getMessage())) {
                    msg = e.getMessage();
                }
                return JsonResult.failure(msg);
            }

        }
        catch (Exception e) {
            return JsonResult.failure(e.getMessage());

        }

    }

    private int insertUser(SysUser user) {
     boolean flag = sysUserService.insert(user);
     if(flag ){
         return 1;
     } else {
         return 0;
     }
    }

    /**
     * 获取用户姓名
     *
     * @param accessToken
     * @param userId
     * @return*/
    private OapiUserGetResponse getUserName(String accessToken, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查看用户角色
     */
    @PostMapping(value = "/selectRole")
    @Log(title = "查询用户是什么角色" )
    @ApiOperation(value="查询用户是什么角色", notes="查询用户是什么角色")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectRole(){

        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
         EntityWrapper<SysUserRole> ew =  new EntityWrapper<>();
         ew.eq("user_id",user.getUserId());
         SysUserRole userRole =  sysUserRoleService.selectOne(ew);
         if (userRole != null){
               SysRole role = sysRoleService.selectById(userRole.getRoleId());
             if (role.getRoleKey().equalsIgnoreCase("admin")){
                 return JsonResult.success("admin");
             }else if(role.getRoleKey().equalsIgnoreCase("superAdmin")){
                  return JsonResult.success("superAdmin");
             }else{
                  return JsonResult.success("common");
             }
         }
        return JsonResult.success("common");
    }
}


