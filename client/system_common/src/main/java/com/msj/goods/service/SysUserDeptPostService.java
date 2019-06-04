package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.ManageUser;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.SysUserDeptPost;

import java.util.List;

/**
 * <p>
 * 用户属于那个部门管理那个部门 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-12-30
 */
public interface SysUserDeptPostService extends IService<SysUserDeptPost> {
    /**
     *
     * 查询每个领导所管理的部门
     * */
    PageInfo<ManageUser> selectLeaderdepts(String search, SysUser user, String pageSize, String pageNum);

    List<SysUserDeptPost> findSysUserDeptPost();

    List<SysUserDeptPost> findSudpByUserId(Integer userId);

    void departOwnerPoints(List<SysUserDeptPost> listsp,Integer userId);

    /** 通过部门id 查询谁管理本部门*/
    SysUserDeptPost selectDeptIdUserId(Integer deptId);

    int findSudpByUserId2(Integer userId);

    int findSudpByUserId3(Integer userId);
}
