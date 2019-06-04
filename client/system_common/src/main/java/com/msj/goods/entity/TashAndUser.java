package com.msj.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author sun li
 * @Date 2019/2/15 10:52
 * @Description
 */
public class TashAndUser {

    private Integer rtId;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 任务类型
     */
    private Integer taskTypeId;

    /**
     * 积分类型
     */
    private Integer integralTypeId;

    /**
     * 可申请方式 0 抢单任务.1 挑战仅限一次 2.每日一一次
     */
    private String appWay;

    /**
     * 任务积分数量
     */
    private Integer taskIntegral;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date endTime;

    /**
     * 抢单人数
     */
    private Integer peopleNum;

    /**
     * 完成执行顺序
     */
    private String sort;

    /**
     * 使用状态 0 使用中 1 停用
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    /**
     * 部门ids
     */
    private String deptId;

    /**
     * 备注
     */
    private String remark;

    /** 发布任务的 用户id */
    private Integer releaseUserId;

    /** 角色id */
    private String roleIds;

    /** 用户所对用的主键 */

    private Integer rtuId;

    /**
     * 任务标题主键
     */
    private Integer rewardTaskId;

    /**
     *  当前用户对这条单据的 状态
     */
    private String userState;

    /**
     *  当前用户 id
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




    /**  封装 这条任务 所有 用户 */
    private List<TaskUserList> taskAndUserList;

    public List<TaskUserList> getTaskAndUserList() {
        return taskAndUserList;
    }

    public void setTaskAndUserList(List<TaskUserList> taskAndUserList) {
        this.taskAndUserList = taskAndUserList;
    }



    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Integer taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public Integer getIntegralTypeId() {
        return integralTypeId;
    }

    public void setIntegralTypeId(Integer integralTypeId) {
        this.integralTypeId = integralTypeId;
    }

    public String getAppWay() {
        return appWay;
    }

    public void setAppWay(String appWay) {
        this.appWay = appWay;
    }

    public Integer getTaskIntegral() {
        return taskIntegral;
    }

    public void setTaskIntegral(Integer taskIntegral) {
        this.taskIntegral = taskIntegral;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(Integer releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getRtuId() {
        return rtuId;
    }

    public void setRtuId(Integer rtuId) {
        this.rtuId = rtuId;
    }

    public Integer getRewardTaskId() {
        return rewardTaskId;
    }

    public void setRewardTaskId(Integer rewardTaskId) {
        this.rewardTaskId = rewardTaskId;
    }

    @Override
    public String toString() {
        return "TashAndUser{" + "rtId=" + rtId + ", title='" + title + '\'' + ", content='" + content + '\'' + ", taskTypeId=" + taskTypeId + ", integralTypeId=" + integralTypeId + ", appWay='" + appWay + '\'' + ", taskIntegral=" + taskIntegral + ", startTime=" + startTime + ", endTime=" + endTime + ", peopleNum=" + peopleNum + ", sort='" + sort + '\'' + ", status='" + status + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + ", deptId='" + deptId + '\'' + ", remark='" + remark + '\'' + ", releaseUserId=" + releaseUserId + ", roleIds='" + roleIds + '\'' + ", rtuId=" + rtuId + ", rewardTaskId=" + rewardTaskId + ", userState='" + userState + '\'' + '}';
    }
}
