package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.Integral;
import com.msj.goods.entity.SysUser;

import java.util.Map;

/**
 * <p>
 * 积分表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralService extends IService<Integral> {

    /**
     *  查询所有积分榜展示数据
     *  以及所有的检索条件
     */
    PageInfo<Map> selectAllList(SysUser user, Integer pageSize, Integer pageNum, Integer times, String deptId, String postId, String typeId, String spTime1, String spTime2, String search);

    Integral selectdan(Integer userId);

    int updatexiugai(Integer integralId,Integer countIntegral,Integer addIntegral,Integer goodCountIntegral );


}
