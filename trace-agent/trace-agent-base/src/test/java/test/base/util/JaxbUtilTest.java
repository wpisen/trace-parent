package test.base.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wpisen.trace.agent.common.util.JaxbUtil;
import com.wpisen.trace.agent.core.AgentItemSource;
import com.wpisen.trace.agent.core.AgentPlugin;
import com.wpisen.trace.agent.core.MethodInfo;
import com.wpisen.trace.agent.core.SrcTemplate;
import com.wpisen.trace.agent.core.AgentFinal.AgentWay;

/**
 * Created by wpisen on 16/11/4.
 */
public class JaxbUtilTest {
    static String  tempValue=
            "\n{\n" +
            "com.wpisen.trace.agent.collect.InParams _param=new com.wpisen.trace.agent.collect.InParams();\n" +
            "        com.wpisen.trace.agent.collect.OutResult _result=new com.wpisen.trace.agent.collect.OutResult();\n" +
            "        _param.args=$args;\n" +
            "        _result.args=$args;\n" +
            "        _param.className=\"${target.className}\";\n" +
            "        _param.methodName=\"${target.methodName}\";\n" +
            "        _result.className=\"${target.className}\";\n" +
            "        _result.methodName=\"${target.methodName}\";\n" +
            "        _param.paramTypes=\"${target.paramTypes}\".split(\",\");\n" +
            "        _param.paramTypes=\"${target.paramTypes}\".split(\",\");\n" +
            "${beforeSrc}\n" +
            "        com.wpisen.trace.agent.collect.CollectHandle handle=com.wpisen.trace.agent.collect.CollectHandleBeanFactory.getBean(${handle.className}.class);\n" +
            "        com.wpisen.trace.agent.collect.Event event = handle.invokerBefore(null,_param);\n" +
            "        try {\n" +
            "            _result.result=($w)${target.methodName}$agent($$);\n" +
            "        } catch (Throwable e) {\n" +
            "            _result.error=e;\n" +
            "            throw e;\n" +
            "        }finally{\n" +
            "${afterSrc}\n" +
            "            handle.invokerAfter(event, _result);\n" +
            "        }\n" +
            "        return ($r) _result.result;\n" +
            "}\n";
    @Test
    public void testConvertToXml() throws Exception {
        AgentPlugin plugin=new AgentPlugin();
        plugin.setDescribe("测试插件");

        plugin.setTemplates(new SrcTemplate[]{new SrcTemplate("common",tempValue)});
        List<AgentItemSource> list=new ArrayList<>();
        plugin.setAgentItems(list);
        AgentItemSource obj = new AgentItemSource();
        obj.setAfterSrc("sysout_after_src");
        obj.setTargetClassName("javax.http.servlet");
        MethodInfo methodinfo = new MethodInfo();
        methodinfo.setReturnType("void");
        methodinfo.setMethodName("service");
        methodinfo.setParamTypes(new String[]{"httprequest", "httpresponse"});
        obj.setMethodInfo(methodinfo);
        obj.setSrcTemplate("common");

        obj.setWay(AgentWay.trace);
        list.add(obj);
        String xml = JaxbUtil.convertToXml(plugin);
        System.out.println(xml);
    }
}