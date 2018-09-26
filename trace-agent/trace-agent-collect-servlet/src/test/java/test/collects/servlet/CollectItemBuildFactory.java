package test.collects.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wpisen.trace.agent.common.util.JaxbUtil;
import com.wpisen.trace.agent.core.AgentItemSource;
import com.wpisen.trace.agent.core.AgentPlugin;
import com.wpisen.trace.agent.core.MethodInfo;
import com.wpisen.trace.agent.core.SrcTemplate;

import javassist.CannotCompileException;
import javassist.NotFoundException;

/**
 * Created by wpisen on 16/10/22.
 */
@SuppressWarnings("restriction")
public class CollectItemBuildFactory extends BasiceTest {
    public static void main(String[] args) throws Exception {
        new CollectItemBuildFactory().buildConfigs();
    }

    // 生成agentItem 并以json 形式保存至文件
    public  void buildConfigs() throws Exception {
        String out_file = System.getProperty("user.dir") + "/agent-collect-servlet/src/main/resources/agentConfig/agent.xml";
        File file = new File(out_file);
        file.getAbsolutePath();
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        List<AgentItemSource> list = new ArrayList<>();
        list.addAll(createServletItems());

        /**
         * 转换成xml
         */
        AgentPlugin plugin = new AgentPlugin();
        plugin.setDescribe("servlet采集器");
        plugin.setAgentItems(list);
        SrcTemplate returnTemplate = new SrcTemplate("returnTemplate", BasiceTest.return_Src_Template);
        SrcTemplate voidTemplate = new SrcTemplate("voidTemplate", BasiceTest.void_src_template);
        plugin.setTemplates(new SrcTemplate[] { returnTemplate, voidTemplate });
        String str = JaxbUtil.convertToXml(plugin);

        System.out.println(str);
        FileOutputStream fileout = new FileOutputStream(file);
        fileout.write(str.getBytes());
        fileout.flush();
        fileout.close();
        System.out.println("agent item 生成成功!" + out_file);
    }

    /***
     * encode by Base64
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static String encodeBase64(byte[] input) throws Exception {
        com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode(input);
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, new Object[] { input });
        return (String) retObj;
    }

    /***
     * decode by Base64
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] decodeBase64(String input) throws Exception {
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
    }

    public  List<AgentItemSource> createServletItems() throws NotFoundException, CannotCompileException, IOException {
        String className = "javax.servlet.http.HttpServlet";

        String handClassName = "com.wpisen.trace.agent.collects.servlet.ServletHandle";
        String method = "service";
        MethodInfo info = new MethodInfo(className, method);
        info.setReturnType(void.class.getName());
        info.setParamTypes(new String[] { "javax.servlet.http.HttpServletRequest", "javax.servlet.http.HttpServletResponse" });
        return loadAgentItems(className, handClassName, "voidTemplate", null, null, info);
    }


}
