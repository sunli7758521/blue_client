package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.constants.UsingStatusConstans;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.entity.*;
import com.msj.goods.mapper.IntegralApprovalMapper;
import com.msj.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 审批管理 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class IntegralApprovalServiceImpl extends ServiceImpl<IntegralApprovalMapper, IntegralApproval> implements IntegralApprovalService {

    @Autowired
    private IntegralApprovalMapper integralApprovalMapper;

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysUserPostService  sysUserPostService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private ChaoSongService chaoSongService;

    @Autowired
    private SysUserDeptPostService sysUserDeptPostService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RewardTaskService rewardTaskService;

    @Autowired
    private RewardTaskUserService rewardTaskUserService;

    @Autowired
    private RewardTaskDeptService rewardTaskDeptService;

    @Autowired
    private IntegralAppUserService integralAppUserService;

    @Autowired
    private XwIntegralService  xwIntegralService;


    /**
     * 管理员查看 管理 奖扣日志
     * @return IntegralApproval
     *
     * @param */
    @Override
    public PageInfo<IntegralApproval> selectgly(Integer tiJiaoId, String pageSize, String pageNum) {
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
           List<IntegralApproval> selectgly =integralApprovalMapper.selectgly(tiJiaoId);
        return new PageInfo<>(selectgly);
    }


    /**
     * 添加一条水平考核信息
     * @return IntegralApproval
     *
     * @param */
    @Override
    public int addxinxi(Integer num,String Phone ,Integer userId ,String shenPiRenIds,String tiJiaoRenIds,Integer tiJiaoId,Integer status,Integer integralTypeId, String userName,Integer userDeptId) {
           int rows=integralApprovalMapper.addxinxi( num,Phone ,userId ,shenPiRenIds,tiJiaoRenIds,tiJiaoId,status,integralTypeId,userName,userDeptId);
          return rows;
    }


    /**
     * 查询冠军
     * @return IntegralApproval
     *
     * @param */
    @Override
    public List<Map> selectGJ(Integer deptIds) {
        return integralApprovalMapper.selectGJ(deptIds);
    }

   /* 首页显示所有用户的信息*/
    /** 往缓存 缓存数据 */
    @Override
    public PageInfo<AppLog> selectOneIntegralApproval(SysUser user, String pageSize, String pageNum) {
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<AppLog> logs = null;
        if(user.getIsApprover() == UserConstants.SUPER_ADMIN){
            logs = this.baseMapper.selectListAll();
        }else  {
            logs = integralApprovalMapper.selectCommon(user.getDeptId());
        }
        return new PageInfo<>(logs);
    }

    @Override
    public boolean insertIntegralApprover(SysUser sysUser,String addIntegral, String spRemark, String typeId, String[] from, String[] to, String[] approvalImg1, String approvalContent, String approvalTitle, String approvalId, String[] apps) {
        //所有提交人的ids
        for (String id : from) {
            IntegralApproval integralApproval = new IntegralApproval();
            SysUser user =  sysUserService.selectById(id);
            SysDept sysDept = sysDeptService.selectById(user.getDeptId());
            if(user.getApplyIds() !=null && user.getApplyIds() !=""){
                user.setApplyIds(user.getApplyIds()+approvalId+",");
            } else{
                user.setApplyIds(approvalId+",");
            }
            boolean flag = sysUserService.updateById(user);

            // todo 添加 用户关联积分项id
//            XwIntegral xw = xwIntegralService.selectById(approvalId);
//
//            IntegralAppUser appUser = new IntegralAppUser();
//            appUser.setMenuId(Long.parseLong(approvalId));
//            appUser.setAppType(xw.getShenQingFangShi());
//            appUser.setUserId(Long.parseLong(id));
//            appUser.setCreateTime(DateUtils.getNowDate());
//            integralAppUserService.insert(appUser);

            integralApproval.setMenuId(approvalId+",");
            integralApproval.setApprovalNum("JFB"+ DateUtils.dateTime());
            integralApproval.setSqTime(DateUtils.getNowDate());
            integralApproval.setSqIntegral(Integer.parseInt(addIntegral) );
            integralApproval.setIntegralTypeId(Integer.parseInt(typeId));
            integralApproval.setApprovalContent(approvalContent);
            integralApproval.setApprovalTitle(approvalTitle);
            integralApproval.setApprovalImg1(StringUtils.join(approvalImg1, ","));
            integralApproval.setSpRemark(spRemark);
            /*integralApproval.setTypeId(Integer.parseInt(typeId));*/
            integralApproval.setUserImg(user.getAvatar());
            integralApproval.setUserId(user.getUserId());
            integralApproval.setUserName(user.getUserName());
            integralApproval.setStatus(0);
            /**  添加用户部门id*/
            integralApproval.setUserDeptId(user.getDeptId());
            integralApproval.setUserDept(sysDept.getDeptName());
            /**  通过用户id 查询职位添加职位id*/
            EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
            ew.eq("user_id",user.getUserId());
            SysUserPost userPost =  sysUserPostService.selectOne(ew);
            if(userPost != null){
                integralApproval.setUserPostId(userPost.getPostId());
             SysPost post=   sysPostService.selectById(userPost.getPostId());
                integralApproval.setUserPost(post.getPostName());
            }
            /** 提交人的信息*/
            integralApproval.setTiJiaoId(sysUser.getUserId());
            integralApproval.setTiJiaoName(sysUser.getUserName());
            integralApproval.setTiJiaoNameImg(sysUser.getAvatar());
            integralApproval.setUserPhone(Long.parseLong(user.getPhonenumber()));
           /**  申请人  ids  默认是自己 from 是所有的申请人 */
            integralApproval.setTiJiaoRenIds(StringUtils.join(from, ","));

            /**  审批人 ids 把数组转成字符串保存到数据库*/
            integralApproval.setShenPiRenIds(StringUtils.join(apps, ","));
            //申请项的id
            integralApproval.setApprovalImg2(approvalId);

          /** 正常状态 0*/
            integralApproval.setStatus(Integer.parseInt(UserConstants.NORMAL));

            int  i = this.baseMapper.insert(integralApproval);

            if(to !=null){
                for (String csId : to) {
                    ChaoSong cs = new ChaoSong();
                    cs.setChaoSongId(Integer.parseInt(csId));
                    cs.setUserId(user.getUserId());
                    cs.setChaoTime(DateUtils.getNowDate());
                    cs.setApprovalId(integralApproval.getApprovalId());
                    chaoSongService.insert(cs);
                }
            }

            if (i>0){
                return true;
            }
        }
        return false;
    }

    /**
     *   查询待审和已审批列表
     */
    @Override
    public PageInfo<App> selectApproversPel(SysUser user,Integer pageSize, Integer pageNum,Integer status,String search) {

        List<App> list = null;
        PageHelper.startPage(pageNum,pageSize);
        if(status==0){
            list  = this.baseMapper.selectAllList(user.getUserId(),search,status);
        }else if(status==1){
            list  = this.baseMapper.selectAllListNo(user.getUserId(),search,null);
        }
        return  new PageInfo<>(list);
    }

    /**
     *  approvalImg1 上传的图片没有用到
     *  管理员自由奖扣
     */
    @Override
    public boolean freeIntegral(SysUser sysUser,String addIntegral,String delIntegral, String spRemark, String typeId, String[] from, String[] approvalImg1, String approvalContent, String approvalTitle,String dateTime) {
        int i=0;
        for (String id : from) {
         int row =  insertApprovalFreeIntegral(addIntegral,delIntegral,spRemark,typeId,approvalContent,approvalTitle,approvalImg1,sysUser,id,dateTime);
         i =  insertLog(addIntegral,delIntegral,spRemark,typeId,approvalContent,approvalTitle,dateTime,id);
           i  = updateIIntegral(id,addIntegral,delIntegral,typeId);
        }

        if (i>1){
            return true;
        }
        return false;
    }
    /** 添加管理奖扣审批值 */
    private int insertApprovalFreeIntegral(String addIntegral, String delIntegral, String spRemark, String typeId, String approvalContent, String approvalTitle, String[] approvalImg1, SysUser sysUser, String id, String dateTime) {
        /** 更新用户管理奖扣积分值 */
        IntegralApproval approval = new IntegralApproval();
        approval.setApprovalNum("JFB"+DateUtils.dateTime());
        Integer manAward =null;
        if(sysUser.getManAward() ==null){
            manAward = 0;
        }else{
            manAward = sysUser.getManAward();
        }
        synchronized (sysUser){
            if(delIntegral !=null && delIntegral !=""){
                approval.setkIntegral(Integer.parseInt(delIntegral));
                sysUser.setManAward(manAward+Integer.parseInt(delIntegral));
                sysUserService.updateById(sysUser);
            }
            if(addIntegral !=null  && addIntegral !=""){
                approval.setSqIntegral(Integer.parseInt(addIntegral));
                sysUser.setManAward(manAward-Integer.parseInt(addIntegral));
                sysUserService.updateById(sysUser);
            }
        }

        /** 奖励那个人的所用信息 */
        SysUser user =  sysUserService.selectById(id);
        approval.setUserId(Integer.parseInt(id));
        approval.setUserDeptId(user.getDeptId());
        approval.setUserPhone(Long.parseLong(user.getPhonenumber()));
        approval.setUserDept(sysDeptService.selectById(user.getDeptId()).getDeptName());
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",user.getUserId());
        SysUserPost userPost =  sysUserPostService.selectOne(ew);
        if(userPost !=null){
            approval.setUserPostId(userPost.getPostId());
            approval.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
        }
        approval.setUserName(user.getUserName());
        approval.setUserImg(user.getAvatar());

        if (dateTime != null && dateTime !="") {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                approval.setSpTime(format.parse(dateTime));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        approval.setSqTime(DateUtils.getNowDate());
        approval.setSpRemark(spRemark);
        if(typeId !=null){
            approval.setIntegralTypeId(Integer.parseInt(typeId));
        }

        approval.setApprovalTitle(approvalTitle);
        approval.setApprovalContent(approvalContent);
        /** 提交那个人的信息也就相当于管理员的信息 */
        approval.setTiJiaoId(sysUser.getUserId());
        approval.setTiJiaoNameImg(sysUser.getAvatar());
        approval.setTiJiaoName(sysUser.getUserName());
        approval.setStatus(1);
        approval.setSpTime(DateUtils.getNowDate());
        approval.setShenPiRenIds(sysUser.getUserId()+"");
        if (approvalImg1 !=null ){
            approval.setApprovalImg1(StringUtils.join(approvalImg1, ","));
        }

        int row = this.baseMapper.insert(approval);
        return  row;
    }


    /**
     * 自由奖扣申请
     */
    @Override
    public boolean freeIntegralApprover( SysUser user,String addIntegral, String delIntegral, String spRemark, String typeId, String[] from, String[] to, String[] approvalImg1, String approvalContent, String approvalTitle, String approvalId, String[] apps) {

        for (String id : from) {
         int row =    insertIntegralApproval(user,addIntegral,delIntegral,spRemark,typeId,approvalContent,approvalTitle,approvalId,approvalImg1,apps,to,id,from);
         if(row >0){
             return true;
         }
        }
        return false;
    }

    /**
     * 申请  1通过、 2不通过、3撤销、
     */
    @Override
    public int approversYesNo(String approvalId, String status,String keyWord,String app) {
        IntegralApproval approval = this.baseMapper.selectById(approvalId);
        int i = 0;
         if (status.equalsIgnoreCase("1")){
            approval.setStatus(1);
            approval.setSpTime(DateUtils.getNowDate());
            if(keyWord !=null && keyWord !=""){
                approval.setApprovalImg9(keyWord);
            }
            // 拒接理由
             approval.setApprovalImg9(null);
             if(app !=null && app !=""){
                 approval.setApprovalImg7(app);
             }
             // 申诉理由
             approval.setApprovalImg7(null);
            /** 修改总积分*/
            EntityWrapper<Integral> ew = new EntityWrapper<Integral>();
               ew.eq("user_id",approval.getUserId());
             Integral integral = integralService.selectOne(ew);

             if(integral != null){
                 integral.setCountIntegral(integral.getCountIntegral() + approval.getSqIntegral());
                 integral.setGoodCountIntegral(integral.getGoodCountIntegral() + approval.getSqIntegral());
//                 synchronized (integral){
//                     integral.setCountIntegral(integral.getCountIntegral() + approval.getSqIntegral());
//                     integral.setGoodCountIntegral(integral.getGoodCountIntegral() + approval.getSqIntegral());
//                 }
             }

             integralService.updateById(integral);

             IntegralLog log = new IntegralLog();
             log.setGetTime(DateUtils.getNowDate());
             log.setIntegralContent(approval.getApprovalTitle());
             log.setRemark(approval.getRemark());
             log.setBianIntegral(approval.getSqIntegral());
             log.setIntegralTitle(approval.getApprovalTitle());
             log.setTypeId(approval.getTypeId());
             log.setApprovalNum(approval.getApprovalNum());
             log.setUserName(approval.getUserName());
             log.setUserId(approval.getUserId());
             log.setUserPhone(approval.getUserPhone());
             log.setUserImg(approval.getUserImg());
             /** 添加部门名称  */
             log.setUserDept(sysDeptService.selectById(approval.getUserDeptId()).getDeptName());
             EntityWrapper<SysUserPost> ew1 = new EntityWrapper<SysUserPost>();
             ew1.eq("user_id",approval.getUserId());
             SysUserPost userPost =  sysUserPostService.selectOne(ew1);
             /** 添加职位名称  */
             if(userPost != null){
                 log.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
                 log.setStatus(UsingStatusConstans.LOG_DEFULT);
             }
             boolean flag =  integralLogService.insert(log);
            i =  this.baseMapper.updateById(approval);
//               SysUser use =     ShiroUtils.getUserEntity();
//               // 更新一下  session 的值
//             sysUserService.updateById(use);
                /** 悬赏任务 日常，抢单，挑战任务的id  更新 用户对任务类型的状态 */
            if(StringUtils.isNotNull(approval.getApprovalImg8())){
                EntityWrapper<RewardTaskUser> rtu = new EntityWrapper<>();
                rtu.eq("rt_id",Integer.parseInt(approval.getApprovalImg8()));
               List<RewardTaskUser> list =  rewardTaskUserService.selectList(rtu);
                /** 用户对任务类型单据完成状态 */
                for (RewardTaskUser taskUser : list) {
                    if(taskUser.getState().equals("2")){
                        taskUser.setState(UserConstants.USER_STATUS_SUCCESS+"");
                        boolean flag1  = rewardTaskUserService.updateById(taskUser);
                    }
                }

//
                /** 审批通过  更新抢单任务类型的状态  */
                RewardTask rewardTask =  rewardTaskService.selectById(approval.getApprovalImg8());
                if(rewardTask !=null){
                    if (rewardTask.getTaskTypeId().equals(UserConstants.GRAB_TASK) ||
                            rewardTask.getTaskTypeId().equals(UserConstants.DAY_TASK) ||
                            rewardTask.getTaskTypeId().equals(UserConstants.CHALLENGE_TASK)){
                        if(rewardTask.getPeopleNum() !=null){
                            Integer peopleNum = rewardTask.getPeopleNum();
//
                            /**  抢单人数和 用户任务的人数一样更新任务单据的状态改成完成 */
                            if(peopleNum==0){
                                // todo
                                rewardTask.setStatus(UserConstants.TASK_OK+"");
                                boolean flag2 = rewardTaskService.updateById(rewardTask);
                            }
                        }
                        /** 更新减少管理奖扣的分数 */
                        boolean bb = rewardTaskService.updateById(rewardTask);
                        //添加审批表的申请积分
                        approval.setSqIntegral(rewardTask.getTaskIntegral());
                        i =  this.baseMapper.updateById(approval);
                    }
                }
            }
    }
    /** 审批不通过 */
        if (status.equalsIgnoreCase("2")){
            approval.setStatus(2);
            approval.setSpTime(DateUtils.getNowDate());
            /** 审批不通过理由 */
            approval.setApprovalImg9(keyWord);
            i = this.baseMapper.updateById(approval);
            /** 悬赏任务 日常，抢单，挑战任务的id  更新 用户对任务类型的状态 */
            if(StringUtils.isNotNull(approval.getApprovalImg8())){
                EntityWrapper<RewardTaskUser> rtu = new EntityWrapper<>();
                rtu.eq("rt_id",Integer.parseInt(approval.getApprovalImg8()));
                rtu.eq("user_id",approval.getUserId());

                RewardTaskUser taskUser =  rewardTaskUserService.selectOne(rtu);
                /** 用户对任务类型单据审批不通过状态 */
                 taskUser.setState(UserConstants.USER_STATUS_FAIL+"");
                boolean flag1  = rewardTaskUserService.updateById(taskUser);
                /** 审批不通更新抢单任务类型的人数 */
                RewardTask rewardTask =  rewardTaskService.selectById(approval.getApprovalImg8());

                if (rewardTask.getTaskTypeId().equals(UserConstants.GRAB_TASK)){
                    rewardTask.setPeopleNum(rewardTask.getPeopleNum()+1);
                    // 更新谁发布任务管理员的分数
                    updateUserManage(rewardTask);
                    rewardTaskService.updateById(rewardTask);
                }
                /** 如果是挑战任务 扣除挑战任务分数的 1.5倍  */
                if(rewardTask.getTaskTypeId().equals(UserConstants.CHALLENGE_TASK)){
                    //更新挑战人数
                    rewardTask.setPeopleNum(rewardTask.getPeopleNum()+1);
                    rewardTaskService.updateById(rewardTask);
                    // 修改管理奖扣积分 在原有的管理奖扣分值    *1.5 倍
                    Integer row =  addPubManagement(rewardTask);
                    // 修改挑战人的积分 Double.parseDouble(kIntegral+"")
                   Double kIntegral = rewardTask.getTaskIntegral()*UserConstants.CHALLENGE_SCORE;
                    // 更新积分商城 显示商城总积分
                    EntityWrapper<Integral> in = new EntityWrapper<>();
                    in.eq("user_id",approval.getUserId());
                    Integral intg = integralService.selectOne(in);
                    synchronized (intg){
                        intg.setGoodCountIntegral(intg.getGoodCountIntegral() - kIntegral.intValue());
                        intg.setCountIntegral(intg.getCountIntegral() - kIntegral.intValue());
                        integralService.updateById(intg);
                    }

                    // 抢单任务
                    approval.setStatus(1);
                     approval.setSqIntegral(0);
                     approval.setkIntegral(kIntegral.intValue());
                   Integer flag =  this.baseMapper.updateById(approval);

                }
            }
        }
        if (status.equalsIgnoreCase("3")){
            approval.setStatus(3);
            approval.setSpTime(DateUtils.getNowDate());
            /** 撤销审批理由 */
            approval.setApprovalImg9(keyWord);
            i = this.baseMapper.updateById(approval);

            /** 悬赏任务 日常，抢单，挑战任务的id  更新 用户对任务类型的状态 */
            if(StringUtils.isNotNull(approval.getApprovalImg8())){
                EntityWrapper<RewardTaskUser> rtu = new EntityWrapper<>();
                rtu.eq("rt_id",Integer.parseInt(approval.getApprovalImg8()));
                RewardTaskUser taskUser =  rewardTaskUserService.selectOne(rtu);
                /** 用户对任务类型单据撤销审批状态 */
                taskUser.setState(UserConstants.USER_STATUS_CHEXIAO+"");
                boolean flag1  = rewardTaskUserService.updateById(taskUser);
                /** 审批不通更新抢单任务类型的人数 */
                RewardTask rewardTask =  rewardTaskService.selectById(approval.getApprovalImg8());
                if (rewardTask.getTaskTypeId().equals(UserConstants.GRAB_TASK)){
                    rewardTask.setPeopleNum(rewardTask.getPeopleNum()+1);
                    // 更新谁发布任务管理员的分数
                    updateUserManage(rewardTask);
                    boolean bb = rewardTaskService.updateById(rewardTask);
                }
                /** 如果是挑战任务 扣除挑战任务分数的 1.5倍  */
                if(rewardTask.getTaskTypeId().equals(UserConstants.CHALLENGE_TASK)){
                    // 修改管理奖扣积分
                    Integer row =  addPubManagement(rewardTask);
                    // 修改挑战人的积分 Double.parseDouble(kIntegral+"")
                    Double kIntegral = rewardTask.getTaskIntegral()*UserConstants.CHALLENGE_SCORE;

                    // 更新积分商城 显示商城总积分
                    EntityWrapper<Integral> in = new EntityWrapper<>();
                    in.eq("user_id",approval.getUserId());
                    Integral intg = integralService.selectOne(in);
                    synchronized (intg){
                        intg.setGoodCountIntegral(intg.getGoodCountIntegral() - kIntegral.intValue());
                        intg.setCountIntegral(intg.getCountIntegral() - kIntegral.intValue());
                        integralService.updateById(intg);
                    }

                    // 抢单任务
                    approval.setStatus(1);
                    approval.setSqIntegral(0);
                    approval.setkIntegral(kIntegral.intValue());
                    Integer flag =  this.baseMapper.updateById(approval);

                }

            }
        }
        return i;
    }
    /**   更新谁发布任务管理员的分数 */
    private void updateUserManage(RewardTask rewardTask) {
        SysUser user = sysUserService.selectById(rewardTask.getReleaseUserId());
        user.setManAward(user.getManAward() + rewardTask.getTaskIntegral());
//        synchronized (user){
//
//        }
        sysUserService.updateById(user);
    }

    /** 修改管理奖扣积分 */
    private Integer addPubManagement(RewardTask rewardTask) {
     SysUser user = sysUserService.selectById(rewardTask.getReleaseUserId());
        Double kIntegral = rewardTask.getTaskIntegral()*UserConstants.CHALLENGE_SCORE;
        user.setManAward(user.getManAward()+ kIntegral.intValue());
//     synchronized (user){
//
//     }
        boolean row = sysUserService.updateById(user);
        if (row){
            return 1;
        }

        return 0;
    }


    /** 审批通过添加日志 */
    private int addLog(IntegralApproval approval) {
        IntegralLog log = new IntegralLog();
            log.setGetTime(DateUtils.getNowDate());
            log.setIntegralContent(approval.getApprovalTitle());
            log.setRemark(approval.getRemark());
            log.setBianIntegral(approval.getSqIntegral());
            log.setIntegralTitle(approval.getApprovalTitle());
            log.setTypeId(approval.getTypeId());
            log.setApprovalNum(approval.getApprovalNum());
            log.setUserName(approval.getUserName());
            log.setUserId(approval.getUserId());
            log.setUserPhone(approval.getUserPhone());
            log.setUserImg(approval.getUserImg());
            /** 添加部门名称  */
            log.setUserDept(sysDeptService.selectById(approval.getUserDeptId()).getDeptName());
            EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
            ew.eq("user_id",approval.getUserId());
             SysUserPost userPost =  sysUserPostService.selectOne(ew);
           /** 添加职位名称  */

           if(userPost != null){
               log.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
               log.setStatus(UsingStatusConstans.LOG_DEFULT);
           }

     boolean flag =  integralLogService.insert(log);
        if (flag){
            return 1;
        }
         return 0;
    }



    /** 管理员显示待我审批的数量 */
    @Override
    public Integer selectCountDwsp(Integer userId) {
        return this.baseMapper.selectCountDwsp(userId);
    }

    /**
     *   领导表扬加分  from 给员工申请
     */
    @Override
    public boolean leaderIntegral(SysUser user,String approvalContent, String approvalTitle, String typeId,  String[] approvalImg11, String addIntegral, String spRemark, String[] from,String dateTime) {
        int i =0;

        int ids =0;
        for (String id : from) {
        int row = insertApproval(addIntegral,null,spRemark,typeId,approvalContent,approvalTitle,approvalImg11,user,id,dateTime);
        int rowLog = insertLog(addIntegral,null,spRemark,typeId,approvalContent,approvalTitle,dateTime,id);

         i=  updateIIntegral(id,addIntegral,null,typeId);
            ids++;
        }
         SysUser sysUser =  sysUserService.selectById(user.getUserId());

        if(ids > 0){
            if (addIntegral !=null && addIntegral !=""){
            Integer  leaderCountIntegral = ids * Integer.parseInt(addIntegral);
            synchronized (sysUser){
                sysUser.setBiaoIntegral(sysUser.getBiaoIntegral() - leaderCountIntegral);
            }
            boolean flag   = sysUserService.updateById(sysUser);

            }

        }
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     *  积分榜查看一个人所的日志列表
     * */
    @Override
    public PageInfo<IntegralApproval> selectPersonalList(String userId, String pageSize, String pageNum) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        EntityWrapper<IntegralApproval> ew = new EntityWrapper<>();
        /** 审批通过的 */
        ew.eq("user_id",userId);
        ew.eq("status","1");
        ew.orderBy("sp_time").last("desc");
        List<IntegralApproval>  logs = integralApprovalMapper.selectList(ew);

        return   new PageInfo<>(logs);

    }

    /** 根据当前用户部门id 在本部门的排名显示什么样的冠军 */
    @Override
    public List<Map> selectCommonGJ(Integer deptId) {
        return this.baseMapper.selectCommonGJ(deptId);
    }


//    /** 查询所有人员的排名 */
//    @Override
//    public List<Map> selectAllGJ(Integer roleId) {
//        return this.baseMapper.selectAllGJ(roleId);
//    }


    /**
     *  自由奖扣添加申批记录
     */
    private int insertIntegralApproval( SysUser sysUser,String addIntegral, String delIntegral, String spRemark, String typeId, String approvalContent, String approvalTitle, String approvalId, String[] approvalImg1, String[] apps, String[] to,String id, String[] from) {

        IntegralApproval approval = new IntegralApproval();
        approval.setApprovalNum("JFB"+DateUtils.dateTime());
        if(delIntegral !=null){
            approval.setkIntegral(Integer.parseInt(delIntegral));
        }
        if(addIntegral !=null){
            approval.setSqIntegral(Integer.parseInt(addIntegral));
        }
        /** 奖励那个人的 id  */
        SysUser user =  sysUserService.selectById(id);
        approval.setUserId(user.getUserId());
        approval.setUserDeptId(user.getDeptId());
        approval.setUserPhone(Long.parseLong(user.getPhonenumber()));
        approval.setUserDept(sysDeptService.selectById(user.getDeptId()).getDeptName());
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",user.getUserId());
        SysUserPost userPost =  sysUserPostService.selectOne(ew);
        approval.setUserPostId(userPost.getPostId());
        approval.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
        approval.setUserName(user.getUserName());
        approval.setUserImg(user.getAvatar());
        approval.setSqTime(DateUtils.getNowDate());

        approval.setSpRemark(spRemark);
        if(typeId !=null){
            /*approval.setTypeId(Integer.parseInt(typeId));*/
            approval.setIntegralTypeId(Integer.parseInt(typeId));
        }
        approval.setApprovalTitle(approvalTitle);
        approval.setApprovalContent(approvalContent);
        /** 提交那个人的 id   from 申请人  to 抄送人    apps 审批人        */
        approval.setTiJiaoId(sysUser.getUserId());
        approval.setTiJiaoNameImg(sysUser.getAvatar());
        approval.setTiJiaoName(sysUser.getUserName());
        approval.setTiJiaoRenIds(StringUtils.join(from, ","));


        approval.setShenPiRenIds(StringUtils.join(apps, ","));
        /** 自由奖扣审批的默认状态 */
        approval.setStatus(UsingStatusConstans.APP_DEFULT);
        approval.setApprovalImg1(StringUtils.join(approvalImg1, ","));

        int row = this.baseMapper.insert(approval);

        if(approval.getMenuId() != null){
            approval.setMenuId(approval.getMenuId()+approval.getApprovalId()+",");
        }else{
            approval.setMenuId(approval.getApprovalId()+",");
        }
        if(sysUser.getApplyIds() !=null){
            sysUser.setApplyIds(sysUser.getApplyIds()+ approval.getApprovalId()+",");
        }else{
            sysUser.setApplyIds(approval.getApprovalId()+",");
        }
        /** 添加抄送表 */
        addChaoSongRen(to,sysUser,approval.getApprovalId());
        int row1 =  this.baseMapper.updateById(approval);
       boolean aa =   sysUserService.updateById(sysUser);
      return  row;
    }
    /** 添加抄送表 */
    private void addChaoSongRen(String[] to, SysUser sysUser, Integer approvalId) {
        if(to !=null){
            for (String id : to) {
                ChaoSong cs = new ChaoSong();
                cs.setUserId(sysUser.getUserId());
                cs.setChaoSongId(Integer.parseInt(id));
                cs.setApprovalId(approvalId);
                cs.setChaoTime(DateUtils.getNowDate());
                boolean b = chaoSongService.insert(cs);
        }

        }
    }

    /**
     * 添加申批记录里
     */
    private int insertApproval(String addIntegral, String delIntegral, String spRemark, String typeId, String approvalContent, String approvalTitle, String[] approvalImg1,SysUser sysUser,String id,String dateTime) {
        /** 更新用户管理奖扣积分值 */


        IntegralApproval approval = new IntegralApproval();
        approval.setApprovalNum("JFB"+DateUtils.dateTime());
        if(delIntegral !=null && delIntegral !=""){
            approval.setkIntegral(Integer.parseInt(delIntegral));

        }
        if(addIntegral !=null  && addIntegral !=""){
            approval.setSqIntegral(Integer.parseInt(addIntegral));
        }
        /** 奖励那个人的所用信息 */
        SysUser user =  sysUserService.selectById(id);
        approval.setUserId(Integer.parseInt(id));
        approval.setUserDeptId(user.getDeptId());
        approval.setUserPhone(Long.parseLong(user.getPhonenumber()));
        approval.setUserDept(sysDeptService.selectById(user.getDeptId()).getDeptName());
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",user.getUserId());
        SysUserPost userPost =  sysUserPostService.selectOne(ew);
        approval.setUserPostId(userPost.getPostId());
        approval.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
        approval.setUserName(user.getUserName());
        approval.setUserImg(user.getAvatar());

    if (dateTime != null && dateTime !="") {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            approval.setSpTime(format.parse(dateTime));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
        approval.setSqTime(DateUtils.getNowDate());
        approval.setSpRemark(spRemark);
        if(typeId !=null){
            approval.setIntegralTypeId(Integer.parseInt(typeId));
        }
        approval.setApprovalTitle(approvalTitle);
        approval.setApprovalContent(approvalContent);
        /** 提交那个人的信息也就相当于管理员的信息 */
        approval.setTiJiaoId(sysUser.getUserId());
        approval.setTiJiaoNameImg(sysUser.getAvatar());
        approval.setTiJiaoName(sysUser.getUserName());
        approval.setStatus(1);
        approval.setSpTime(DateUtils.getNowDate());
        if (approvalImg1 !=null ){
            approval.setApprovalImg1(StringUtils.join(approvalImg1, ","));
        }

        int row = this.baseMapper.insert(approval);
        return  row;
    }
    /**
     *  添加管理员奖扣 日志
     *
     * @param addIntegral
     * @param delIntegral
     * @param id
     * @param typeId
     */
    private Integer  updateIIntegral(String id, String addIntegral, String delIntegral,String typeId) {
        EntityWrapper<Integral> ew = new EntityWrapper<Integral> ();
        ew.eq("user_id",id);
        Integral integral =  integralService.selectOne(ew);
        synchronized (integral){
            if(addIntegral != null && addIntegral !=""){
                integral.setCountIntegral(integral.getCountIntegral() + Integer.parseInt(addIntegral));
                integral.setGoodCountIntegral(integral.getGoodCountIntegral() + Integer.parseInt(addIntegral));
            }
            if(delIntegral != null && delIntegral !=""){
                integral.setCountIntegral(integral.getCountIntegral() - Integer.parseInt(delIntegral));
                integral.setGoodCountIntegral(integral.getGoodCountIntegral() - Integer.parseInt(delIntegral));
            }
        }

      boolean flag = integralService.updateById(integral);
        if(flag){
           return 1;
        }
        return 0;
    }

    /**
     *  添加管理员奖扣 日志
     *
     * @param addIntegral
     * @param delIntegral
     * @param spRemark
     * @param typeId
     * @param approvalContent
     * @param approvalTitle
     */
    private int insertLog(String addIntegral, String delIntegral, String spRemark, String typeId, String approvalContent, String approvalTitle,String dateTime,String id) {
    SysUser user =    sysUserService.selectById(id);
        IntegralLog log = new IntegralLog();
        log.setApprovalNum("JFB"+DateUtils.dateTime());
        if(addIntegral !=null && addIntegral !=""){
            log.setBianIntegral(Integer.parseInt(addIntegral));
        }
        if(delIntegral !=null && delIntegral !=""){
            log.setkIntegral(Integer.parseInt(delIntegral));
        }
        if(typeId != null){
            log.setTypeId(Integer.parseInt(typeId));
        }
        /** 代表领导奖励的日志 */
        log.setStatus(UsingStatusConstans.ADMIN__LOG_DEFULT);
        log.setUserId(user.getUserId());
        log.setUserImg(user.getAvatar());
        log.setUserPhone(Long.parseLong(user.getPhonenumber()));
        log.setUserName(user.getUserName());
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",user.getUserId());
        SysUserPost userPost =  sysUserPostService.selectOne(ew);
        if(userPost !=null){
            SysPost post =  sysPostService.selectById(userPost.getPostId());
            if(post !=null){
                log.setUserPost(post.getPostName());
            }
        }

        SysDept dept =  sysDeptService.selectById(user.getDeptId());
        if(dept !=null){
            log.setUserDept(dept.getDeptName());
        }

        log.setRemark(spRemark);
        log.setIntegralTitle(approvalTitle);
        log.setIntegralContent(approvalContent);

        if(dateTime != null && dateTime !="") {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                log.setGetTime(format.parse(dateTime));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        log.setGetTime(DateUtils.getNowDate());
        boolean flag = integralLogService.insert(log);
       if(flag){
           return 1;
       }
       return 0;
    }


    /**
     *  查询审批日志列表
     * @param pageModelParams
     */
    @Override
    public PageInfo<IntegralApproval> selectIntegralApprovalLog(SysUser user,String pageSize,String pageNum) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        EntityWrapper<IntegralApproval> ew = new EntityWrapper<>();
              ew.eq("user_id",user.getUserId());
              ew.eq("status",0);
        List<IntegralApproval> list = this.baseMapper.selectList(ew);
        Collections.reverse(list);
        return new PageInfo<>(list);
    }

    /**
     *  查询有多少人给自己抄送未审批的数量
     */
    @Override
    public Integer selectCsrs(Integer userId) {
        Integer  num = chaoSongService.selectNotAppCount(userId);

        return num;
    }

    /**
     *  查看审批日志列表
     * @param list
     */
    @Override
    public PageInfo<IntegralApproval> selectApproverLog(SysUser user,String pageSize,String pageNum) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        EntityWrapper<IntegralApproval> ew = new EntityWrapper<>();
        ew.eq("ti_jiao_id",user.getUserId());
       // ew.eq("status",0);
        List<IntegralApproval> list = this.baseMapper.selectList(ew);
        return  new PageInfo<>(list);

    }

    /**
     *  查询我发起的列表日志 审核通过和不通过的列表
     * @param list
     */
    @Override
    public PageInfo<IntegralApproval> selectMyFq(SysUser user,Integer pageSize,Integer pageNum,String status,String search) {
        //分页
        PageHelper.startPage(pageNum ,pageSize);

        EntityWrapper<IntegralApproval> ew = new EntityWrapper<>();
        if(status.equals("0")){
            ew.like("user_name",search);
            ew.eq("ti_jiao_id",user.getUserId());
            ew.eq("status",0);
            ew.orderBy("sq_time").last("desc");
        }
       else if(status.equals("1")){
            ew.like("user_name",search);
            ew.eq("ti_jiao_id", user.getUserId());
            ew.in("status","1,2");
            ew.orderBy("sq_time").last("desc");
        }
        List<IntegralApproval> list = this.baseMapper.selectList(ew);

        return new PageInfo<>(list);
    }


    /**
     *  抄送我的未审核列表   1，审核通过 2.审核不通过
     * @param list
     */
    @Override
    public PageInfo<IntegralApproval> selectCswdList(SysUser user,String pageSize,String pageNum,String search,String status) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));

        List<IntegralApproval> list = null;
       if(status.equals("0")){
           list   = this.baseMapper.selectCswdNot(search,user.getUserId());
       }
       /**1，审核通过 2.审核不通过 */
        if(status.equals("1")){
           list   = this.baseMapper.selectCswdYesAndCheXiao(search,user.getUserId());
       }
        return new PageInfo<>(list);
    }


    /**
     *  积分申诉通过列表 和撤销审批、审批不通过列表
     * @param user
     */
    @Override
    public PageInfo<IntegralApproval> selectIntegralListYes(SysUser user,String pageSize,String pageNum,Integer time) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        List<IntegralApproval> list = null;
        EntityWrapper<IntegralApproval> ew = new EntityWrapper<>();
        ew.eq("user_id", user.getUserId());
        if(time==1){
            ew.eq("status", time);
            ew.orderBy("sq_time").last("desc");
            list  = this.baseMapper.selectList(ew);
        }else if(time==2){
            ew.eq("approval_img6","0");
            ew.in("status","2,3");
            ew.orderBy("sq_time").last("desc");
            list  = this.baseMapper.selectList(ew);
        }
        for (IntegralApproval approval : list) {
            if(approval.getSpRemark() !=null){
                approval.setSpRemark(approval.getSpRemark());
            }else{
                approval.setSpRemark("");
            }
        }
        return new PageInfo<>(list);
    }



    /** 添加一条 挑战任务 */
    @Override
    public boolean addChallenges(String taskId, String integralTypeId, String appWay, String releaseUserId, String [] pic, String[] to, SysUser user,String remark) {
        int  row = 0;
        if(taskId !=null && taskId !="" ||
                integralTypeId !=null && integralTypeId !="" ||
                appWay !=null && appWay !="" ||
                releaseUserId !=null && releaseUserId !="" ||
                pic !=null  || to !=null ||  remark != null &&  remark!=""){
            //查询这条单据的回显数据
            RewardTask  rewardTask = rewardTaskService.selectById(taskId);
            rewardTask.setRemark(remark);
            rewardTaskService.updateById(rewardTask);
            //添加抄送人的id
            addChaoSongRen(to,user,null);
            if(rewardTask !=null){
                // 添加审批  抢单任务   记录
                row =  insertIntegralApprovals(rewardTask.getTaskIntegral(),
                        rewardTask.getRemark(),integralTypeId,appWay,rewardTask.getContent(),
                        rewardTask.getTitle(),pic,user,releaseUserId,rewardTask.getRtId());
                /** 修改 用户对这个 抢单任务的状态为审批中为完成状态 */
                row =  updateRewardTaskUserStatus(rewardTask.getRtId());
            }
        }
        if(row>0) {
            return true;
        }
        return false;
    }



    /** 修改 用户对这个 抢单任务的状态为审批中为完成状态 */
    private Integer updateRewardTaskUserStatus(Integer rtId) {
        EntityWrapper<RewardTaskUser> ew = new EntityWrapper<RewardTaskUser> ();
        ew.eq("rt_id",rtId);
        RewardTaskUser rtu = rewardTaskUserService.selectOne(ew);
        rtu.setState("2");
        /** 状态更新为审批中 未完成状态 */
        boolean row = rewardTaskUserService.updateById(rtu);
        if (row){
            return 1;
        }

        return 0;
    }

    /** 添加审批记录 悬赏任务 日常任务 */
    private int insertIntegralApprovals(Integer taskIntegral, String remark, String integralTypeId, String appWay, String content, String title, String[] pic, SysUser user, String releaseUserId,Integer taskId) {
        IntegralApproval approval = new IntegralApproval();
        approval.setApprovalNum("JFB"+DateUtils.dateTime());
        approval.setSqIntegral(taskIntegral);
        /** 奖励那个人的所用信息 */
        approval.setUserId(user.getUserId());
        approval.setUserDeptId(user.getDeptId());
        approval.setUserPhone(Long.parseLong(user.getPhonenumber()));
        approval.setUserDept(sysDeptService.selectById(user.getDeptId()).getDeptName());
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",user.getUserId());
        SysUserPost userPost =  sysUserPostService.selectOne(ew);
        approval.setUserPostId(userPost.getPostId());
        approval.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
        approval.setUserName(user.getUserName());
        approval.setUserImg(user.getAvatar());
        approval.setSqTime(DateUtils.getNowDate());
        approval.setSpRemark(remark);
        approval.setIntegralTypeId(Integer.parseInt(integralTypeId));
        approval.setApprovalTitle(title);
        approval.setStatus(UserConstants.APP_DEFULT);
        String name = null ;
        if(appWay.equalsIgnoreCase("1")){
            name ="日常任务";
        }else if(appWay.equalsIgnoreCase("2")){
            name ="抢单任务";
        }else{
            name ="挑战任务";
        }
        approval.setApprovalContent(content+"++++"+name);
        /** 添加日常，抢单，挑战任务 id  主键  */
        approval.setApprovalImg8(taskId+"");
        /** 提交那个人的信息也就相当于管理员的信息 */
        SysUser sysUser =  sysUserService.selectById(releaseUserId);
        approval.setTiJiaoId(sysUser.getUserId());
        approval.setTiJiaoNameImg(sysUser.getAvatar());
        approval.setTiJiaoName(sysUser.getUserName());
        approval.setSpTime(DateUtils.getNowDate());
        approval.setApprovalImg1(StringUtils.join(pic, ","));
        approval.setShenPiRenIds(releaseUserId);
        int row = this.baseMapper.insert(approval);
        if (row >0 ){
            return  row;
        }
        return  0;
    }

    /** 统计总积分 */
    @Override
    public Long selectCountIntegral(Integer userId) {

        return this.baseMapper.selectCountIntegral(userId);
    }

    /** 个人中心Echarts显示数量  */
    @Override
    public Map selectAddAndCountDelIntegral(Integer userId) {
        return this.baseMapper.selectAddAndCountDelIntegral(userId);
    }

    /**
     *  积分申诉  审批通过 、撤销审批、审批不通过详情页
     */
    @Override
    public IntegralApproval selectComplaintDetails(String approvalId) {
        return this.baseMapper.selectById(approvalId);
    }

    /**
     *  积分申诉  添加提交一条审批不通过 或者 撤销审批
     */
    @Override
    public boolean addComplaint(String approvalId, String appReason, String appPelId, String[] pic, String[] to, SysUser user) {
        Integer i = null;
        if(     approvalId !=null && approvalId !="" ||
                appReason !=null && appReason !="" ||
                appPelId !=null && appPelId !="" ||
                pic !=null || to !=null ){
            /** 添加一条审批记录 */
           i =  addComplaintIntegralApproval(approvalId,appReason,appPelId,pic,to,user);
            /** 抄送人 */
            addChaoSongRen(to,user, Integer.parseInt(approvalId));
        }
        if(i >0 ){
         return true;
        }
        return false;
    }
    /** 通过用户 审批日志  */
    @Override
    public Integer selectCountNum(Integer userId) {
        return this.baseMapper.selectCountNum(userId);
    }


    /** 积分申诉到指定审批  */
    private Integer addComplaintIntegralApproval(String approvalId, String appReason, String appPelId, String[] pic, String[] to, SysUser user) {
        IntegralApproval ia =  this.baseMapper.selectById(approvalId);

        IntegralApproval approval = new IntegralApproval();
        approval.setApprovalId(ia.getApprovalId());
        approval.setApprovalNum(ia.getApprovalNum());
        approval.setSqIntegral(ia.getSqIntegral());
        /** 奖励那个人的所用信息 */
        approval.setUserId(user.getUserId());
        approval.setUserDeptId(user.getDeptId());
        approval.setUserPhone(Long.parseLong(user.getPhonenumber()));
        approval.setUserDept(sysDeptService.selectById(user.getDeptId()).getDeptName());
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",user.getUserId());
        SysUserPost userPost =  sysUserPostService.selectOne(ew);
        approval.setUserPostId(userPost.getPostId());
        approval.setUserPost(sysPostService.selectById(userPost.getPostId()).getPostName());
        approval.setUserName(user.getUserName());
        approval.setUserImg(user.getAvatar());
        approval.setSqTime(DateUtils.getNowDate());
        approval.setSpRemark(ia.getSpRemark());
        approval.setIntegralTypeId(ia.getIntegralTypeId());
        approval.setApprovalTitle(ia.getApprovalTitle());
        approval.setStatus(UserConstants.APP_DEFULT);

        approval.setApprovalContent(ia.getApprovalContent());
        /** 添加日常，抢单，挑战任务 id  主键  */
        approval.setApprovalImg8(approvalId);
        /** 提交那个人的信息也就相当于管理员的信息 */
        SysUser sysUser =  sysUserService.selectById(user.getUserId());
        approval.setTiJiaoId(sysUser.getUserId());
        approval.setTiJiaoNameImg(sysUser.getAvatar());
        approval.setTiJiaoName(sysUser.getUserName());
        approval.setSpTime(DateUtils.getNowDate());
        approval.setApprovalImg1(StringUtils.join(pic, ","));
        /** 积分申诉 指定人的 id  */
        approval.setShenPiRenIds(appPelId);
        /** 二次申诉理由  todo   */
        approval.setApprovalImg7(appReason);
        /** 无论审批通过不同最终修改未不能重复申诉状态 */
        approval.setApprovalImg6(UserConstants.COMPLAINT_FAIL);
        Integer row =   this.baseMapper.updateById(approval);
     //  this.baseMapper.insert(approval);
       return row;
    }
}
