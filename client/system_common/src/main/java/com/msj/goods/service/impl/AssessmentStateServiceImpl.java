package com.msj.goods.service.impl;

import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.AssessmentState;
import com.msj.goods.mapper.AssessmentStateMapper;
import com.msj.goods.service.AssessmentStateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-04-20
 */
@Service
@Transactional
public class AssessmentStateServiceImpl extends ServiceImpl<AssessmentStateMapper, AssessmentState> implements AssessmentStateService {

    @Autowired
    private AssessmentStateMapper assessmentStateMapper;

    @Override
    public int insertassessmentState(Integer userId, Integer levelId, Integer state, String shenQingFangShi, Date carateTime) {
        int rows=assessmentStateMapper.insertassessmentState(userId,levelId,state,shenQingFangShi,carateTime);
        return rows;
    }


    @Override
    public PageInfo<AssessmentState> selectAssessmentState(Integer userid) {
        List<AssessmentState> shuai =assessmentStateMapper.selectAssessmentState(userid);
        return new PageInfo<>(shuai);
    }

    @Override
    public void updatezt() {
        assessmentStateMapper.updatezt();
    }

}
