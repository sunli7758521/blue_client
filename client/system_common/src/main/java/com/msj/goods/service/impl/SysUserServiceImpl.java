package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.entity.*;
import com.msj.goods.mapper.SysUserMapper;
import com.msj.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表 服务实现类
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserPostService sysUserPostService;

    @Autowired
    private SysUserDeptPostService sysUserDeptPostService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 通过手机号查询当前用户 return user
     */
    @Override
    public SysUser selectPhoneByUser(String phone) {
        return sysUserMapper.selectPhoneByUser(phone);
    }


    /***
     *
     * 通过 主键id 查找到当前的登录人
     *
     * */
    @Override
    public SysUser selectuserid(Integer userId) {
        return sysUserMapper.selectuserid(userId);
    }


    /***
     *
     *
     *
     * */
    @Override
    public int insertUser(int userId,int quan) {
        return sysUserMapper.insertUser(userId,quan);
    }


    /**
     * 查询所有的员工 return user
     */
    @Override
    public PageInfo<SysUser> selectAllUser(String pageSize, String pageNum, String search) {
        // 分页
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.eq("del_flag", 0);
        ew.eq("status", 0);

        if (StringUtils.isNotNull(search)) {
            ew.like("user_name", search);
        }
    /* if(StringUtils.isNotNull(deptId)){
        ew.eq("dept_id",deptId);
    }*/
        List<SysUser> userList = this.baseMapper.selectList(ew);
        for (SysUser user : userList) {
            SysDept dept = sysDeptService.selectById(user.getDeptId());
            if (dept != null) {
                user.setDeptName(dept.getDeptName());
            }
      /*if(StringUtils.isNotNull(postId)){
          SysUserPost userPost =  sysUserPostService.selectById(user.getUserId());
          if(userPost !=null){
              ew.eq("post_id",postId);
          }

      }*/
        }

        return new PageInfo<>(userList);
    }

    /**
     * 不通层级查询不同级别的审批
     */
    @Override
    public Map selectApproverPel(SysUser user) {

        SysUser sysUser = null;
        List<Map> list = null;
        Map listMap = new HashMap();
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        /** 普通员工 */
        EntityWrapper<SysUserRole> ew1 = new EntityWrapper<SysUserRole>();
        ew1.eq("user_id", user.getUserId());
        SysUserRole userRole = sysUserRoleService.selectOne(ew1);
        SysRole role = sysRoleService.selectById(userRole.getRoleId());
        String rolekey = role.getRoleKey();
        EntityWrapper<SysUserDeptPost> ew2 = new EntityWrapper<>();
        SysUserDeptPost userDeptPost = null;
        SysUser u = null;
        // 普通员工登录
        if (rolekey.equalsIgnoreCase(UserConstants.ROLE_COMMON)
                || rolekey.equalsIgnoreCase(UserConstants.ROLE_COMPETENT)
                || rolekey.equalsIgnoreCase(UserConstants.ROLE_ADMIN)) {


            list = sysUserService.selectCommonDept(user.getDeptId(), null);
            for (Map map : list) {
                if (map.get("roleKey").equals(UserConstants.ROLE_MANAGER)) {
                    listMap.put("userId", map.get("userId"));
                    listMap.put("userName", map.get("userName"));
                    listMap.put("avatar", map.get("avatar"));
                    listMap.put("deptName", map.get("deptName"));
                    break;
                } else if (map.get("roleKey").equals(UserConstants.ROLE_FUNCTION)) {
                    listMap.put("userId", map.get("userId"));
                    listMap.put("userName", map.get("userName"));
                    listMap.put("avatar", map.get("avatar"));
                    listMap.put("deptName", map.get("deptName"));
                    break;
                } else if (map.get("roleKey").equals(UserConstants.ROLE_SUPER_ADMIN)) {
                    listMap.put("userId", map.get("userId"));
                    listMap.put("userName", map.get("userName"));
                    listMap.put("avatar", map.get("avatar"));
                    listMap.put("deptName", map.get("deptName"));
                    break;
                } else if (map.get("roleKey").equals(UserConstants.ROLE_GENERAL_MANAGE)) {
                    listMap.put("userId", map.get("userId"));
                    listMap.put("userName", map.get("userName"));
                    listMap.put("avatar", map.get("avatar"));
                    listMap.put("deptName", map.get("deptName"));
                    break;
                } else if (map.get("roleKey").equals(UserConstants.ROLE_DIRECTOR)) {
                    listMap.put("userId", map.get("userId"));
                    listMap.put("userName", map.get("userName"));
                    listMap.put("avatar", map.get("avatar"));
                    listMap.put("deptName", map.get("deptName"));
                    break;
                }
                // 如果以上都没用 就查询谁管理这个部门
                SysUserDeptPost  appUserId =  sysUserDeptPostService.selectDeptIdUserId(user.getDeptId());
                if(appUserId !=null){
                    listMap.put("userId", appUserId.getUserId());
                }
                SysUser sysUser1 =   sysUserMapper.selectuserid(appUserId.getUserId());
                if(sysUser1 !=null){
                    listMap.put("userName", sysUser1.getUserName());
                    listMap.put("avatar", sysUser1.getAvatar());

                }
               Map dept = sysDeptService.selectByDeptId(sysUser1.getDeptId());
                if(dept !=null){
                    listMap.put("deptName", dept.get("name"));
                }
            }
        }
        // 总经理 ，事业部总监  经理级登录 ，职能总监
        if (rolekey.equalsIgnoreCase(UserConstants.ROLE_MANAGER)
                || rolekey.equalsIgnoreCase(UserConstants.ROLE_FUNCTION)
                || rolekey.equalsIgnoreCase(UserConstants.ROLE_DIRECTOR)
                || rolekey.equalsIgnoreCase(UserConstants.ROLE_GENERAL_MANAGE)) {
            ew2.eq("dept_id", user.getDeptId());
            userDeptPost = sysUserDeptPostService.selectOne(ew2);
            if (userDeptPost != null) {
                sysUser = sysUserService.selectById(userDeptPost.getUserId());
                u = sysUserService.selectById(sysUser.getUserId());
                listMap.put("userId", sysUser.getUserId());
                listMap.put("userName", u.getUserName());
                listMap.put("avatar", u.getAvatar());
                listMap.put("deptName", u.getDeptName());
            }
        }

        /** 如果总裁级登录 */
        if (rolekey.equalsIgnoreCase(UserConstants.ROLE_SUPER_ADMIN)) {
            sysUser = sysUserService.selectById(user.getUserId());
            listMap.put("userId", sysUser.getUserId());
            listMap.put("userName", sysUser.getUserName());
            listMap.put("avatar", sysUser.getAvatar());
            listMap.put("deptName", sysUser.getDeptName());
        }

        return listMap;
    }

    /**
     * 通过手机号查询当前用户 return user
     */
    @Override
    public SysUser selectPhoneUser(String mobile) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.eq("phonenumber", mobile);
        SysUser userList = sysUserService.selectOne(ew);
        return userList;
    }

    /**
     * 查询本部门所有的用户
     */
    @Override
    public PageInfo<SysUser> selectAllDeptUser(
            SysUser user, String pageSize, String pageNum, String search) {
        // 分页
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.eq("del_flag", 0);
        ew.eq("status", 0);
        ew.eq("dept_id", user.getDeptId());
        if (StringUtils.isNotNull(search)) {
            ew.like("user_name", search);
        }
        List<SysUser> userList = this.baseMapper.selectList(ew);

        return new PageInfo<>(userList);
    }

    /**
     * 修改爱心点赞值
     *
     * @param loveIntegral
     */
    @Override
    public void updateUser(Integer loveIntegral) {
        if (loveIntegral != null) this.sysUserMapper.updateUser(loveIntegral);
    }

    /**
     * 根据被点赞用户id，查询点赞用户信息
     *
     * @param benefit_id 点赞用户id
     */
    @Override
    public List<SysUser> findByBenefitId(Integer benefit_id) {
        if (benefit_id == null) return null;
        Map<String, String> map = DateUtils.getWeekDate();
        String mondayDate = map.get("mondayDate");
        String sundayDate = map.get("sundayDate");
        return this.sysUserMapper.findByBenefitId(benefit_id, mondayDate, sundayDate);
    }

    /**
     * 查询所用属于本部门的员工
     */
    @Override
    public List<Map> selectDeptAllUser(Integer userId, Integer deptId, String search) {
        return this.sysUserMapper.selectDeptAllUser(userId, deptId, search);
    }

    @Override
    public List<SysUser> findUserByDepId(Integer depId, Integer userId, Integer roleId) {
        return this.sysUserMapper.findUserByDepId(depId, userId, roleId);
    }

    /**
     * 普通用户查询所有的部门人员
     */
    @Override
    public List<Map> selectCommonDept(Integer deptId, String search) {
        return this.sysUserMapper.selectCommonDept(deptId, search);
    }

    @Override
    public List<ManageUser> selectCommonDept1(Integer deptId, String search) {
        return this.sysUserMapper.selectCommonDept1(deptId, search);
    }

    /**
     * 经理级别的奖励人员查询
     */
    @Override
    public List<ManageUser> selectManagerDept(Integer deptId, Integer userId, String search) {
        return this.sysUserMapper.selectManagerDept(deptId, userId, search);
    }

    /**
     * 总监级 查询所管理部门 经理级别的人员
     */
    @Override
    public List<ManageUser> selectDirectorDept(
            Integer deptId, Integer userId, String search, Integer status) {
        return this.sysUserMapper.selectDirectorDept(deptId, userId, search, status);
    }

    @Override
    public List<ManageUser> selectGeneralManagerDept(Integer deptId, Integer userId, String search) {
        return this.sysUserMapper.selectGeneralManagerDept(deptId, userId, search);
    }

    /** 医美 市场部查询经理角色 */
    @Override
    public ManageUser selectRole(Long deptId, Integer status) {
        return this.sysUserMapper.selectRole(deptId,status);
    }
}
