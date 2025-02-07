package com.zxb.backEnd.utils;

import com.zxb.backEnd.pojos.User;

public class UserContext {
    // 创建一个ThreadLocal存储线程用户
    static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前线程的用户
     *
     * @return 返回用户
     */
    public static User getUser() {
        return threadLocal.get();
    }

    /**
     * 设置当前线程是用户
     *
     * @param user 用户
     */
    public static void setUser(User user) {
        threadLocal.set(user);
    }

    /**
     * 清除当前线程用户
     */
    public static void clearUser() {
        threadLocal.remove();
    }

}
