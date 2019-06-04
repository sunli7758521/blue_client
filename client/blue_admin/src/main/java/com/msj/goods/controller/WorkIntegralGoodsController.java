package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.Integral;
import com.msj.goods.entity.IntegralGoods;
import com.msj.goods.entity.IntegralRecord;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.IntegralGoodsService;
import com.msj.goods.service.IntegralRecordService;
import com.msj.goods.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sun li
 * @Date 2018/11/10 13:37
 * @Description 积分商城
 */
@RestController
@RequestMapping("/integralGoods")
@Api(description = "积分商城")
public class WorkIntegralGoodsController {

    @Autowired
    private IntegralGoodsService integralGoodsService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private IntegralRecordService integralRecordService;

    @PostMapping(value = "/selectIntegralGoodsList")
    @Log(title = "积分商城列表")
    @ApiOperation(value="积分商城列表", notes="积分商城列表")
    @ApiImplicitParam(name = "pageModelParams",value = "json对象",required = true)
    public JsonResult selectIntegralList(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        PageInfo<IntegralGoods> pageInfo = integralGoodsService.selectIntegralGoodsList(pageSize,pageNum);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    @GetMapping(value = "/selectIntegralGoodsDetail/{goodId}")
    @Log(title = "积分商城商品详情页")
    @ApiOperation(value="积分商城商品详情页", notes="积分商城商品详情页")
    @ApiImplicitParam(name = "goodId",value = "json对象",required = true)
    public JsonResult selectIntegralGoodsDetail(@PathVariable("goodId") String goodId){
        return JsonResult.success(integralGoodsService.selectById(goodId) , JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/selectIntegralGoodsDetailRecord/{goodId}")
    @Log(title = "积分商城他们本商品兑换记录")
    @ApiOperation(value="积分商城他们本商品兑换记录", notes="积分商城他们本商品兑换记录")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectIntegralGoodsDetailRecord(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum,
                                                      @PathVariable("goodId") String goodId){
        PageInfo<IntegralRecord> pageInfo = integralRecordService.selectIntegralGoodsRecord(pageSize,pageNum,goodId);
        return JsonResult.success(pageInfo, JsonResultConstants.SUCCESS);
    }
    @PostMapping(value = "/selectIntegralGoodsDetailRecordCountNum/{goodId}")
    @Log(title = "积分商城他们本商品兑换记录数量")
    @ApiOperation(value="积分商城他们本商品兑换记录数量", notes="积分商城他们本商品兑换记录数量")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectIntegralGoodsDetailRecordCountNum(@PathVariable("goodId") String goodId){
       EntityWrapper<IntegralRecord> ew  = new  EntityWrapper<IntegralRecord>();
        ew.eq("g_id",goodId);
        ew.eq("status",1);
       Integer mun  = integralRecordService.selectCount(ew);
        return JsonResult.success(mun, JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/selectIntegralGoodsKYIntegral")
    @Log(title = "积分商城剩余可用积分")
    @ApiOperation(value="积分商城剩余可用积分", notes="积分商城剩余可用积分")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectIntegralGoodsKYIntegral(){
       SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        EntityWrapper<Integral> ew = new  EntityWrapper<Integral>();
        ew.eq("user_id",user.getUserId());
        return JsonResult.success(integralService.selectOne(ew).getGoodCountIntegral(), JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/selectIntegralGoodsRecord")
    @Log(title = "自己积分商城兑换记录列表")
    @ApiOperation(value="自己积分商城兑换记录列表", notes="自己积分商城兑换记录列表")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectIntegralGoodsRecord(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralRecord> pageInfo = integralGoodsService.selectIntegralGoodsRecord(user,pageSize,pageNum);
        return JsonResult.success(pageInfo, JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/selectIntegralAddGoods")
    @Log(title = "确认兑换商品")
    @ApiOperation(value="确认兑换商品", notes="确认兑换商品")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectIntegralAddGoods(@RequestParam("goodId") String goodId){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        boolean result = integralGoodsService.insetIntegralGoodsRecord(user,goodId);
        return result ? JsonResult.success(result,JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }
}
