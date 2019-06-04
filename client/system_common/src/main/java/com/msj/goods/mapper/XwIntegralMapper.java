package com.msj.goods.mapper;

import com.msj.goods.entity.XwIntegral;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 行为c积分管理 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface XwIntegralMapper extends BaseMapper<XwIntegral> {

    /**
     *  查询个人部门下所有的行为积分项 以及公共的菜单项
     */

    List<XwIntegral> selectDeptIntegralParentId(@Param("deptId") Integer deptId,
                                                @Param("search") String search,
                                                @Param("typeId") Integer typeId );
}
