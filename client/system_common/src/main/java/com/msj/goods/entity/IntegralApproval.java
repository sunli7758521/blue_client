package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 审批管理
 * </p>
 *
 * @author sun li
 * @since 2018-11-19
 */
public class IntegralApproval extends Model<IntegralApproval> {

    private static final long serialVersionUID = 1L;

    /**
     * 审批主键
     */
    @TableId(value = "approval_id", type = IdType.AUTO)
    private Integer approvalId;

    /**
     * 审批编号
     */
    private String approvalNum;

    /**
     * 审批标题
     */
    private String approvalTitle;

    /**
     * 审批内容
     */
    private String approvalContent;

    /**
     * 关联用户id
     */
    private Integer userId;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工头像
     */
    private String userImg;

    /**
     * 申请人电话也是唯一标识
     */
    private Long userPhone;

    /**
     * 所属部门
     */
    private String userDept;

    /**
     * 部门id
     */
    private Integer userDeptId;

    /**
     * 职位id
     */
    private Integer userPostId;

    /**
     * 员工职位
     */
    private String userPost;

    /**
     * 提交人的id
     */
    private Integer tiJiaoId;

    /**
     * 审请人姓名
     */
    private String tiJiaoName;

    /**
     * 申请人头像
     */
    private String tiJiaoNameImg;

    /**
     * 积分类型
     */
    private Integer integralTypeId;

    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date sqTime;

    /**
     * 审批时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date spTime;

    /**
     * 审批状态(0,审批中 1审批通过，2审批不通过，3撤销审批 )
     */
    private Integer status;

    /**
     * 申请积分
     */
    private Integer sqIntegral;

    /**
     * 审批备注
     */
    private String spRemark;

    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date approvalTime;

    /**
     * 上传图片1
     */
    private String approvalImg1;

    /**
     * 申请项的id
     */
    private String approvalImg2;

    /**
     * 上传图片3
     */
    private String approvalImg3;

    /**
     * 上传图片4
     */
    private String approvalImg4;

    /**
     * 上传图片5
     */
    private String approvalImg5;

    /**
     * 0.可以申诉，1.不可以申诉
     */
    private String approvalImg6;

    /**
     * 二次申诉理由
     */
    private String approvalImg7;

    /**
     * 日常，抢单，挑战 任务 id
     */
    private String approvalImg8;

    /**
     * 审批不通过原因
     */
    private String approvalImg9;

    /**
     * 备注
     */
    private String remark;

    /**
     * 扣除积分
     */
    private Integer kIntegral;

    /**
     * 抄送人id
     */
    private Integer chaoSongId;

    /**
     * 抄送人姓名
     */
    private String chaoSongName;

    /**
     * 抄送人头像
     */
    private String chaoSongImg;

    /**
     * 抄送人部门
     */
    private String chaoSongDeptName;

    /**
     * 抄送人id
     */
    private Integer chaoSongDeptId;

    /**
     * 积分类型  id
     */
    private Integer typeId;

    /**
     * 总积分
     */
    private Long countIntegral;

    /**
     * 添加积分
     */
    private Long addIntegral;

    /**
     * 扣除总积分和
     */
    private Long deduce;

    /**
     * 总分
     */
    private Long amount;

    /**
     * 提交人的ids
     */
    private String tiJiaoRenIds;

    /**
     * 抄送人的ids
     */
    private String chaoSongIds;

    /**
     * 审批人的ids
     */
    private String shenPiRenIds;

    /**
     * 对应餐单项的id
     */
    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(String approvalNum) {
        this.approvalNum = approvalNum;
    }

    public String getApprovalTitle() {
        return approvalTitle;
    }

    public void setApprovalTitle(String approvalTitle) {
        this.approvalTitle = approvalTitle;
    }

    public String getApprovalContent() {
        return approvalContent;
    }

