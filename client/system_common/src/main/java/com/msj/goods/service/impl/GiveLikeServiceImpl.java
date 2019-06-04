package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msj.goods.common.constants.Constants;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.entity.*;
import com.msj.goods.mapper.GiveLikeMapper;
import com.msj.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 员工爱心点赞表 服务实现类
 *
 * @author sun li
 * @since 2018-12-19
 */
@Service
@Transactional
public class GiveLikeServiceImpl extends ServiceImpl<GiveLikeMapper, GiveLike>
        implements GiveLikeService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserPostService sysUserPostService;
    @Autowired
    private SysPostService sysPostService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private IntegralApprovalService integralApprovalService;

    /**
     * * 按照部门查询用户
     *
     * @param userName 用户名
     * @return
     */
    @Override
    public List<SysUser> findUserAll(SysUser user, String userName) {
        if (user == null) {
            return null;
        }
        Integer deptId = user.getDeptId();
        Integer userId = user.getUserId();
        SysDept dept = sysDeptService.selectById(deptId);
        List<SysDept> list = new ArrayList<SysDept>();
        list.add(dept);
        List<SysDept> depts = sysDeptService.findDeptAndSubUsers(dept, list);
        List<SysUser> users = new ArrayList<SysUser>();
        List<Integer> sb = new ArrayList<Integer>();
        sb.add(3);
        sb.add(4);
        sb.add(5);
        sb.add(6);
        sb.add(7);
        EntityWrapper ew = new EntityWrapper();
        ew.eq("user_id", userId);
        SysUserRole userRole = sysUserRoleService.selectOne(ew);
        Integer rId = null;
        if (userRole != null) rId = userRole.getRoleId();
        for (SysDept sd : depts) {
            List<SysUser> userList = sysUserService.findUserByDepId(sd.getDeptId(), userId, null);
            if (userList.isEmpty()) continue;
            boolean falg = true;
            for (SysUser sysUser : userList) {
                Integer roleId = sysUser.getIntegralStatus(); // integralStatus
                if (sb.contains(rId) && sb.contains(roleId)) {
                    falg = false;
                    users.add(sysUser);
                }
            }
            if (falg) {
                users.addAll(userList);
            }
        }

        return users;
    }

    /**
     * 爱心点赞信息保存到积分审批表
     *
     * @param userId
     */
    @Override
    public void saveIntegralApp(String userId) {
        SysUser user = sysUserService.selectById(userId);
        if (user == null) {
            return;
        }
        SysDept sysDept = sysDeptService.selectById(user.getDeptId());
        if (sysDept == null) {
            return;
        }
        SysUserPost sysUserPost = sysUserPostService.findByUserId(user.getUserId());
        if (sysUserPost == null) {
            return;
        }
        SysPost post = sysPostService.selectById(sysUserPost.getPostId());
        if (post == null) {
            return;
        }
        IntegralApproval al = new IntegralApproval();
        Integer id = user.getUserId();
        String userName = user.getUserName();
        al.setApprovalNum("JFB" + DateUtils.dateTime());
        al.setApprovalTitle("爱心点赞");
        al.setUserId(id);
        al.setUserName(userName);
        al.setUserPhone(Long.valueOf(user.getPhonenumber()));
        al.setUserDeptId(user.getDeptId());
        al.setUserDept(sysDept.getDeptName());
        al.setUserPostId(sysUserPost.getPostId());
        al.setUserPost(post.getPostName());
        al.setTiJiaoId(id);
        al.setTiJiaoName(userName);
        al.setIntegralTypeId(6);
        al.setSqTime(new Date());
        al.setSpTime(new Date());
        al.setStatus(1);
        al.setSqIntegral(Constants.LOVE_NUM);
        al.setApprovalTime(new Date());
        integralApprovalService.insert(al);
    }

    /**
     * 当前用户获取本周已点赞的用户ID
     *
     * @param userId 当前用户ID
     * @return 返回已点赞用户ID
     */
    @Override
    public Integer getTswkPraiseUsers(Integer userId) {
        Map<String, String> map = DateUtils.getWeekDate();
        String mondayDate = map.get("mondayDate");
        String sundayDate = map.get("sundayDate");
        EntityWrapper<GiveLike> ew = new EntityWrapper<GiveLike>();
        ew.eq("user_id", userId);
        ew.between("careate_time", mondayDate, sundayDate);
        GiveLike gl = this.selectOne(ew);
        if (gl == null) return null;
        return gl.getBenefitId();
    }

    /**
     * 被点赞用户，查询点赞用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public String lovePraiseLogList(Integer userId) {
        StringBuffer sb = new StringBuffer();
        List<SysUser> listUser = this.sysUserService.findByBenefitId(userId);
        if (listUser.isEmpty()) return null;
        for (SysUser su : listUser) {
            sb.append(su.getUserName());
            sb.append(",");
        }
        int length = sb.length() - 1;
        String userNames = sb.substring(0, length);
        return userNames;
    }
}
