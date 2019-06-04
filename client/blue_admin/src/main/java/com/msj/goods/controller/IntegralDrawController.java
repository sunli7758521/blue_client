package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.Integral;
import com.msj.goods.entity.IntegralDraw;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.IntegralDrawService;
import com.msj.goods.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyan @Date 2019/2/21 17:32
 */
@RestController
@RequestMapping("/integralDraw")
@Api(description = "抽奖类")
public class IntegralDrawController {
    @Autowired
    public IntegralDrawService integralDrawService;
    @Autowired
    private IntegralService integralService;

    @Log(title = "查询奖品列表")
    @PostMapping(value = "/find")
    @ApiOperation(value = "查询奖品", notes = "所有的数据")
    @ApiImplicitParam(name = "", value = "json对象", required = true)
    public JsonResult find() {
        SysUser user = ShiroUtils.getUserEntity();
        EntityWrapper<Integral> idw = new EntityWrapper<Integral>();
        EntityWrapper<IntegralDraw> wrapper = new EntityWrapper<IntegralDraw>();
        wrapper.eq("status", '0');
        List<IntegralDraw> lists = integralDrawService.selectList(wrapper);
        Integral integral = integralService.selectOne(idw);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gzdList", lists);
        map.put("integral", integral);
        JsonResult jr = JsonResult.success(map, JsonResultConstants.SUCCESS);
        System.out.println(jr.getData().toString());
        return jr;
    }

    @Log(title = "抽中的奖品")
    @PostMapping(value = "/saveIntegralDraw")
    @ApiOperation(value = "抽中的奖品", notes = "所有的数据")
    @ApiImplicitParam(name = "", value = "json对象", required = true)
    public JsonResult saveIntegralDraw() {
        EntityWrapper<IntegralDraw> wrapper = new EntityWrapper<IntegralDraw>();
        wrapper.eq("status", "0");
        List<IntegralDraw> lists = integralDrawService.selectList(wrapper);
        return JsonResult.success(lists, JsonResultConstants.SUCCESS);
    }

    /**
     * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
     *
     * @param ids
     * @return random：奖品列表prizes中的序列（prizes中的第random个就是抽中的奖品）
     */
    public int getPrizeIndex(List<IntegralDraw> ids) {
        DecimalFormat df = new DecimalFormat("######0.00");
        int random = -1;
        try {
            // 计算总权重
            double sumWeight = 0;
            for (IntegralDraw id : ids) {
                sumWeight += id.getWeight();
            }

            // 产生随机数
            double randomNumber;
            randomNumber = Math.random();

            // 根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < ids.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(ids.get(i).getWeight())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(ids.get(i - 1).getWeight())) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }
}
