package com.msj.goods.service;

import com.msj.goods.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRoleKeys(Integer userId);
}
