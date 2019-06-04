package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.entity.SysDept;
import com.msj.goods.mapper.SysDeptMapper;
import com.msj.goods.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {


    /**
     * 加载角色部门（数据权限）列表树
     */
    @Override
    public List<Map<String, Object>> selectAllDept() {

          List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
           EntityWrapper <SysDept> ew = new EntityWrapper<>();
           List<SysDept> deptList = this.baseMapper.selectList(ew);
           trees = getTrees(deptList);
        return trees;
    }

    /**
     *  查询所有部门
     * */
    @Override
    public List<Map> selectDeptList() {
        return this.baseMapper.selectDeptList();
    }

    /** 经理 级别 同级的部门展示  */
    @Override
    public List<Map> selectManagerDeptList(Integer isApprover) {
        return this.baseMapper.selectManagerDeptList(isApprover);
    }

    /** 通过部门查询  部门名称 */
    @Override
    public Map selectByDeptId(Integer deptId) {
        return this.baseMapper.selectByDeptId(deptId);
    }


    private List<Map<String, Object>> getTrees(List<SysDept> deptList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (SysDept dept : deptList)
        {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
            {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("id", dept.getDeptId());
                deptMap.put("pId", dept.getParentId());
                deptMap.put("name", dept.getDeptName());
                deptMap.put("title", dept.getDeptName());
                trees.add(deptMap);
            }
        }
        return trees;
    }


    public List<SysDept> findDeptAndSubUsers(SysDept dept,List<SysDept> lists){
        EntityWrapper<SysDept> ew = new EntityWrapper<SysDept>();
        ew.eq("parent_id",dept.getDeptId());
        List<SysDept> depts = this.selectList(ew);
        lists.addAll(depts);
        for(SysDept s : depts){
            findDeptAndSubUsers(s,lists);
        }
        return lists;
    }
}
