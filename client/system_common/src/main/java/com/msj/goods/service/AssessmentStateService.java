package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.AssessmentState;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-04-20
 */
public interface AssessmentStateService extends IService<AssessmentState> {

/**  添加 考核的用户信息 */
     int insertassessmentState(Integer userId, Integer levelId, Integer state, String shenQingFangShi, Date carateTime );

     /**查询考核的用户信息*/
     PageInfo<AssessmentState> selectAssessmentState(Integer userid);


     /** 定时器时间 的修改*/
     public void updatezt();


}
