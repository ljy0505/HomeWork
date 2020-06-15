package com.wsy.everylesson.net.api;

public class URLConstants {

    public static String BASE_URL = "https://www.seetao.com/";


    //推荐列表
    public static String RECOMMEND_LIST = "app/v_1_3/article/recommendlist";
    //栏目列表
    public static String COLUM_LIST = "api/column/columnlist";
    //  视频列表
    public static String VEDIO_LIST = "app/v_1_3/article/videolist";


    //    手机验证码登录接口
    public static String LOGIN = "api/user/smslogin";
    //    获取验证码接口   /发送短信验证码
    public static String SENDVERIFIED = "api/sms/sendsms";
    //    验证验证码是否正确
    public static String CHECKSMSCODE = "api/sms/checksmscode";
    //    用户注册
    public static String USERREGISTER = "api/user/register";
    //    手机号密码登录
    public static String PHONEPAWORD_LOGIN = "api/user/login";
    //    忘记密码
    public static String FORGET_PW = "app/v_1_1/user/savepassword";
    //文章关联列表
    public static String ARTICLE_Related_LIST = "api/article/articleaccess";
    //    收藏取消收藏接口
    public static String SETCOLLECT = "api/article/usercollect";
    //    获取文章评论列表
    public static String COMMENTLIST = "api/comment_reply/commentlist";
}