    public void setApprovalContent(String approvalContent) {
        this.approvalContent = approvalContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public Integer getUserDeptId() {
        return userDeptId;
    }

    public void setUserDeptId(Integer userDeptId) {
        this.userDeptId = userDeptId;
    }

    public Integer getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(Integer userPostId) {
        this.userPostId = userPostId;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public Integer getTiJiaoId() {
        return tiJiaoId;
    }

    public void setTiJiaoId(Integer tiJiaoId) {
        this.tiJiaoId = tiJiaoId;
    }

    public String getTiJiaoName() {
        return tiJiaoName;
    }

    public void setTiJiaoName(String tiJiaoName) {
        this.tiJiaoName = tiJiaoName;
    }

    public String getTiJiaoNameImg() {
        return tiJiaoNameImg;
    }

    public void setTiJiaoNameImg(String tiJiaoNameImg) {
        this.tiJiaoNameImg = tiJiaoNameImg;
    }

    public Integer getIntegralTypeId() {
        return integralTypeId;
    }

    public void setIntegralTypeId(Integer integralTypeId) {
        this.integralTypeId = integralTypeId;
    }

    public Date getSqTime() {
        return sqTime;
    }

    public void setSqTime(Date sqTime) {
        this.sqTime = sqTime;
    }

    public Date getSpTime() {
        return spTime;
    }

    public void setSpTime(Date spTime) {
        this.spTime = spTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSqIntegral() {
        return sqIntegral;
    }

    public void setSqIntegral(Integer sqIntegral) {
        this.sqIntegral = sqIntegral;
    }

    public String getSpRemark() {
        return spRemark;
    }

    public void setSpRemark(String spRemark) {
        this.spRemark = spRemark;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalImg1() {
        return approvalImg1;
    }

    public void setApprovalImg1(String approvalImg1) {
        this.approvalImg1 = approvalImg1;
    }

    public String getApprovalImg2() {
        return approvalImg2;
    }

    public void setApprovalImg2(String approvalImg2) {
        this.approvalImg2 = approvalImg2;
    }

    public String getApprovalImg3() {
        return approvalImg3;
    }

    public void setApprovalImg3(String approvalImg3) {
        this.approvalImg3 = approvalImg3;
    }

    public String getApprovalImg4() {
        return approvalImg4;
    }

    public void setApprovalImg4(String approvalImg4) {
        this.approvalImg4 = approvalImg4;
    }

    public String getApprovalImg5() {
        return approvalImg5;
    }

    public void setApprovalImg5(String approvalImg5) {
        this.approvalImg5 = approvalImg5;
    }

    public String getApprovalImg6() {
        return approvalImg6;
    }

    public void setApprovalImg6(String approvalImg6) {
        this.approvalImg6 = approvalImg6;
    }

    public String getApprovalImg7() {
        return approvalImg7;
    }

    public void setApprovalImg7(String approvalImg7) {
        this.approvalImg7 = approvalImg7;
    }

    public String getApprovalImg8() {
        return approvalImg8;
    }

    public void setApprovalImg8(String approvalImg8) {
        this.approvalImg8 = approvalImg8;
    }

    public String getApprovalImg9() {
        return approvalImg9;
    }

    public void setApprovalImg9(String approvalImg9) {
        this.approvalImg9 = approvalImg9;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getkIntegral() {
        return kIntegral;
    }

    public void setkIntegral(Integer kIntegral) {
        this.kIntegral = kIntegral;
    }

    public Integer getChaoSongId() {
        return chaoSongId;
    }

    public void setChaoSongId(Integer chaoSongId) {
        this.chaoSongId = chaoSongId;
    }

    public String getChaoSongName() {
        return chaoSongName;
    }

    public void setChaoSongName(String chaoSongName) {
        this.chaoSongName = chaoSongName;
    }

    public String getChaoSongImg() {
        return chaoSongImg;
    }

    public void setChaoSongImg(String chaoSongImg) {
        this.chaoSongImg = chaoSongImg;
    }

    public String getChaoSongDeptName() {
        return chaoSongDeptName;
    }

    public void setChaoSongDeptName(String chaoSongDeptName) {
        this.chaoSongDeptName = chaoSongDeptName;
    }

    public Integer getChaoSongDeptId() {
        return chaoSongDeptId;
    }

    public void setChaoSongDeptId(Integer chaoSongDeptId) {
        this.chaoSongDeptId = chaoSongDeptId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getCountIntegral() {
        return countIntegral;
    }

    public void setCountIntegral(Long countIntegral) {
        this.countIntegral = countIntegral;
    }

    public Long getAddIntegral() {
        return addIntegral;
    }

    public void setAddIntegral(Long addIntegral) {
        this.addIntegral = addIntegral;
    }

    public Long getDeduce() {
        return deduce;
    }

    public void setDeduce(Long deduce) {
        this.deduce = deduce;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTiJiaoRenIds() {
        return tiJiaoRenIds;
    }

    public void setTiJiaoRenIds(String tiJiaoRenIds) {
        this.tiJiaoRenIds = tiJiaoRenIds;
    }

    public String getChaoSongIds() {
        return chaoSongIds;
    }

    public void setChaoSongIds(String chaoSongIds) {
        this.chaoSongIds = chaoSongIds;
    }

    public String getShenPiRenIds() {
        return shenPiRenIds;
    }

    public void setShenPiRenIds(String shenPiRenIds) {
        this.shenPiRenIds = shenPiRenIds;
    }

    @Override
    protected Serializable pkVal() {
        return this.approvalId;
    }

    @Override
    public String toString() {
        return "IntegralApproval{" +
        "approvalId=" + approvalId +
        ", approvalNum=" + approvalNum +
        ", approvalTitle=" + approvalTitle +
        ", approvalContent=" + approvalContent +
        ", userId=" + userId +
        ", userName=" + userName +
        ", userImg=" + userImg +
        ", userPhone=" + userPhone +
        ", userDept=" + userDept +
        ", userDeptId=" + userDeptId +
        ", userPostId=" + userPostId +
        ", userPost=" + userPost +
        ", tiJiaoId=" + tiJiaoId +
        ", tiJiaoName=" + tiJiaoName +
        ", tiJiaoNameImg=" + tiJiaoNameImg +
        ", integralTypeId=" + integralTypeId +
        ", sqTime=" + sqTime +
        ", spTime=" + spTime +
        ", status=" + status +
        ", sqIntegral=" + sqIntegral +
        ", spRemark=" + spRemark +
        ", approvalTime=" + approvalTime +
        ", approvalImg1=" + approvalImg1 +
        ", approvalImg2=" + approvalImg2 +
        ", approvalImg3=" + approvalImg3 +
        ", approvalImg4=" + approvalImg4 +
        ", approvalImg5=" + approvalImg5 +
        ", approvalImg6=" + approvalImg6 +
        ", approvalImg7=" + approvalImg7 +
        ", approvalImg8=" + approvalImg8 +
        ", approvalImg9=" + approvalImg9 +
        ", remark=" + remark +
        ", kIntegral=" + kIntegral +
        ", chaoSongId=" + chaoSongId +
        ", chaoSongName=" + chaoSongName +
        ", chaoSongImg=" + chaoSongImg +
        ", chaoSongDeptName=" + chaoSongDeptName +
        ", chaoSongDeptId=" + chaoSongDeptId +
        ", typeId=" + typeId +
        ", countIntegral=" + countIntegral +
        ", addIntegral=" + addIntegral +
        ", deduce=" + deduce +
        ", amount=" + amount +
        ", tiJiaoRenIds=" + tiJiaoRenIds +
        ", chaoSongIds=" + chaoSongIds +
        ", shenPiRenIds=" + shenPiRenIds +
        ", menuId=" + menuId +
        "}";
    }
}
