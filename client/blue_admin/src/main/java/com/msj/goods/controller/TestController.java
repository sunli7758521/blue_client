package com.msj.goods.controller;

import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.web.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sun li
 * @Date 2018/11/21 13:42
 * @Description
 */
@RestController
@Api(description = "测试接口")
@RequestMapping("/test")
public class TestController {


    @PostMapping(value = "/test")
    @Log(title = "添加一条数据的审批")
    @ApiOperation(value="添加一条数据的审批", notes="添加一条数据的审批数据")
    @ApiImplicitParam(name = "integralApproval",value = "json对象",required = true)
    public JsonResult addIntegralApprover(  ){
        return null;
    }
}
