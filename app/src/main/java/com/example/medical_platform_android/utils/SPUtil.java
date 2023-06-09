package com.example.medical_platform_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("sp_info", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("sp_info",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void removeString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("sp_info",Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //    判断有没有用户登录
    public static boolean loginStar(Context context){
        if(getString(context,"userid")!=null&&getString(context,"userid")!=null){
            return false;
        }else{
            return true;
        }


    }

}
