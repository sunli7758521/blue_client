package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工爱心点赞表
 * </p>
 *
 * @author sun li
 * @since 2018-12-19
 */
public class GiveLike extends Model<GiveLike> {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞表的主键
     */
    @TableId(value = "d_id", type = IdType.AUTO)
    private Integer dId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 受益人的id
     */
    private Integer benefitId;
    /**
     * 爱心积分
     */
    private Integer loveIntegral;

    /*
    积分排行榜
     */
    private Integer countIntefral;
    /**
     * 受益时间
     */
    private Date careateTime;

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(Integer benefitId) {
        this.benefitId = benefitId;
    }
    public Date getCareateTime() {
        return careateTime;
    }

    public void setCareateTime(Date careateTime) {
        this.careateTime = careateTime;
    }

    public Integer getLoveIntegral() {
        return loveIntegral;
    }

    public void setLoveIntegral(Integer loveIntegral) {
        this.loveIntegral = loveIntegral;
    }

    public Integer getCountIntefral() {
        return countIntefral;
    }

    public void setCountIntefral(Integer countIntefral) {
        this.countIntefral = countIntefral;
    }

    @Override
    protected Serializable pkVal() {
        return this.dId;
    }

    @Override
    public String toString() {
        return "GiveLike{" +
        "dId=" + dId +
        ", userId=" + userId +
        ", benefitId=" + benefitId +
        ", careateTime=" + careateTime +
                ",loveIntegral=" + loveIntegral +
                ",countIntefral=" + countIntefral +
        "}";
    }
}
