package com.wpisen.trace.agent.test;/**
 * Created by Administrator on 2018/6/19.
 */

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * @author wpisen
 *         Created by wpisen on 2018/6/19
 **/
public class ClassByteUtil {

    public static void main(String[] args) throws IOException {
        String path = "/" + LubanServiceImpl.class.getName().replaceAll("[.]", "/") + ".class";
        InputStream stream = ClassByteUtil.class.getResourceAsStream(path);
        // 读取 ClASS
        ClassReader reader = new ClassReader(stream);
        // 访问者模式
        reader.accept(new TraceClassVisitor(new PrintWriter(System.out)), ClassReader.SKIP_FRAMES);
    }
}
