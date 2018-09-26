package com.wpisen.trace.agent.transfer;

import com.wpisen.trace.agent.collect.CollectHandleProxy;
import com.wpisen.trace.agent.common.logger.Logger;
import com.wpisen.trace.agent.common.logger.LoggerFactory;
import com.wpisen.trace.agent.trace.TraceContext;

/**
 * 获取队列线程
 * 
 * @since 0.1.0
 */
public class TransferTask extends Thread {
    private TraceContext context;
    private final Logger logger;

    public TransferTask(TraceContext context, String name) {
        this.setName(name);
        this.context = context;
        logger = LoggerFactory.getLogger(CollectHandleProxy.class);
    }
    
    @Override
    public void run() {
        for (;;) {
            try {
                context.uploadNode(20);
            } catch (Throwable e) {
                logger.error("upload Task error", e);
            }
        }
    }
}