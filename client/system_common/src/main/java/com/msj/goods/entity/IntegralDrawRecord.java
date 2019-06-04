package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 积分抽奖记录
 * </p>
 *
 * @author zhaoyan
 * @since 2019-02-21
 */
public class IntegralDrawRecord extends Model<IntegralDrawRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "idr_id", type = IdType.AUTO)
    private Integer idrId;

    /**
     * 对应奖品主键
     */
    private Integer idId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录抽奖时间
     */
    private Date createTime;

    public Integer getIdrId() {
        return idrId;
    }

    public void setIdrId(Integer idrId) {
        this.idrId = idrId;
    }
    public Integer getIdId() {
        return idId;
    }

    public void setIdId(Integer idId) {
        this.idId = idId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.idrId;
    }

    @Override
    public String toString() {
        return "IntegralDrawRecord{" +
        "idrId=" + idrId +
        ", idId=" + idId +
        ", userId=" + userId +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
