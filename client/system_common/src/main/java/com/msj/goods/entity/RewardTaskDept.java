package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 任务标题部门
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
public class RewardTaskDept extends Model<RewardTaskDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "rtd_id", type = IdType.AUTO)
    private Integer rtdId;

    /**
     * 任务列表主键
     */
    private Integer rtId;

    /**
     * 部门主键
     */
    private Integer dId;

    /**
     * 创建时间
     */
    private Date createDate;

    public Integer getRtdId() {
        return rtdId;
    }

    public void setRtdId(Integer rtdId) {
        this.rtdId = rtdId;
    }
    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }
    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.rtdId;
    }

    @Override
    public String toString() {
        return "RewardTaskDept{" +
        "rtdId=" + rtdId +
        ", rtId=" + rtId +
        ", dId=" + dId +
        ", createDate=" + createDate +
        "}";
    }
}
