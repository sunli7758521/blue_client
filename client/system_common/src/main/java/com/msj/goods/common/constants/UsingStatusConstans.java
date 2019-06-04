package com.msj.goods.common.constants;

/**
 * @author sun li
 * @Date 2018/11/20 10:14
 * @Description
 */
public class UsingStatusConstans {
    /**
     *  日志使用状态
     *      状态 （0正常    1.撤销刚才所加的积分 2.管理员添加积分）
     */

    public static final Integer LOG_DEFULT = 0;
    public static final Integer CHEN_XIAO_LOG_DEFULT = 1;
    public static final Integer ADMIN__LOG_DEFULT = 2;

    /**
     *  审批的使用状态
     *      审批状态(0,审批中 1审批通过，2审批不通过，3撤销审批 )
     */
    public static final Integer APP_DEFULT = 0 ;
    public static final Integer APP_SUCCESS = 1;
    public static final Integer APP_FAIL = 2;
    public static final Integer APP_CHE_XIAO = 3;
}
