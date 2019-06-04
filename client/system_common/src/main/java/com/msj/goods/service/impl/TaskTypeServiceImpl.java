package com.msj.goods.service.impl;

import com.msj.goods.entity.TaskType;
import com.msj.goods.mapper.TaskTypeMapper;
import com.msj.goods.service.TaskTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务类型表 服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
@Service
public class TaskTypeServiceImpl extends ServiceImpl<TaskTypeMapper, TaskType> implements TaskTypeService {

}
