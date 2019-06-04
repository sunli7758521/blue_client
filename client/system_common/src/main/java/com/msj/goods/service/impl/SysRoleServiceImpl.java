package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.entity.SysRole;
import com.msj.goods.mapper.SysRoleMapper;
import com.msj.goods.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Autowired
    private SysRoleMapper roleMapper;
    
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Integer userId) {
        List<SysRole> perms = roleMapper.selectRolesByUserId(userId);

        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perms))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
