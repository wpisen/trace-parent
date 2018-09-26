package com.wpisen.trace.agent.transfer;

import java.util.Collection;

import com.wpisen.trace.agent.trace.TraceFrom;
import com.wpisen.trace.agent.trace.TraceNode;

/**
 * 上传器接口
 * 
 * @since 0.1.0
 */
public interface UploadService {
    public void uploadByDefault(Collection<TraceNode> nodes, Collection<TraceFrom> infos);

    public void uploadByHttp(Collection<TraceNode> nodes, Collection<TraceFrom> infos, String url);

    public void uploadByDubbo(Collection<TraceNode> nodes, Collection<TraceFrom> infos);
    
    public void uploadToLogfile(Collection<TraceNode> nodes, Collection<TraceFrom> infos);

    void uploadToRedis(Collection<TraceNode> nodes, Collection<TraceFrom> infos);

    void uploadToMysql(Collection<TraceNode> nodes, Collection<TraceFrom> infos);

}
