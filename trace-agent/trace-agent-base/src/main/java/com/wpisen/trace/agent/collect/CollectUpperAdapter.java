package com.wpisen.trace.agent.collect;

import com.wpisen.trace.agent.common.logger.Logger;
import com.wpisen.trace.agent.common.logger.LoggerFactory;
import com.wpisen.trace.agent.common.util.Assert;

public class CollectUpperAdapter {
    static final Logger logger = LoggerFactory.getLogger(CollectUpperAdapter.class);
    private CollectHandle hand;
    private String methodName;
    private String targetClassName;
    @SuppressWarnings("unused")
	private String handClass;
    private boolean initSuccess = true;

    public CollectUpperAdapter(String handClass, String targetClassName, String methodName) {
        Assert.hasText(handClass);
        try {
            hand = CollectHandleBeanFactory.getBean(Class.forName(handClass));
        } catch (ClassNotFoundException e) {
            logger.error("找不到指定采集器:" + handClass, e);
            this.initSuccess = false;
        }
        this.handClass = handClass;
        this.targetClassName = targetClassName;
        this.methodName = methodName;
    }

    public Event invokerBefore(Object proxy, Object[] params) throws ClassNotFoundException {
        if (!initSuccess)
            return null;

        InParams in = new InParams();
        in.args = params;
        in.methodName = methodName;
        in.className = targetClassName;
        in.put("this", proxy);
        return hand.invokerBefore(null, in);
    }

    public Object invokerAfter(Object proxy, Object[] params, Object result, Event event) throws ClassNotFoundException {
        if (!initSuccess)
            return null;

        CollectHandle hand = CollectHandleBeanFactory.getBean(Class.forName(targetClassName));
        OutResult out = new OutResult();
        out.args = params;
        out.methodName = methodName;
        out.className = targetClassName;
        out.put("this", proxy);
        out.result = result;
        hand.invokerAfter(event, out);
        if (out.result != result)
            return out.result;
        return null;
    }
}
