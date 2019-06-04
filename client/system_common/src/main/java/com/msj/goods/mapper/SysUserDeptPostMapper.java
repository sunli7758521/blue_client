package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.SysUserDeptPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户属于那个部门管理那个部门 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-12-30
 */
public interface SysUserDeptPostMapper extends BaseMapper<SysUserDeptPost> {

    List<SysUserDeptPost> findSysUserDeptPost();

    List<SysUserDeptPost> findSudpByUserId(@Param("userId") Integer userId);

    /** 通过部门id 查询谁管理本部门*/
    SysUserDeptPost selectDeptIdUserId(@Param("deptId") Integer deptId);


    int findSudpByUserId2(@Param("userId") Integer userId);

    int findSudpByUserId3(@Param("userId") Integer userId);

}
