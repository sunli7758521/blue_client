package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 任务标题员工表
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
public class RewardTaskUser extends Model<RewardTaskUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "rtu_id", type = IdType.AUTO)
    private Integer rtuId;

    /**
     * 任务标题主键
     */
    private Integer rtId;

    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 审批状态(0,审批中 1完成，2审批不通过，
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createDate;

    public Integer getRtuId() {
        return rtuId;
    }

    public void setRtuId(Integer rtuId) {
        this.rtuId = rtuId;
    }
    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.rtuId;
    }

    @Override
    public String toString() {
        return "RewardTaskUser{" +
        "rtuId=" + rtuId +
        ", rtId=" + rtId +
        ", userId=" + userId +
        ", state=" + state +
        ", createDate=" + createDate +
        "}";
    }
}
