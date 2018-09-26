package test.base.util;

import org.junit.Test;

import com.wpisen.trace.agent.common.util.JarUtil;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by wpisen on 16/11/5.
 */
public class JarUtilTest {

    @Test
    public void testAddUrl() throws Exception {
        URL url = new URL("jar:file:/Users/wpisen/git/trace-agent/out/trace-agent-bootstrap.jar!/trace-agent-collects.jar");
        URLClassLoader loader = new URLClassLoader(new URL[0]);
        JarUtil.addUrl(url, loader);
        Class<?> c = loader.loadClass("com.wpisen.trace.agent.collects.dubbo.DubboConsumerMonitorHandle");
        System.out.println(c.getName());
    }
}