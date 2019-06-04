package com.msj.goods.mapper;

import com.msj.goods.entity.IntegralRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品兑换记录表 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralRecordMapper extends BaseMapper<IntegralRecord> {

    // 查询所有人的兑换记录
    List<Map> selectAllCountYDScore();
}
