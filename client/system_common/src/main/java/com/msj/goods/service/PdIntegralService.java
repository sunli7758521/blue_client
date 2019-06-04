package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.PdIntegral;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.XwIntegral;

/**
 * <p>
 * 品德A积分管理 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface PdIntegralService extends IService<PdIntegral> {

    /**
     *  查询个人部门下所有的品德积分项
     */
    PageInfo<XwIntegral> selectDeclareMoral(SysUser user, String pageSize, String pageNum, String search);
}
