package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.PdIntegral;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.XwIntegral;
import com.msj.goods.mapper.PdIntegralMapper;
import com.msj.goods.mapper.XwIntegralMapper;
import com.msj.goods.service.PdIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品德A积分管理 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class PdIntegralServiceImpl extends ServiceImpl<PdIntegralMapper, PdIntegral> implements PdIntegralService {

      @Autowired
      private   XwIntegralMapper xwIntegralMapper;
    /**
     *  查询个人部门下所有的品德积分项
     */
    @Override
    public PageInfo<XwIntegral> selectDeclareMoral(SysUser user,String pageSize,String pageNum,String search) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        Integer typeId = 1 ;
        List<XwIntegral> list = xwIntegralMapper.selectDeptIntegralParentId(user.getDeptId(),search,typeId);

        return new PageInfo<>(list);
    }
}
