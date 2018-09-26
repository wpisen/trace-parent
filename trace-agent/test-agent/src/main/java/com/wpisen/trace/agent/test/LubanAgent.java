package com.wpisen.trace.agent.test;/**
 * Created by Administrator on 2018/6/28.
 */

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author wpisen
 *         Created by wpisen on 2018/6/28
 **/
public class LubanAgent {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("premain：" + args);
        // 所有的Class在装载之前都会调用这个方法
        //字节码层面的aop
        System.out.println("inster byte code");
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (!"com/wpisen/trace/agent/test/LubanServiceImpl".equals(className)) {
                    return null;
                }
                // javassist 工具 改造
                try {
                    ClassPool pool = new ClassPool();
                    pool.insertClassPath(new LoaderClassPath(loader));
                    CtClass ctclass = pool.get("com.wpisen.trace.agent.test.LubanServiceImpl");
                    CtMethod method = ctclass.getDeclaredMethod("hello");
                    method.insertBefore(" System.out.println(System.currentTimeMillis());");
                    method.insertBefore("long begin = System.currentTimeMillis();");
                    method.insertAfter(" long end = System.currentTimeMillis();\n" +
                            "        System.out.println(end - begin);");
                    return ctclass.toBytecode();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });
    }

}
