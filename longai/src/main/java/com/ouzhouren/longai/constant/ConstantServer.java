package com.ouzhouren.longai.constant;

/**
 * Created by BenPC on 2015/8/31.
 */
public class ConstantServer {
    public static String PRE_FIX = "http://";
   // public static String HOSTNAME = "192.168.1.101:8080";
    public static String HOSTNAME = "192.168.1.102:8080";
    public static String CHAT="192.168.1.111";
   // public static String HOSTNAME = "192.168.1.102:8080";
    public static String PATCH_LOGIN = "/longai/user/login";
    public static String PATCHP_REGISTER = "/longai/user/register";

    //todo
    public static String PATCH_SEND_MOMENT="/longai/moment/addMoment";
    public static String PATCH_DELETE_MOMENT="/longai/moment/deleteMoment";
    public static String PATCH_GET_MOMENT="/longai/moment/getAllMomentVo";
    public static String PATCH_GET_EVENT="/longai/event/getEvent";
    public static String PATCH_ENROLL="/longai/enroll/enrollEvent";
    public static String PATCH_GET_NEWS="/longai/new/newsList";
    public static String PATCH_GET_NEWS_COMMENT="/longai/newscomment/commentsByNewsId";
    public static String PATCH_SEND_NEWS_COMMENT = "/longai/newscomment/commentNews";
    public static String PATCH_GET_NEWS_LIKE="/longai/newslike/likesByNewsId";
    public static String PATCH_SEND_NEWS_LIKE = "/longai/newslike/newsLike";
    public static String PATCH_UPDATE_USER_INFO="/longai/user/insertDetail";
    public static String PATCH_UPLOAD_PHOTO="/longai/picture/picUpload";
    public static String PATCH_UPLOAD_PROFILE_PIC="/longai/user/userIcon";
    public static String PATCH_GET_ALBUM="/longai/picture/getTotalPic";
    public static String PATCH_GET_MOMENT_COMMENT = "/longai/momentComment/getComment";
    public static String PATCH_SEND_MOMENT_COMMENT="/longai/momentComment/addMomentComment";
    public static String PATCH_DELETE_MOMENT_COMMENT="/longai/momentComment/deleteMomentComment";
    public static String PATCH_GET_MOMENT_LIKE="/longai/momentLike/getLikes";
    public static String PATCH_SEND_MOMENTLIKE="/longai/momentLike/addMomentlike";
    public static String PATCH_DELETE_MOMENT_LIKE="/longai/momentLike/deleteMomentlike";
    public static String PATCH_GET_USERS_BY_GENS= "/longai/user/findLover";
    public static String PATCH_DELETE_NEWS_LIKE="/longai/newslike/deleteNewsLike";
    public static String PRFIX_PROFILE_PIC=PRE_FIX+HOSTNAME+"/longai/img/userIcon/";
    public static String PRFIX_EVENT_PIC=PRE_FIX+HOSTNAME+"/longai/img/eventImage/";

}
