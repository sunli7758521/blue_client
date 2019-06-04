package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）    0 在职 1.离职
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）  参与积分排名 0,参与 2 不参与
     */
    private String delFlag;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 积分
     */
    private String integralComplainant;

    /**
     * 基础积分
     */
    private Long jiChuIntegral;

    /**
     * 可用表样积分
     */
    private Long biaoIntegral;

    /**
     * 本月可用爱心积分
     */
    private Long loveIntegral;

    /**
     * 是否参加积分活动（1参加  2不参加）
     */
    private Integer integralStatus;

    /** 是不是审批人(1.是审批人 0.不是审批人) */
    private  Integer isApprover;

    /** 部门名称*/
    private String deptName;

    /** 部门名称*/
    private String applyIds;

    private Integer manAward;

    public String getApplyIds() {
        return applyIds;
    }

    public void setApplyIds(String applyIds) {
        this.applyIds = applyIds;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public Integer getIsApprover() {
        return isApprover;
    }

    public void setIsApprover(Integer isApprover) {
        this.isApprover = isApprover;
    }

    /** 判断是不是超级管理员*/
    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }
    public static boolean isAdmin(Integer userId)
    {
        return userId != null && 1L == userId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public String getIntegralComplainant() {
        return integralComplainant;
    }

    public void setIntegralComplainant(String integralComplainant) {
        this.integralComplainant = integralComplainant;
    }

    public Long getJiChuIntegral() {
        return jiChuIntegral;
    }

    public void setJiChuIntegral(Long jiChuIntegral) {
        this.jiChuIntegral = jiChuIntegral;
    }
    public Long getBiaoIntegral() {
        return biaoIntegral;
    }

    public void setBiaoIntegral(Long biaoIntegral) {
        this.biaoIntegral = biaoIntegral;
    }
    public Long getLoveIntegral() {
        return loveIntegral;
    }

    public void setLoveIntegral(Long loveIntegral) {
        this.loveIntegral = loveIntegral;
    }
    public Integer getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(Integer integralStatus) {
        this.integralStatus = integralStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    public Integer getManAward() {
        return manAward;
    }

    public void setManAward(Integer manAward) {
        this.manAward = manAward;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginDate=" + loginDate +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", integralComplainant=" + integralComplainant +
                ", jiChuIntegral=" + jiChuIntegral +
                ", biaoIntegral=" + biaoIntegral +
                ", loveIntegral=" + loveIntegral +
                ", integralStatus=" + integralStatus +
                ", isApprover=" + isApprover +
                ", deptName='" + deptName + '\'' +
                ", applyIds='" + applyIds + '\'' +
                ", manAward=" + manAward +
                '}';
    }
}
