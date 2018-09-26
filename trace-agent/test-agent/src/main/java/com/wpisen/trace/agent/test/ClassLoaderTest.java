package com.wpisen.trace.agent.test;/**
 * Created by Administrator on 2018/6/19.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wpisen
 *         Created by wpisen on 2018/6/19
 **/
public class ClassLoaderTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String collectServletPath = "file:///G:\\git\\trace-agent\\" +
                "agent-collect-servlet\\target\\trace-agent-collect-servlet-1.0.0.jar";
        String servletPath = "file:///C:\\Users\\Administrator\\.m2\\repository" +
                "\\javax\\servlet\\javax.servlet-api\\3.1.0\\javax.servlet-api-3.1.0.jar";
        URLClassLoader parent = new URLClassLoader(new URL[]{new URL(collectServletPath)});
        URLClassLoader child = new URLClassLoader(new URL[]{new URL(collectServletPath),
                new URL(servletPath)}, parent);
        Class<?> clas = child.loadClass("com.wpisen.trace.agent.collects.servlet.ServletResponseProxy");
        clas.newInstance();
    }
}
