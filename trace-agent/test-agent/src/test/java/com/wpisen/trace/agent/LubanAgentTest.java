package com.wpisen.trace.agent;/**
 * Created by Administrator on 2018/6/28.
 */

import com.wpisen.trace.agent.test.LubanServiceImpl;

/**
 * @author wpisen
 *         Created by wpisen on 2018/6/28
 **/
public class LubanAgentTest {
    public static void main(String[] args) {
        System.out.println("main");
        new LubanServiceImpl().hello("", "");
    }
}
