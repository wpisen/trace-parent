package test.base.util;


import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

import com.wpisen.trace.agent.common.util.ReflectionUtils;

/**
 * Created by wpisen on 16/10/10.
 */
public class UrlJarTest {
    @SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
        byte[] bytes=new byte[200];
        URL url = new URL("jar:file:/Users/wpisen/git/trace-agent/out/trace-agent-bootstrap.jar!/trace-agent-base.jar");
        Path path = Files.createTempFile("jar_cache", null, new FileAttribute[0]);
        Files.copy(url.openStream(), path, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
        url= path.toUri().toURL();
        path.toFile().deleteOnExit();
        URLClassLoader ucl = new URLClassLoader(new URL[]{}, null);
        Method method = ReflectionUtils.findMethod(URLClassLoader.class, "addURL", URL.class);
        method.setAccessible(true);
        ReflectionUtils.invokeMethod(method, ucl, url);

        Class<?> cl =  ucl.loadClass("com.wpisen.trace.agent.trace.AppInfo");
        System.out.println(cl.getName());

        System.getProperty("temp.dir");
    }


}

