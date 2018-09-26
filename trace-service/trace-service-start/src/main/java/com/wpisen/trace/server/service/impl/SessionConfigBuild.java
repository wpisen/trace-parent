package com.wpisen.trace.server.service.impl;

import java.util.Properties;

/**
 * Created by wpisen on 17/3/25.
 */
public class SessionConfigBuild {
    public static final String PRO_APPIDS="pro.appIds";
    public static final String APP_NAME="appName";
    public static final String APP_NAMESPACE="namespace";
    public static final String APP_STACK_INCLUDES="stack.includes";
    public static final String APP_PREFIX="app.";
    public static final String SYS_PREFIX="sys.";
    public static final String PRO_PREFIX="pro.";

    Properties properties=new Properties();

    public SessionConfigBuild setAppIds(Integer[] appIds){
        String value="";
        for (Integer appId : appIds) {
            value+=",";
            value+=appId;
        }
        if (appIds.length > 0)
            value = value.substring(1);
        properties.put(PRO_APPIDS,value);
        return this;
    }
    public SessionConfigBuild setAppName(Integer appId, String appName){
        properties.put(APP_PREFIX+appId+"."+APP_NAME,appName);
        return this;
    }
    public SessionConfigBuild setAppNamespace(Integer appId, String namespace){
        properties.put(APP_PREFIX+appId+"."+APP_NAMESPACE,namespace);
        return this;
    }
    public SessionConfigBuild setAppStackIncludes(Integer appId, String includes){
        properties.put(APP_PREFIX+appId+"."+APP_STACK_INCLUDES,includes);
        return this;
    }

    public SessionConfigBuild setAppConfig(Integer appId,String key,String value){
        properties.put(APP_PREFIX+appId+"."+key,value);
        return this;
    }

    public SessionConfigBuild setProConfig(String key,String value){
        properties.put(PRO_PREFIX+key,value);
        return this;
    }
    public SessionConfigBuild setSysConfig(String key,String value){
        properties.put(SYS_PREFIX+key,value);
        return this;
    }

    public Properties toProperties() {
        return properties;
    }
}
