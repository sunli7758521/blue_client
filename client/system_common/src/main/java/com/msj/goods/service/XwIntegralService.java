package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.XwIntegral;

/**
 * <p>
 * 行为c积分管理 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface XwIntegralService extends IService<XwIntegral> {
    /**
     *  查询个人部门下所有的行为积分项
     */
    PageInfo<XwIntegral> selectDeptIntegral(SysUser user,String pageSize, String pageNum, String search);
}
