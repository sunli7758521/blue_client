package com.msj.goods.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.Integral;
import com.msj.goods.entity.LevelAss;
import com.msj.goods.entity.LevelTitle;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sun li
 * @Date 2019/3/20 16:15
 * @Description
 */
@RestController
@RequestMapping("/Leveltitle")
@Api(description = "水平考核科目 里面的试题")
public class LevelTitleController {


    @Autowired
    private  AssessmentStateService assessmentStateService;
    @Autowired
    private LevelTitleService levelTitleService;
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private LevelAssService levelAssService;
    @Autowired
    private IntegralApprovalService integralApprovalService;
    @Autowired
    private IntegralService integralService;

    @PostMapping(value = "/LevelTitleController/{levelId}/{shenQingFangShi}") /** 根据传入的科目id去找到 相关的考题*/
    @Log(title = "展示所有的水平考核试题列表")
    @ApiOperation(value = "水平考核试题", notes = "水平考核里面的试题展示数据")
    @ApiImplicitParam(name = "levelId", value = "json对象", required = true)
    public JsonResult index(@RequestParam("shenQingFangShi") String shenQingFangShi, ModelMap map, @RequestParam("levelId") Integer levelId, @RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum) {

        PageInfo<LevelTitle> pageInfo = levelTitleService.selectByLevelId(levelId, pageSize, pageNum); /**先根据 水平考核科目的id 去获取 考题*/
        return JsonResult.success(pageInfo, JsonResultConstants.SUCCESS);
    }

     /**
     * 根据前台返回的 值  进行 积分增加
     */
    @PostMapping(value = "/servehotselectiveajax/{levelId}/{shenQingFangShi}")
    @Log(title = "进行提交 申请")
    @ApiOperation(value = "水平考核试题 申请提交", notes = "水平考核里面的试题 提交")
    public JsonResult servehotselectiveajax( HttpServletRequest req, HttpServletResponse resp, @RequestBody JSONObject obj) {

        String data = obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String modifyArr = json.getString("modifyArr");
        String shenQingFangShi = json.getString("shenQingFangShi");
        String III = json.getString("levelId");
        Integer  levelId   =Integer.parseInt(String.valueOf(III));/** 科目类型转换的类型*/
        System.out.println(shenQingFangShi+"ASDASDASDADADADAD");
         /** 查询出来 这个科目 类型的  单条信息*/
        LevelAss abc = levelAssService.selectLevelAssid(levelId);/** 根据科目表的id 查询到科目 实体全部 */
        Integer tiaoshu     = abc.getNumberid();/**获取到 当前的科目考核条数*/
        Integer  numberid  = tiaoshu+1;
        /**获取到当前的条数累计加一*/

        Integer geshu   = levelAssService.insertLevelAss(levelId,numberid);
        System.out.println(geshu+"看看我的个数 为多少？？？？？？");

        int num = 0; /**初始化 积分*/
        String shenPiRenIds="";/** 初始化 出题人的id*/
        /*  if(StringUtils.isNotEmpty(modifyArr)){*/
        JSONArray modifyArray = JSONArray.parseArray(modifyArr);
        /*modifyArray={"modifyArray"[{"id":1,"zhi":"0"},{"id":"2":"zhi"}]}*/
        String cid = ShiroUtils.getUserId();/** 获取到 登录人的 id*/
        int userId = Integer.valueOf(cid);  /** 根据 id  转类型*/

        /** 我需要 获取到 登录人的id  根据 shiro  */
        /** 其次 我需要 获取到 他的 科目id，*/
            int fanhuizhi= assessmentStateService.insertassessmentState(userId,levelId,1,shenQingFangShi, DateUtils.getNowDate());
        System.out.println(fanhuizhi+"看到");
        SysUser user = sysUserService.selectuserid(userId);/**获取到 当前登录人的详细信息*/
            String tiJiaoRenIds= String.valueOf(user.getUserId());
              int tiJiaoId=user.getUserId();
             String userName= user.getUserName();
             int userDeptId=user.getDeptId();
        String  Phone    =  user.getPhonenumber();/** 获取到他的手机号*/


           /*   int userId =user.getUserId();*/ /**获取到的 登录人的id*/
        for (int i = 0; i < modifyArray.size(); i++) {
            Integer tId = JSONObject.parseObject(JSONObject.toJSONString(modifyArray.get(i))).getInteger("tId");
            String tEnd = JSONObject.parseObject(JSONObject.toJSONString(modifyArray.get(i))).getString("tEnd");
            LevelTitle aa = levelTitleService.leveltitleshuai(tId);/** 查询出来一个单条的选择题*/
            String shenqingren = aa.getLevelId();/**获取到科目表的id*/
            int levelcd = Integer.valueOf(shenqingren);/**强转类型*/
            LevelAss ass = levelAssService.selectLevelAssid(levelcd);/** 根据科目表的id 查询到科目 实体全部 */
                 long chutiren = ass.getUserIds();/**获取到 出题人的id*/
            shenPiRenIds = String.valueOf(chutiren);/** 出题人转换的类型*/
            String b = aa.gettEnd();/** 正确答案*/
            long daan = aa.gettIntegral(); /**积分 奖励*/
            int jifen = Integer.parseInt(String.valueOf(daan));/** 积分奖励转换的类型*/
            if (tEnd.equals(b)) {
                num += jifen;
            }
        }
        /** 我需要添加一个 出题人的id，需要添加一个一个做题人的 信息，还有需要添加一个行为类型，审批通过，*/
     /*   req.getSession().setAttribute("num",num);*//** 把 积分 奖励 进行累加*/
       int rows= integralApprovalService.addxinxi( num,Phone ,userId,shenPiRenIds,tiJiaoRenIds,tiJiaoId,1,10,userName,userDeptId);
              Integral integral = integralService.selectdan(userId);
                  int  shangcheng=integral.getGoodCountIntegral();
                  int goodCountIntegral=shangcheng+num;  /**积分商城 显示的信息*/
             int integralId=integral.getIntegralId();/** 主键id*/
             /* String username=integral.getUserName();*//** 姓名*/
              /*int userid=integral.getUserId();*//**关联的 那个 用户 id*/
             int zongfenshu= integral.getCountIntegral();/** 总积分*/
               int countIntegral= zongfenshu+num; /** 这个 获取到是 总共的积分*/
        int   zjifen    = integral.getAddIntegral();/** 获取到 之前增加的积分*/
                int addIntegral= zjifen+num; /** 奖励的积分*/
            /* String shoujihao=integral.getUserPhone();*//** 用户的手机号*/
            /* int kouchu=integral.getDelIntegral();*//** 扣除的 积分*/
             /*int jiangli=integral.getAddIntegral();*//**奖励的积分*/
             int zhengque=0;
        int bbc=integralService.updatexiugai(integralId,countIntegral,addIntegral,goodCountIntegral);


                   if (rows>0 || bbc>0){
                       zhengque=1;
                       return JsonResult.success(zhengque, JsonResultConstants.SUCCESS);
                   }
        return JsonResult.success(zhengque, JsonResultConstants.SUCCESS);
    }
}