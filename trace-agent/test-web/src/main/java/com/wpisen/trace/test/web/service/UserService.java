package com.wpisen.trace.test.web.service;/**
 * Created by Administrator on 2018/5/31.
 */

import com.wpisen.trace.test.web.bean.User;

/**
 * @author wpisen
 *         Created by wpisen on 2018/5/31
 **/
public interface UserService {
    User getUser(String userid, String name);
}
