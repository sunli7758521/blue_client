package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作台应用管理
 *
 * @author sun li
 * @since 2018-11-05
 */
public class Gzd extends Model<Gzd> {

    private static final long serialVersionUID = 1L;

    /**
     * 工作台主键
     */
    @TableId(value = "gzt_id", type = IdType.AUTO)
    private Integer gztId;

    /**
     * 应用图标
     */
    private String yyImg;

    /**
     * 应用标题
     */
    private String yyTitle;

    /**
     * 应用管理
     */
    private String yyType;

    /**
     * 状态,（0使用中 1 停用 2开启抽奖，3管理愿望分 4爱心个数）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
    /**
     * 图标在工作台对应的位置
     */
    private String sort;
    /**
     * 抽奖状态
     */
    private String lotteryStatus;

    public Integer getGztId() {
        return gztId;
    }

    public void setGztId(Integer gztId) {
        this.gztId = gztId;
    }

    public String getYyImg() {
        return yyImg;
    }

    public void setYyImg(String yyImg) {
        this.yyImg = yyImg;
    }

    public String getYyTitle() {
        return yyTitle;
    }

    public void setYyTitle(String yyTitle) {
        this.yyTitle = yyTitle;
    }

    public String getYyType() {
        return yyType;
    }

    public void setYyType(String yyType) {
        this.yyType = yyType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.gztId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(String lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }

    @Override
    public String toString() {
        return "Gzd{"
                + "gztId="
                + gztId
                + ", yyImg="
                + yyImg
                + ", yyTitle="
                + yyTitle
                + ", yyType="
                + yyType
                + ", status="
                + status
                + ", createTime="
                + createTime
                + ", updateTime="
                + updateTime
                + ", remark="
                + remark
                + ", sort="
                + sort
                + ", lotteryStatus="
                + lotteryStatus
                + "}";
    }
}
