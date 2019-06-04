package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.IntegralRecord;
import com.msj.goods.mapper.IntegralRecordMapper;
import com.msj.goods.service.IntegralRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品兑换记录表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class IntegralRecordServiceImpl extends ServiceImpl<IntegralRecordMapper, IntegralRecord> implements IntegralRecordService {

    /**
     *  积分商城商品兑换记录
     */
    @Override
    public PageInfo<IntegralRecord> selectIntegralGoodsRecord(String pageSize, String pageNum,String goodId) {

        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        EntityWrapper<IntegralRecord> ew = new EntityWrapper<IntegralRecord>();
        ew.eq("g_id",goodId);
        ew.eq("status","1");
        List<IntegralRecord> list =  this.baseMapper.selectList(ew);
        return new PageInfo<>(list);
    }

    // 查询所有人的兑换记录
    @Override
    public List<Map> selectAllCountYDScore() {
        return this.baseMapper.selectAllCountYDScore();
    }
}
