package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品兑换记录日志表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralRecordLog extends Model<IntegralRecordLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "record_log_id", type = IdType.AUTO)
    private Integer recordLogId;

    /**
     * 记录商品名称
     */
    private String recordName;

    /**
     * 记录商品封面
     */
    private String recordImg;

    /**
     * 兑换积分
     */
    private Integer dhIntegral;

    /**
     * 兑换姓名
     */
    private String userName;

    /**
     * 兑换人手机号
     */
    private Long userPhone;

    /**
     * 剩余积分
     */
    private Integer syIntegral;

    /**
     * 兑换时间
     */
    private Date dhCreateTime;

    /**
     * 审核时间
     */
    private Date shTime;

    /**
     * 状态(0 审核通过 ，1审核不通过)
     */
    private Integer status;

    /**
     * 商品id
     */
    private Integer gId;

    /**
     * 备注
     */
    private String remark;

    public Integer getRecordLogId() {
        return recordLogId;
    }

    public void setRecordLogId(Integer recordLogId) {
        this.recordLogId = recordLogId;
    }
    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
    public String getRecordImg() {
        return recordImg;
    }

    public void setRecordImg(String recordImg) {
        this.recordImg = recordImg;
    }
    public Integer getDhIntegral() {
        return dhIntegral;
    }

    public void setDhIntegral(Integer dhIntegral) {
        this.dhIntegral = dhIntegral;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getSyIntegral() {
        return syIntegral;
    }

    public void setSyIntegral(Integer syIntegral) {
        this.syIntegral = syIntegral;
    }
    public Date getDhCreateTime() {
        return dhCreateTime;
    }

    public void setDhCreateTime(Date dhCreateTime) {
        this.dhCreateTime = dhCreateTime;
    }
    public Date getShTime() {
        return shTime;
    }

    public void setShTime(Date shTime) {
        this.shTime = shTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.recordLogId;
    }

    @Override
    public String toString() {
        return "IntegralRecordLog{" +
        "recordLogId=" + recordLogId +
        ", recordName=" + recordName +
        ", recordImg=" + recordImg +
        ", dhIntegral=" + dhIntegral +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", syIntegral=" + syIntegral +
        ", dhCreateTime=" + dhCreateTime +
        ", shTime=" + shTime +
        ", status=" + status +
        ", gId=" + gId +
        ", remark=" + remark +
        "}";
    }
}
