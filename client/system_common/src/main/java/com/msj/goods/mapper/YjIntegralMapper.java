package com.msj.goods.mapper;

import com.msj.goods.entity.YjIntegral;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 业绩B积分管理 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface YjIntegralMapper extends BaseMapper<YjIntegral> {

    /** 查询本部门 和所有 通用的菜单项 */
    List<YjIntegral> selectDeptParentList(@Param("deptId") Integer deptId, @Param("search") String search);
}
