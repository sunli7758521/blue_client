package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.entity.Integral;
import com.msj.goods.entity.IntegralGoods;
import com.msj.goods.entity.IntegralRecord;
import com.msj.goods.entity.SysUser;
import com.msj.goods.mapper.IntegralGoodsMapper;
import com.msj.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品管理表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class IntegralGoodsServiceImpl extends ServiceImpl<IntegralGoodsMapper, IntegralGoods> implements IntegralGoodsService {

    @Autowired
    private IntegralRecordService integralRecordService;

    @Autowired
    private IntegralRecordLogService integralRecordLogService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private SysDeptService sysDeptService;
    @Override
    public PageInfo<IntegralGoods> selectIntegralGoodsList(String pageSize,String pageNum) {
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        EntityWrapper<IntegralGoods> ew = new  EntityWrapper<IntegralGoods>();
        List<IntegralGoods> list = this.baseMapper.selectList(ew);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<IntegralRecord> selectIntegralGoodsRecord(SysUser user,String pageSize,String pageNum) {
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        EntityWrapper<IntegralRecord> ew = new  EntityWrapper<IntegralRecord>();
        ew.eq("user_id",user.getUserId());
        List<IntegralRecord> list =   integralRecordService.selectList(ew);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional
    public boolean insetIntegralGoodsRecord(SysUser user, String goodId) {
        IntegralGoods good =  this.baseMapper.selectById(goodId);

        IntegralRecord recordNew = new IntegralRecord();
        recordNew.setgId(good.getGoodId());
        recordNew.setRecordImg(good.getGoodImg());
        recordNew.setDeptId(user.getDeptId());
        recordNew.setUserId(user.getUserId());
        recordNew.setDeptName(sysDeptService.selectById(user.getDeptId()).getDeptName());
        recordNew.setStatus(0);
        recordNew.setDhCreateTime(DateUtils.getNowDate());
        recordNew.setDhIntegral(good.getDhIntegral());
        recordNew.setRecordName(good.getGoodName());
        recordNew.setSyIntegral(getUserSyIntegral(user,good.getDhIntegral()));
        jianShaoGoods(good.getGoodKc(),good.getYdhNum(),goodId);
        recordNew.setUserPhone(Long.parseLong(user.getPhonenumber()));
        recordNew.setUserName(user.getUserName());
        recordNew.setRemark(good.getGoodDetails());
        boolean flag =  integralRecordService.insert(recordNew);

        if (flag) {
            return true;
        }
        return false;
    }
    /** 查询员工剩余积分的积分 */
    private Integer getUserSyIntegral(SysUser user, Integer dhIntegral) {
        EntityWrapper<Integral> ew = new EntityWrapper<Integral>();
        ew.eq("user_id",user.getUserId());
        Integral integral =  integralService.selectOne(ew);
        synchronized (integral){
            if(integral != null){
                integral.setGoodCountIntegral(integral.getGoodCountIntegral() - dhIntegral);
                integralService.updateById(integral);
            }
        }

      Integer syCount =  integral.getCountIntegral();
        return syCount;
    }





    /**
     *  减少商品
     *
     */
    private Integer jianShaoGoods(Integer goodKc, Integer ydhNum, String goodId) {

        IntegralGoods  goods = this.baseMapper.selectById(goodId);
        synchronized (goods){
            goods.setGoodCount(goods.getGoodCount()-1);
            goods.setGoodKc(goodKc-1);
            if(ydhNum ==null){
                ydhNum = 0;
            }
            goods.setYdhNum(ydhNum+1);
            goods.setGoodId(Integer.parseInt(goodId));
        }

               Integer flag =  this.baseMapper.updateById(goods);
               if (flag>1) {
                   return 1;
               }
               return 0;
    }
}
