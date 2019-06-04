package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 任务类型表
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
public class TaskType extends Model<TaskType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    /**
     * 任务类型名称
     */
    private String taskName;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }

    @Override
    public String toString() {
        return "TaskType{" +
        "taskId=" + taskId +
        ", taskName=" + taskName +
        "}";
    }
}
