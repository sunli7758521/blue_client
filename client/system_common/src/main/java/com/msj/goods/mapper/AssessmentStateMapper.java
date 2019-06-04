package com.msj.goods.mapper;

import com.msj.goods.entity.AssessmentState;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoyan
 * @since 2019-04-20
 */
public interface AssessmentStateMapper extends BaseMapper<AssessmentState> {

    int insertassessmentState(@Param("userId") Integer userId, @Param("levelId") Integer levelId, @Param("state") Integer state, @Param("shenQingFangShi") String shenQingFangShi, @Param("carateTime") Date carateTime);
    List<AssessmentState> selectAssessmentState( @Param("userId") Integer userId);

    void updatezt();

}
