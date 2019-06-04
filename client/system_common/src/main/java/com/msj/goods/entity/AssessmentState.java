package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoyan
 * @since 2019-04-20
 */
public class AssessmentState extends Model<AssessmentState> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "zhu_id", type = IdType.AUTO)
    private Integer zhuId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 科目id
     */
    private Integer levelId;

    /**
     * 考核状态
     */
    private Integer state;

    /**
     * 申请方式
     */
    private String shenQingFangShi;

    /**
     * 时间
     */
    private Date carateTime;

    /** true 和false 的状态*/

    private  boolean cheack;

    public boolean isCheack() {
        return cheack;
    }

    public void setCheack(boolean cheack) {
        this.cheack = cheack;
    }

    public Integer getZhuId() {
        return zhuId;
    }

    public void setZhuId(Integer zhuId) {
        this.zhuId = zhuId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getShenQingFangShi() {
        return shenQingFangShi;
    }

    public void setShenQingFangShi(String shenQingFangShi) {
        this.shenQingFangShi = shenQingFangShi;
    }
    public Date getCarateTime() {
        return carateTime;
    }

    public void setCarateTime(Date carateTime) {
        this.carateTime = carateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.zhuId;
    }

    @Override
    public String toString() {
        return "AssessmentState{" +
        "zhuId=" + zhuId +
        ", userId=" + userId +
        ", levelId=" + levelId +
        ", state=" + state +
        ", shenQingFangShi=" + shenQingFangShi +
        ", carateTime=" + carateTime +
                ", cheack=" + cheack +
        "}";
    }
}
