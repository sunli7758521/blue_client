package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.constants.Constants;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.GiveLike;
import com.msj.goods.entity.Integral;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.GiveLikeService;
import com.msj.goods.service.IntegralService;
import com.msj.goods.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyan
 * @date 2018/12/18 14:20
 */
@RestController
@RequestMapping("/lovePraise")
@Api(description = "爱心点赞")
public class LovePraiseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private GiveLikeService giveLikeService;
    @Autowired
    private IntegralService integralService;

    @PostMapping(value = "/lovePraiseList")
    @ApiOperation(value = "爱心点赞页面", notes = "数据")
    @ApiImplicitParam(name = "", value = "json对象", required = true)
    public JsonResult lovePraiseList(
            @RequestParam(value = "search", required = false) String search) {
        SysUser user = ShiroUtils.getUserEntity(); // 获取当前用户81 曹飞 84赵建 72李伟涛
        // SysUser user = sysUserService.selectById(84);
        if (user == null) {
            return JsonResult.failure(2018, "请重新登录");
        }
        // SysUser user = sysUserService.selectById(23);
        String pp = giveLikeService.lovePraiseLogList(user.getUserId()); // 获取点赞人
        user.setRemark(pp);
        // 查询本部门用户，当前用户除外
        List<SysUser> userList = giveLikeService.findUserAll(user, search);
        if (userList.isEmpty()) return null; // userList 为空返回
        for (SysUser su : userList) {
            pp = giveLikeService.lovePraiseLogList(su.getUserId());
            su.setRemark(pp);
        }
        Integer loveUser = giveLikeService.getTswkPraiseUsers(user.getUserId());
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("userList", userList);
        maps.put("user", user);
        maps.put("loveUser", loveUser);
        return JsonResult.success(maps, JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/updataUser")
    @ApiOperation(value = "爱心点赞页面", notes = "点赞某个用户")
    @ApiImplicitParam(name = "", value = "json对象", required = true)
    public JsonResult updataUser(@RequestParam(value = "userId", required = false) String userId) {
        String falg = "1";
        SysUser user = ShiroUtils.getUserEntity(); // 获取当前用户
        SysUser u = sysUserService.selectById(user.getUserId());
        Long oveIntegral = user.getLoveIntegral();
        if (u == null) {
            falg = "0";
            return JsonResult.failure(falg);
        }
        if (oveIntegral == 0) {
            return JsonResult.failure(falg);
        }
        u.setLoveIntegral(0L);

        sysUserService.updateById(u);
        /** 爱心点赞 需要更新积分榜总积分，积分榜商城的总积分 */
        EntityWrapper<Integral> ew = new EntityWrapper<Integral>();
        ew.eq("user_id", Integer.valueOf(userId));
        Integral integral = integralService.selectOne(ew); // 获取受益用户
        if (integral == null) {
            falg = "0";
            return JsonResult.failure(falg);
        }
        synchronized (integral){
            int countIntefral = integral.getCountIntegral(); // 积分榜总积分
            int goodCountIntegral = integral.getGoodCountIntegral(); // 总积分
            int love = Constants.LOVE_NUM;
            countIntefral = countIntefral + love;
            goodCountIntegral = goodCountIntegral + love;
            integral.setCountIntegral(countIntefral);
            integral.setGoodCountIntegral(goodCountIntegral);
        integralService.insertOrUpdate(integral);
        /** 日志 */
        GiveLike giveLike = new GiveLike();
        giveLike.setUserId(user.getUserId()); // 点赞用户
        giveLike.setBenefitId(Integer.valueOf(userId));
        giveLike.setCareateTime(new Date());
        giveLike.setLoveIntegral(love); // 爱心点赞积分
        giveLike.setCountIntefral(countIntefral); // 总积分
        giveLikeService.insert(giveLike);
        giveLikeService.saveIntegralApp(userId);
        }
        return JsonResult.failure(falg);
    }
}
