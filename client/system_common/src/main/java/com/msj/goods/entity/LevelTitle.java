package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 水平考核题目表
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-21
 */
public class LevelTitle extends Model<LevelTitle> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;

    /**
     * 题目名称
     */
    private String tName;

    private Long tIntegral;

    /**
     * 答案a
     */
    private String tA;

    /**
     * 答案b
     */
    private String tB;

    /**
     * 答案c
     */
    private String tC;

    /**
     * 答案d
     */
    private String tD;

    /**
     * 正确答案
     */
    private String tEnd;

    /**
     * 对应题目id
     */
    private String levelId;

    private Date catabTime;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
    public Long gettIntegral() {
        return tIntegral;
    }

    public void settIntegral(Long tIntegral) {
        this.tIntegral = tIntegral;
    }
    public String gettA() {
        return tA;
    }

    public void settA(String tA) {
        this.tA = tA;
    }
    public String gettB() {
        return tB;
    }

    public void settB(String tB) {
        this.tB = tB;
    }
    public String gettC() {
        return tC;
    }

    public void settC(String tC) {
        this.tC = tC;
    }
    public String gettD() {
        return tD;
    }

    public void settD(String tD) {
        this.tD = tD;
    }
    public String gettEnd() {
        return tEnd;
    }

    public void settEnd(String tEnd) {
        this.tEnd = tEnd;
    }
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
    public Date getCatabTime() {
        return catabTime;
    }

    public void setCatabTime(Date catabTime) {
        this.catabTime = catabTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.tId;
    }

    @Override
    public String toString() {
        return "LevelTitle{" +
        "tId=" + tId +
        ", tName=" + tName +
        ", tIntegral=" + tIntegral +
        ", tA=" + tA +
        ", tB=" + tB +
        ", tC=" + tC +
        ", tD=" + tD +
        ", tEnd=" + tEnd +
        ", levelId=" + levelId +
        ", catabTime=" + catabTime +
        "}";
    }
}
