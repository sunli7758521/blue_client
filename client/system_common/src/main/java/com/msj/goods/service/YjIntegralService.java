package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.XwIntegral;
import com.msj.goods.entity.YjIntegral;

/**
 * <p>
 * 业绩B积分管理 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface YjIntegralService extends IService<YjIntegral> {

    /**
     *  查询个人部门下所有的业绩积分项
     */
    PageInfo<XwIntegral> selectDeclareResults(SysUser user, String pageSize, String pageNum, String search);
}
