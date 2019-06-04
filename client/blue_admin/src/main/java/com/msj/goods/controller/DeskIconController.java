package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.Gzd;
import com.msj.goods.service.GzdService;
import com.msj.goods.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaoyan @Date 2019/2/16 10:15
 */
@RestController
@RequestMapping("/deskIcon")
@Api(description = "工作台")
public class DeskIconController {
    @Autowired
    private GzdService gzdService;
    @Autowired
    private IntegralService integralService;

    @Log(title = "查询工作台图标")
    @PostMapping(value = "/find")
    @ApiOperation(value = "查询工作台图标", notes = "所有的数据")
    @ApiImplicitParam(name = "", value = "json对象", required = true)
    public JsonResult find() {
        // SysUser user = ShiroUtils.getUserEntity();
        // EntityWrapper<Integral> idw = new EntityWrapper<Integral>();
        // idw.eq("good_count_integral", user.getUserId());
        EntityWrapper<Gzd> gzdew = new EntityWrapper<Gzd>();
        gzdew.eq("status", 0);
        List<Gzd> lists = gzdService.selectList(gzdew);
        JsonResult jr = JsonResult.success(lists, JsonResultConstants.SUCCESS);
        System.out.println(jr.getData());
        return jr;
  }
}
