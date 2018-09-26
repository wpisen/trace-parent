package com.wpisen.trace.agent.bootstrap;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Properties;

/**
 * Created by wpisen on 16/11/2.
 */
public interface AgentApplication {
    public void init(TraceSessionInfo session, Properties properties, String[] collectPaths, Instrumentation inst) throws Exception;
    public ClassFileTransformer getTransformer();
}
