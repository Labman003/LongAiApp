package com.ouzhouren.longai.constant;

/**
 * Created by BenPC on 2015/8/31.
 */
public class ConstantServer {
    public static String PRE_FIX = "http://";
   // public static String HOSTNAME = "192.168.1.101:8080";
    public static String HOSTNAME = "192.168.1.103:8080";
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
    public static String PATCH_UPLOAD_PROFILE_PIC="/longai/userIcon";
    public static String PATCH_GET_ALBUM="/longai/picture/getTotalPic";
}
