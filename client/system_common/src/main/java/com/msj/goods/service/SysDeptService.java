package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.msj.goods.entity.SysDept;

import java.util.List;
import java.util.Map;

/**
 * 部门表 服务类
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 加载角色部门（数据权限）列表树
     */
    List<Map<String, Object>> selectAllDept();

    List<Map> selectManagerDeptList(Integer isApprover);

    Map selectByDeptId(Integer deptId);

    /**
     * 查询所有部门
     */
    List<Map> selectDeptList();

    List<SysDept> findDeptAndSubUsers(SysDept dept, List<SysDept> lists);
}
