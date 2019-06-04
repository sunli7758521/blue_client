package com.msj.goods.controller;

import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author sun li
 * @Date 2018/11/8 8:44
 * @Description 企业公司部门树形结构
 */
@RestController
@RequestMapping("/company")
@Api(description = "公司部门树形结构")
public class CompanyController {

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping(value = "/selectAllDept")
    @ApiOperation(value="公司部门树形结构", notes="公司部门树形结构展示数据")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectAllDept(){
        List<Map<String, Object>> trees = sysDeptService.selectAllDept();
        return JsonResult.success(trees, JsonResultConstants.SUCCESS);
    }

}
