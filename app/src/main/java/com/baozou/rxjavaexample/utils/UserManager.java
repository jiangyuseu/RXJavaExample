package com.baozou.rxjavaexample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.baozou.rxjavaexample.model.UserBean;

/**
 * Created by jiangyu on 2016/5/16.
 * 用户管理中心，包括：保存用户，判断用户是否登录，记载用户信息
 */
public class UserManager {

    private Context mContext;
    private SharedPreferences preferences;
    private String PREFERENCE_KEY = "aixue_key";
    private static UserManager sInstance;

    //登录用户的基本信息
    public static final String USER_ACCESSS_TOKEN = "aixue_accessToken";
    public static final String USER_AVATAR = "aixue_avatar";
    public static final String USER_NAME = "aixue_name";
    public static final String USER_ID = "aixue_user_id";

    private UserManager(Context pContext) {
        this.mContext = pContext.getApplicationContext();
        this.preferences = this.mContext.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    public static UserManager getInstance(Context pContext) {
        if (UserManager.sInstance == null) {
            UserManager.sInstance = new UserManager(pContext);
        }
        return UserManager.sInstance;
    }

    public synchronized void saveUser(UserBean pUser) {
        if (pUser != null) {
            SharedPreferences.Editor editor = preferences.edit();
            if (!TextUtils.isEmpty(pUser.getToken()) && !TextUtils.isEmpty(pUser.getUid())) {
                editor.putString(UserManager.USER_ACCESSS_TOKEN, pUser.getToken());
                editor.putString(UserManager.USER_ID, pUser.getUid());
            }
            editor.putString(UserManager.USER_AVATAR, pUser.getAvatar());
            editor.putString(UserManager.USER_NAME, pUser.getName());
            editor.commit();
        }
    }

    public synchronized UserBean loadUser() {
        String accessToken = preferences.getString(UserManager.USER_ACCESSS_TOKEN, "");
        String avatar = preferences.getString(UserManager.USER_AVATAR, "");
        String userId = preferences.getString(UserManager.USER_ID, "");
        String userName = preferences.getString(UserManager.USER_NAME, "");
        UserBean tempUser = null;
        if (!TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(userId)) {
            tempUser = new UserBean();
            tempUser.setToken(accessToken);
            tempUser.setAvatar(avatar);
            tempUser.setName(userName);
            tempUser.setUid(userId);
        }
        return tempUser;
    }

    /**
     * 判断当前用户是否已经登录
     *
     * @return
     */
    public synchronized boolean isUserLogin() {
        String accessToken = preferences.getString(UserManager.USER_ACCESSS_TOKEN, "");
        String userId = preferences.getString(UserManager.USER_ID, "");
        if (!TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(userId)) {
            return true;
        } else {
            return false;
        }
    }

}
