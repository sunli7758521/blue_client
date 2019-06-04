package com.msj.goods.mapper;

import com.msj.goods.entity.Integral;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 积分表 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralMapper extends BaseMapper<Integral> {

    Integral selectdan(@Param("userId") Integer userId );

    int updatexiugai( @Param("integralId") Integer integralId, @Param("countIntegral") Integer countIntegral,@Param("addIntegral")   Integer addIntegral,@Param("goodCountIntegral")   Integer goodCountIntegral );


}
