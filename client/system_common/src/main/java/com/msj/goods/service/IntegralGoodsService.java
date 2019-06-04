package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.IntegralGoods;
import com.msj.goods.entity.IntegralRecord;
import com.msj.goods.entity.SysUser;

/**
 * <p>
 * 商品管理表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralGoodsService extends IService<IntegralGoods> {

    /**
     * 积分商城列表
     */
    PageInfo<IntegralGoods> selectIntegralGoodsList(String pageSize,String pageNum);

    /**
     * 自己积分商城兑换记录列表
     */
    PageInfo<IntegralRecord> selectIntegralGoodsRecord(SysUser user,String pageSize, String pageNum);

    /**
     * 兑换商品
     */
    boolean insetIntegralGoodsRecord(SysUser user,String goodId);
}
