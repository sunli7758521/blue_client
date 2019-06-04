package com.msj.goods.common.constants;

/**
 * 用户常量信息
 * 
 * @author ruoyi
 */
public class UserConstants
{
    /** 用户是否离职状态 */
    public static final String YES_LI_ZHI = "1";
    public static final String NO_LI_ZHI = "0";

   /** 用户是否参与积分状态 */
    public static final String NO_INTEGRAL = "1";
    public static final String YES_INTEGRAL = "0";

    /** 正常状态 */
    public static final String NORMAL = "0";

    /** 异常状态 */
    public static final String EXCEPTION = "1";

    /** 用户封禁状态 */
    public static final String USER_BLOCKED = "1";

    /** 角色封禁状态 */
    public static final String ROLE_BLOCKED = "1";

    /** 部门正常状态 */
    public static final String DEPT_NORMAL = "0";

    /**
     * 用户名长度限制
     */
    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;

    /** 登录名称是否唯一的返回结果码 */
    public final static String USER_NAME_UNIQUE = "0";
    public final static String USER_NAME_NOT_UNIQUE = "1";

    /** 手机号码是否唯一的返回结果 */
    public final static String USER_PHONE_UNIQUE = "0";
    public final static String USER_PHONE_NOT_UNIQUE = "1";

    /** e-mail 是否唯一的返回结果 */
    public final static String USER_EMAIL_UNIQUE = "0";
    public final static String USER_EMAIL_NOT_UNIQUE = "1";

    /** 部门名称是否唯一的返回结果码 */
    public final static String DEPT_NAME_UNIQUE = "0";
    public final static String DEPT_NAME_NOT_UNIQUE = "1";

    /** 角色名称是否唯一的返回结果码 */
    public final static String ROLE_NAME_UNIQUE = "0";
    public final static String ROLE_NAME_NOT_UNIQUE = "1";

    /** 岗位名称是否唯一的返回结果码 */
    public final static String POST_NAME_UNIQUE = "0";
    public final static String POST_NAME_NOT_UNIQUE = "1";

    /** 角色权限是否唯一的返回结果码 */
    public final static String ROLE_KEY_UNIQUE = "0";
    public final static String ROLE_KEY_NOT_UNIQUE = "1";

    /** 岗位编码是否唯一的返回结果码 */
    public final static String POST_CODE_UNIQUE = "0";
    public final static String POST_CODE_NOT_UNIQUE = "1";

    /** 菜单名称是否唯一的返回结果码 */
    public final static String MENU_NAME_UNIQUE = "0";
    public final static String MENU_NAME_NOT_UNIQUE = "1";

    /** 字典类型是否唯一的返回结果码 */
    public final static String DICT_TYPE_UNIQUE = "0";
    public final static String DICT_TYPE_NOT_UNIQUE = "1";

    /** 参数键名是否唯一的返回结果码 */
    public final static String CONFIG_KEY_UNIQUE = "0";
    public final static String CONFIG_KEY_NOT_UNIQUE = "1";

    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 手机号码格式限制
     */
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";

    /**
     * 邮箱格式限制
     */
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";

    /** 1管理员  2 超级管理员
     * 3 普通员工审批状态  4经理级
     * 5 总经理级 9 职能总监
     *  6 总监级  7 主管级
     *  8 特殊普通员工
     *
     * */
    public static final int ADMIN = 1;
    public static final int SUPER_ADMIN = 2;
    public static final int COMMON = 3;
    public static final int MANAGER = 4;
    public static final int GENERAL_MANAGE =5 ;
    public static final int FUNCTION = 9;
    public static final int DIRECTOR = 6;
    public static final int COMPETENT_LEVEL = 7;
    public static final int SPECIAL = 8;

    /**
     *  角色  1
     */
    // 1
    public static final String ROLE_ADMIN = "admin";
    // 3
    public static final String ROLE_COMMON = "common";
    // 4
    public static final String ROLE_MANAGER = "manager";
    // 5
    public static final String ROLE_GENERAL_MANAGE = "generalManager";
    // 9
    public static final String ROLE_FUNCTION = "function";
    // 2
    public static final String ROLE_SUPER_ADMIN = "superAdmin";
    // 6
    public static final String ROLE_DIRECTOR = "director";
    // 8
    public static final String ROLE_SPECIAL = "special";
    // 7
    public static final String ROLE_COMPETENT = "competentLevel";

    /**
     *   抢单任务状态 1未完成 2已完成 3 作废(禁用)过期
     */

      public static final  Integer TASK_FAIL = 1;
      public static final  Integer TASK_OK = 2;
      public static final  Integer TASK_DISABLE = 3;

      /**
       *  用户对这条抢单 的用户状态
       *  审批状态(0.正常状态，1抢到 （未完成）2.审批中（提交中未完成） 3.完成（审批通过），4审批不通过，5.撤销审批
       */
       public static final  Integer USER_STATUS_DISABLE = 1;
       public static final  Integer USER_STATUS_TI_DISABLE = 2;
       public static final  Integer USER_STATUS_SUCCESS = 3;
       public static final  Integer USER_STATUS_FAIL = 4;
       public static final  Integer USER_STATUS_CHEXIAO = 5;

       /**
        *  抢单任务
        *   超过 3天 这条单据失效
        */
       public static final  Integer GRAB_DAY = 3;

       /**
        *  任务类型 1日常任务 2抢单任务 3挑战任务
        */
        public static final  Integer DAY_TASK = 1;
        public static final  Integer GRAB_TASK = 2;
        public static final  Integer CHALLENGE_TASK = 3;

       /**
        *  3.挑战任务 失败审批不通过会扣除原有挑战任务的  1.5倍积分
        */
       public static final  Double CHALLENGE_SCORE = 1.5;

       /**
        *  审批的使用状态
        *      审批状态(0,审批中 1审批通过，2审批不通过，3撤销审批
        */
         public static final Integer APP_DEFULT = 0 ;
         public static final Integer APP_SUCCESS = 1;
         public static final Integer APP_FAIL = 2;
         public static final Integer APP_CHE_XIAO = 3;

         /**  积分申诉状态  */
         public static final String COMPLAINT_FAIL = "1";

          /** 积分申诉拒绝理由 查询条件*/
         public static final String CONFIG_TYPE = "N";
}
