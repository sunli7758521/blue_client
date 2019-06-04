package com.msj.goods.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.utils.PageBean;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sun li
 * @Date 2018/11/6 11:24
 * @Description
 */
@RestController
@RequestMapping("/home")
@Api(description = "首页")
public class IndexController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;


    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private IntegralLogService integralLogService;


        /**
         *  查询冠军  首页展示    数字展示1-n
         * */
    @PostMapping(value = "/index")
    @ApiOperation(value="首页", notes="首页展示数据")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult home(){
        Map<String,String> map = new HashMap<String,String>();
        SysUser user =  ShiroUtils.getUserEntity();
        if (user !=null) {
            List<Map> list = integralApprovalService.selectCommonGJ(user.getDeptId());
            if(list.size()>0){
                for (Map mp : list) {
                    if(mp.get("userId").equals(user.getUserId())){
                        map.put("amount",mp.get("amount")+"");
                        map.put("rank",mp.get("rank")+"");
                        map.put("userId",mp.get("userId")+"");
                    }
                }
                map.put("userName",list.get(0).get("userName")+"");
                map.put("userImg",list.get(0).get("userImg")+"");
            }
            return JsonResult.success(map, JsonResultConstants.SUCCESS);
        }
        return JsonResult.failure(2018,"请重新登录");
    }
    /**
     *      首页显示所有用户的信息
     * */
    @PostMapping(value = "/list")
    @Log(title = "首页审批通过的日志列表" )
    @ApiOperation(value="首页审批通过的日志列表", notes="首页审批通过的日志列表")
    @ApiImplicitParam(name = "pageSize ,pageNum ",value = "json对象",required = true)
    public JsonResult userLogin(@RequestParam("userId") String userId,@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){

        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null ){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<AppLog> pageInfo = integralApprovalService.selectOneIntegralApproval(user,pageSize,pageNum);

        return JsonResult.success(pageInfo  , JsonResultConstants.SUCCESS);
    }
}
