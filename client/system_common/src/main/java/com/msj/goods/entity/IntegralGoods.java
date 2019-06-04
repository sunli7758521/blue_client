package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品管理表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralGoods extends Model<IntegralGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "good_id", type = IdType.AUTO)
    private Integer goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 总数量
     */
    private Long goodCount;

    /**
     * 商品库存
     */
    private Integer goodKc;

    /**
     * 已兑换数量
     */
    private Integer ydhNum;

    /**
     * 兑换积分
     */
    private Integer dhIntegral;

    /**
     * 兑换转态(0,兑换中  1停止兑换)
     */
    private Integer status;

    /**
     * 商品轮播图
     */
    private String goodLbImg;

    /**
     * 商品详情
     */
    private String goodDetails;

    /**
     * 上传时间
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
     * 商品封面
     */
    private String goodImg;

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
   /* public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }*/

    public Long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Long goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getGoodKc() {
        return goodKc;
    }

    public void setGoodKc(Integer goodKc) {
        this.goodKc = goodKc;
    }
    public Integer getYdhNum() {
        return ydhNum;
    }

    public void setYdhNum(Integer ydhNum) {
        this.ydhNum = ydhNum;
    }
    public Integer getDhIntegral() {
        return dhIntegral;
    }

    public void setDhIntegral(Integer dhIntegral) {
        this.dhIntegral = dhIntegral;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getGoodLbImg() {
        return goodLbImg;
    }

    public void setGoodLbImg(String goodLbImg) {
        this.goodLbImg = goodLbImg;
    }
    public String getGoodDetails() {
        return goodDetails;
    }

    public void setGoodDetails(String goodDetails) {
        this.goodDetails = goodDetails;
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
        return this.goodId;
    }

    @Override
    public String toString() {
        return "IntegralGoods{" +
        "goodId=" + goodId +
        ", goodName=" + goodName +
        ", goodCount=" + goodCount +
        ", goodKc=" + goodKc +
        ", ydhNum=" + ydhNum +
        ", dhIntegral=" + dhIntegral +
        ", status=" + status +
        ", goodLbImg=" + goodLbImg +
        ", goodDetails=" + goodDetails +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        ", goodImg=" + goodImg +
        "}";
    }
}
