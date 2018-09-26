package test.base.core;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.wpisen.trace.agent.bootstrap.TraceSessionInfo;
import com.wpisen.trace.agent.common.util.Assert;
import com.wpisen.trace.agent.core.AgentFinal;
import com.wpisen.trace.agent.core.DefaultApplication;

/**
 * Created by wpisen on 16/11/2.
 */
public class DefaultApplicationTest implements AgentFinal {

    private TraceSessionInfo session;

    @Before
    public void setUp() throws Exception {
        session = new TraceSessionInfo();
    }

    @Test
    public void testInit() throws Exception {
        DefaultApplication boot = new DefaultApplication();
        Properties pro = new Properties();
        pro.setProperty(OPEN, "true");
        boot.init(session, pro, null, null);
    }

    @Test
    public void testDevcollectTest() throws Exception {
        DefaultApplication boot = new DefaultApplication();
        Properties pro = new Properties();
        pro.setProperty(OPEN, "true");
        String[] collects = { "F:\\git\\trace-agent\\agent-collect-servlet\\target\\classes\\",
                "F:\\git\\trace-agent\\agent-collects\\target\\classes\\" };
        boot.init(session, pro, collects, null);
        byte[] result = boot.transform(getClass().getClassLoader(), "com.mysql.jdbc.NonRegisteringDriver", null, null, null);
        Assert.notNull(result);
    }

    @Test
    public void testTransform() throws Exception {
        DefaultApplication boot = new DefaultApplication();
        Properties pro = new Properties();
        pro.setProperty(OPEN, "true");
        boot.init(session, pro, null, null);
        boot.transform(getClass().getClassLoader(), "com.mysql.jdbc.NonRegisteringDriver", null, null, null);
    }

    public static void main(String[] args) {
        DefaultApplicationTest.class.getClassLoader().getResource("com/mysql/jdbc/Driver.class");
    }
}