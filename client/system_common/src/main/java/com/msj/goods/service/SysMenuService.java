package com.msj.goods.service;

import com.msj.goods.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectPermsByUserId(Integer userId);
}
