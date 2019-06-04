package com.msj.goods.service;

import com.msj.goods.entity.SysUserPost;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户与岗位关联表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysUserPostService extends IService<SysUserPost> {

    SysUserPost findByUserId(Integer userId);
}
