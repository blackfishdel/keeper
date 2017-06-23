package com.del.keeper.core.constant;

/**
 * 常量池
 * 
 * @author xie
 *
 */
public class Constant {
    /**
     * 设备类型
     */
    public static final String DEVICE_TYPE = "X-Client-Type";
    /**
     * 设备唯一ID
     */
    public static final String DEVICE_ID = "X-Client";
    /**
     * session:用户
     */
    public static final String SN_UR = "session_user";
    /**
     * session:平台账户
     */
    public static final String SN_UA = "session_userauth";
    /**
     * 账户 redis 失效时间 2小时
     */
    public static final Long PWD_TIME = 7200L;
}
