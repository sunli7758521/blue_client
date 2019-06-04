package com.msj.goods.mapper;

import com.msj.goods.entity.IntegralType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分类别表 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralTypeMapper extends BaseMapper<IntegralType> {

    /**
     * 查询所有积分类型
     * */
    List<Map> selectIntegralTypeList();
}
