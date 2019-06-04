package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.constants.Constants;
import com.msj.goods.common.utils.DatabaseUtil;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.SysUserDeptPost;
import com.msj.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author sun li
 * @Date 2018/12/11 14:24
 * @Description
 */
@Service
public class ScheduledController {

    @Autowired
    private AssessmentStateService assessmentStateService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserPostService sysUserPostService;

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserDeptPostService sysUserDeptPostService;

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private XwIntegralService xwIntegralService;

    @Autowired
    private IntegralRecordService integralRecordService;



        /**   0 0 1 * * ?  每天凌晨1点清空     0/5 * * * * *  每隔5秒清空一次
         *   0 0 1 * * ?
         * */

    @Scheduled(cron = "0 0 1 * * ?")
    public void timer(){
        EntityWrapper<SysUser> ew = new  EntityWrapper<SysUser>();
        List<SysUser> list =  sysUserService.selectList(ew);

        for (SysUser user : list) {
            user.setApplyIds(" ");
          boolean flag = sysUserService.updateById(user);
          if (flag){
              System.out.println("更新成功");
          }else {
              System.out.println("更新失败");
          }
        }
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前时间为:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * 每周日23点，批量修改用户表的love_integral值为20   0 0 23 ? * SAT
     */
    @Scheduled(cron = "0 0 23 ? * SAT")
    public void updateLoveIntegralTask() {
       this.sysUserService.updateUser(Constants.LOVE_NUM);
    }

    /**
     * 每周日23点 批量修改 水平考核 的状态为0  并且这个是 一周一次的 那个 状态为3
     * */
    @Scheduled(cron = "0 0 23 ? * SAT")
    public  void updatezt(){

        this.assessmentStateService.updatezt();

    }

    /**
     * 每个月1号 凌晨 三点  初始化 管理层 与普通员工的 分值
     * */
    @Scheduled(cron = "0 0 3 1 * ?")

    public void  Award(){
        List<SysUserDeptPost> list = sysUserDeptPostService.findSysUserDeptPost();
        for (int i=0;i<list.size();i++){
            int userId = list.get(i).getUserId();
               int zong = sysUserDeptPostService.findSudpByUserId2(userId);
                  int zf=zong*900;
                int  zong1  = sysUserDeptPostService.findSudpByUserId3(userId);
                   int zf1=zong1*300;
                 int quan= zf+zf1;
            sysUserService.insertUser(userId,quan);
        }

    }




    /**
     *  数据库备份  每天备份一次
     *  每天 上午12执行 备份
     *    0 0 12 * * ?
     *    每天晚上 22点执行
     */

    @Scheduled(cron = "0 0 2 * * ?")
    public void dataBackup(){

        /** mysql 参数依次是IP、账号、密码、数据库名 */
        // 线下数据库地址
//        String command1 = "F:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -hlocalhost -uroot -pmsjkj  msjgoods";
        // 线上数据库地址
        String command1 = "D:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump -hlocalhost -uroot -pmsjkj  msjgoods";
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        /** 数据保存 时间和保存数据备份名称 */
        String savePath1 = "D:/dataBackup/meisijia/meisijia"+localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+".sql";
        boolean b1 = new DatabaseUtil().backup(command1, savePath1);
        if(b1){
            System.out.println("备份成功");
        }else {
            System.out.println("备份失败");
        }
    }


    /**
     * 通过审批表统计每个人的分数 // 0 * /5 * * * ?    查询已兑换的人的分数    最后 更新积分榜表 商城积分和总积分  数据
     */
//    @Scheduled(cron = "0 */4 * * * ?")
//    public  void updateScore(){
//
//        //统计查询所有的分数
//        List<Map> list = integralApprovalService.selectCountScore();
//       // 查询所有人的兑换记录
//        List<Map> recordList =   integralRecordService.selectAllCountYDScore();
//        // 查询所用人的积分
//        EntityWrapper<Integral> ew = new EntityWrapper<Integral>();
//        List<Integral> scordList =  integralService.selectList(ew);
//        // 1.先处理 审批表和积分显示表数据
//        for (Integral integral : scordList) {
//            for (Map app : list) {
//                if(integral.getUserId().equals(app.get("userId"))){
//                    integral.setCountIntegral(Integer.parseInt(app.get("countScore")+""));
//                    integral.setGoodCountIntegral(Integer.parseInt(app.get("countScore")+""));
//                    integralService.updateById(integral);
//                }
//            }
//        }
//        // 2. 在处理积分表和兑换商品的总分数
//        for (Integral integral : scordList) {
//            for (Map record : recordList) {
//                if(integral.getUserId().equals(record.get("userId"))){
//                   synchronized (integral){
//                     integral.setGoodCountIntegral(integral.getGoodCountIntegral() - Integer.parseInt(record.get("countScore")+""));
//                       integralService.updateById(integral);
//                   }
//                }
//            }
//        }
//
//        System.out.println("更新完成=============================");
//    }

}





