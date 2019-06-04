package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.*;
import com.msj.goods.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sun li
 * @Date 2018/11/7 16:01
 * @Description
 */
@RestController
@RequestMapping("/personal")
@Api(description = "个人中心")
public class PersonalController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysUserPostService sysUserPostService;
    @Autowired
    private IntegralApprovalService integralApprovalService;


    @PostMapping(value = "/indexHead")
    @ApiOperation(value="查询所有部门", notes="展示所有部门数据")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult indexHead(){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("name",user.getUserName());
        map.put("base",user.getJiChuIntegral());
        map.put("avatar",user.getAvatar());
        Long integral = integralApprovalService.selectCountIntegral(user.getUserId());
        if(integral != null){
            map.put("amount",integral);
        }
        SysDept dept = sysDeptService.selectById(user.getDeptId());
        if(dept !=null){
            map.put("deptName",dept.getDeptName());
        }

        EntityWrapper<SysUserPost> ew1 = new EntityWrapper<>();
        ew1.eq("user_id",user.getUserId());
        SysUserPost userPost = sysUserPostService.selectOne(ew1);
        if(userPost !=null){
            SysPost post = sysPostService.selectById(userPost.getPostId());
            map.put("postName",post.getPostName());
        }


        return JsonResult.success(map , JsonResultConstants.SUCCESS);
    }
    /**======= todo===================================== */
    @PostMapping(value = "/indexHeadBaseIntegral")
    @ApiOperation(value="个人基础积分", notes="个人基础积分信息")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult indexHeadBase(){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        Map<String,Object> map = new HashMap<>();
        /** 查询一个可以有都个职位 */
        /*
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
         ew.eq("user_id",user.getUserId());*/

        SysUserPost userPost = sysUserPostService.selectById(user.getUserId());
        SysPost post = sysPostService.selectById(userPost.getPostId());
        map.put("postIntegral",post.getIntegral());
        map.put("base",user.getJiChuIntegral());
        map.put("avatar",user.getAvatar());
        map.put("userName",user.getUserName());
        map.put("lengthService","");
        map.put("schooling","");
        map.put("theTitle","");
        map.put("honor","");
        map.put("specialty","");
        return JsonResult.success(map, JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/indexEcharts")
    @ApiOperation(value="查询个人的Echarts", notes="查询个人积分Echarts")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult indexEcharts(@RequestParam("userId") String userId){

        Map<String,Object> map = new HashMap   <>();
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        String uId = "";
        if(userId !=null && userId != ""){
            uId = userId;
        }else {
            uId = user.getUserId()+"";
        }

        Map approval = integralApprovalService.selectAddAndCountDelIntegral(Integer.parseInt(uId));
        if(approval != null){
            map.put("addIntegral", approval.get("addIntegral"));
            map.put("countIntegral",approval.get("countIntegral"));
            map.put("delIntegral", approval.get("delIntegral"));
            map.put("baseIntegral",approval.get("baseIntegral"));
        }

         return JsonResult.success(map , JsonResultConstants.SUCCESS);
    }
}
