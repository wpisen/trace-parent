package test.base.util;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.wpisen.trace.agent.common.util.JsonUtils;
import com.wpisen.trace.agent.trace.TraceNode;

import test.BasiceTest;

public class JsonUtilsTest extends BasiceTest {
    @Test
    public void toJsonTest() {
        ArrayList<TraceNode> nodes = newMockTraceNodes(UUID.randomUUID().toString());
        String json = JsonUtils.toJson(nodes, getClass().getClassLoader());
        System.out.println(json);
        Assert.assertNotNull(json);
    }
}
