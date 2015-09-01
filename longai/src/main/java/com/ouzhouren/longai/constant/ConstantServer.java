package com.ouzhouren.longai.constant;

/**
 * Created by BenPC on 2015/8/31.
 */
public class ConstantServer {
    public static String PRE_FIX = "http://";
    //public static String HOSTNAME = "tom:8080";
    public static String HOSTNAME = "192.168.1.103:8080";
    public static String PATCH_LOGIN = "/longai/user/login";
    public static String PATCHP_REGISTER = "/longai/user/register";
    //todo
    public static String PATCH_SEND_MOMENT="/longai/moment/addMoment";
    public static String PATCH_DELETE_MOMENT="/longai/moment/deleteMoment";
    public static String PATCH_GET_MOMENT="/longai/moment/getAllMomentVo";


    public static String PATCH_INSERT_DETAIL = "/longai/user/insertDetail";
    public static String PATCH_USERICON = "/longai/user/userIcon";
    public static String PATCH_GET_DETAIL = "/longai/user/getDetail";
    public static String PATCH_GET_PICS = "/longai/picture/getTotalPic";
    public static String PATCH_PIC_UPLOAD = "/longai/picture/picUpload";
    //todo
    public static String PATCH_EVENTS = "/longai/picture/picUpload";
    public static String PATCH_EVENT_DETAIL = "/longai/picture/picUpload";
}
