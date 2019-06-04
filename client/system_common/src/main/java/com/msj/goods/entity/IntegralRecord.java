package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品兑换记录表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralRecord extends Model<IntegralRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主键
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

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
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date dhCreateTime;

    /**
     * 审核时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date shTime;

    /**
     * 状态(0 审核中 1,审核通过 ，2审核不通过)
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

    /**
     *  兑换人 userId
     */
    private Integer  userId;

    /**
     * 所属部门
     */
    private String deptName;

    /**
     * 所属部门id
     */
    private Integer deptId;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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
        return this.recordId;
    }

    @Override
    public String toString() {
        return "IntegralRecord{" +
        "recordId=" + recordId +
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
        ", userId=" + userId +
        ", deptId=" + deptId +
        ", deptName=" + deptName +
        "}";
    }
}
