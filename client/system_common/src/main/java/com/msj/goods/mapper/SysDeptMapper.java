package com.msj.goods.mapper;

import com.msj.goods.entity.SysDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     *  查询所有部门
     * */
    List<Map> selectDeptList();

    /** 经理 级别 同级的部门展示  */
    List<Map> selectManagerDeptList(@Param("isApprover") Integer isApprover);

    /** 通过部门查询  部门名称 */
    Map selectByDeptId(@Param("deptId") Integer deptId);

}
