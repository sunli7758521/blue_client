package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 抄送表
 * </p>
 *
 * @author sun li
 * @since 2018-12-20
 */
public class ChaoSong extends Model<ChaoSong> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "c_id", type = IdType.AUTO)
    private Integer cId;

    private Integer userId;

    private Integer chaoSongId;

    /**
     * 申请项id
     */
    private Integer approvalId;

    /**
     * 抄送时间
     */
    private Date chaoTime;

    /**
     * 备注
     */
    private String remak;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getChaoSongId() {
        return chaoSongId;
    }

    public void setChaoSongId(Integer chaoSongId) {
        this.chaoSongId = chaoSongId;
    }
    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }
    public Date getChaoTime() {
        return chaoTime;
    }

    public void setChaoTime(Date chaoTime) {
        this.chaoTime = chaoTime;
    }
    public String getRemak() {
        return remak;
    }

    public void setRemak(String remak) {
        this.remak = remak;
    }

    @Override
    protected Serializable pkVal() {
        return this.cId;
    }

    @Override
    public String toString() {
        return "ChaoSong{" +
        "cId=" + cId +
        ", userId=" + userId +
        ", chaoSongId=" + chaoSongId +
        ", approvalId=" + approvalId +
        ", chaoTime=" + chaoTime +
        ", remak=" + remak +
        "}";
    }
}
