package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.enums.BusinessType;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.ChaoSong;
import com.msj.goods.entity.IntegralApproval;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.ChaoSongService;
import com.msj.goods.service.IntegralAppUserService;
import com.msj.goods.service.IntegralApprovalService;
import com.msj.goods.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sun li
 * @Date 2018/12/11 15:49
 * @Description 用户和菜单绑定 后期撤销
 */
@RestController
@RequestMapping("/userMenu")
@Api(description = "用户和菜单绑定 后期撤销")
public class UserMenuController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private ChaoSongService chaoSongService;

    @Autowired
    private IntegralAppUserService integralAppUserService;

    /**
     *   用户对应餐单项id   接口
     *   @RequestParam Long approvalId
     */
    @PostMapping(value = "/selectUserIdAndMenuId")
    @Log(title = "用户对应餐单项id")
    @ApiOperation(value="用户对应餐单项id", notes="用户对应餐单项id")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectUserIdAndMenuId(){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        SysUser u = sysUserService.selectById(user.getUserId());
//        integralAppUserService.se
        return JsonResult.success(u.getApplyIds() , JsonResultConstants.SUCCESS);
    }

    /**
     *   用户撤销餐单项id   接口
     */
    @GetMapping(value = "/selectDelMenu/{approvalId}")
    @Log(title = "用户对应餐单项id", businessType = BusinessType.OTHER)
    @ApiOperation(value="用户撤销餐单项id", notes="用户撤销餐单项id")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectDelMenu(@PathVariable("approvalId") String approvalId){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        SysUser u = sysUserService.selectById(user.getUserId());

              IntegralApproval integralApproval = integralApprovalService.selectById(approvalId);
                if(integralApproval !=null){
                    String appids =   u.getApplyIds().replace(integralApproval.getMenuId()," ");
                    u.setApplyIds(appids);
                }
        boolean  flag =  integralApprovalService.deleteById(integralApproval.getApprovalId());

        EntityWrapper<ChaoSong> cs = new  EntityWrapper<ChaoSong>();
        cs.eq("approval_id",integralApproval.getApprovalId());
        boolean row =  chaoSongService.delete(cs);
        boolean  result =  sysUserService.updateById(u);
        return result ? JsonResult.success(result, JsonResultConstants.SUCCESS ) : JsonResult.failure(JsonResultConstants.FAIL);
    }
}
