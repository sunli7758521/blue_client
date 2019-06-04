package com.msj.goods.service;

import com.msj.goods.entity.IntegralType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分类别表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralTypeService extends IService<IntegralType> {

    /**
     * 查询所有积分类型
     * */
    List<Map> selectIntegralTypeList();
}
