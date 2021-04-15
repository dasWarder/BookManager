package com.babichev.bookmanager.util;

public class SecurityUtil {

    private static Integer id = 2;

    public SecurityUtil() {
    }

    public static Integer getAuthUserId() {
        return id;
    }

    public static void setAuthUser(int id) {
        SecurityUtil.id = id;
    }
}
