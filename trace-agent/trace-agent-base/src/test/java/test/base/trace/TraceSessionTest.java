package test.base.trace;

import org.junit.Test;

import com.wpisen.trace.agent.trace.TraceNode;
import com.wpisen.trace.agent.trace.TraceRequest;
import com.wpisen.trace.agent.trace.TraceSession;

import test.BasiceTest;

import java.util.UUID;

/**
 * Created by wpisen on 16/10/21.
 */
public class TraceSessionTest extends BasiceTest{

    @Test
    public void testAddNode() throws Exception {
        TraceRequest request=new TraceRequest();
        request.setTraceId(UUID.randomUUID().toString());
        request.setParentRpcId("0.1");
        TraceSession session = context.openTrace(request);
        String traceId=TraceSession.createTraceId();
        for(TraceNode node:newMockTraceNodes(traceId)){
            session.addNode(node);
        }
        session.addTraceFrom(newMockTraceFrom(traceId));
        // 延后1秒结束,以有足够时间进行上传
        Thread.sleep(1000);
    }

    @Test
    public void testAddTraceFrom() throws Exception {

    }
}