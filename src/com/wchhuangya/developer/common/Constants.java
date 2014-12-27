/**
 * Copyright @2014 Obsessed - Studio gsww All rights reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何* 渠道使用、修改源代码.
 * 日期 2014-8-25 上午9:06:37
 */
package com.wchhuangya.developer.common;

/**
 * <p>
 * 常量类
 * </p>
 * @company WSuperman
 * @project developer
 * @author wchhuangya
 * @date 2014-8-25 上午9:06:37	
 * @class com.wchhuangya.developer.common.Constants
 *
 */
public class Constants {
    
    /*******************全局开始******************/
	/** 日志标记 */
    public static final String LOG_TAG = "developer";
    /** TODO 日志打印开关 发布时把值改为false */
    public static final boolean LOG_PRINT_SWITCH = true;
    /*******************全局结束******************/

	/*******************training开始******************/
	/** training-fragment里用到的标题 */
	public static final String[] Headlines = {
        "夜的第七章",
        "以父之名"
    };
	/** training-fragment里用到的内容 */
    public static final String[] Articles = {
        "夜的第七章\n\n一九八三年小巷，十二月晴朗，夜的第七章，打字机继续推向接近事实的那下一行。石楠烟斗的雾，飘向枯萎的树，沉默地对我哭诉。贝克街旁的圆形广场，盔甲骑士背上，燕尾花的徽章，微亮。无人马车声响，深夜的拜访，邪恶在维多利亚的月光下血色的开场。消失的手枪，焦黑的手杖，溶化的蜡像谁不在场。珠宝箱上，符号的假象，矛盾通往他堆砌的死巷，证据被完美埋葬，那嘲弄苏格兰广场的嘴角上扬……",
        "以父之名\n\n微凉的晨露，沾湿黑礼服，石板路有雾，父在低诉。无奈的觉悟，只能更残酷，一切都为了，通往圣堂的路。谁不想停住，淹没了意图，谁轻柔渡步，停住。还来不急哭穿过的子弹就带走温度。我们每个人都有罪，犯着不同的罪，我能决定谁对，谁又该要沉睡。争论不能解决，在永无止境的夜，关掉我的嘴，唯一的恩惠……"
    };
    /*******************training结束******************/
    
    /*******************officalsample 开始******************/
    public static final String SPINNER_FILE_NAME = "SpinnerPrefs";
    /*******************officalsample 结束******************/
    
    /*******************msgpush开始******************/
    /** 记录消息推送设置的sharedpreference文件名称 */
    public static final String MSG_PUSH_SETTINGS = "MSG_PUSH_SETTINGS";
    /** 记录百度消息推送注册是否成功 */
    public static final String BAIDU_REGISTER_SUCCESS = "BAIDU_REGISTER_SUCCESS";
    /*******************msgpush结束******************/
}
