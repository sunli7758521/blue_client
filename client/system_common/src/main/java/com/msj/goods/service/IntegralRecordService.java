package com.msj.goods.service;

import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.IntegralRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品兑换记录表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralRecordService extends IService<IntegralRecord> {

    /**
     *  积分商城商品兑换记录
     */
    PageInfo<IntegralRecord> selectIntegralGoodsRecord(String pageSize, String pageNum,String goodId);

    // 查询所有人的兑换记录
    List<Map> selectAllCountYDScore();
}
