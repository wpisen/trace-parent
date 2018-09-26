package com.wpisen.trace.server.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wpisen.trace.server.dao.ClientSessionMapper;
import com.wpisen.trace.server.dao.entity.ClientSession;
import com.wpisen.trace.server.service.ProjectSystemManage;
import com.wpisen.trace.server.service.entity.ClientSessionVo;

import java.util.*;

/**
 * Created by wpisen on 17/6/26.
 */
@Service
public class ProjectSystemManageImpl implements ProjectSystemManage {

        @Autowired
        private ClientSessionMapper clientSessionMapper;
        @Value("${session.latestSecond:60}")
        private int latestSecond;           // 会话保持时间(秒)

        @Override
        public List<ClientSessionVo> getActiveSessions(Integer proId) {
            Assert.notNull(proId,"参数'proId'不能为空");
            List<ClientSessionVo> result=new ArrayList<>();
            Date lastModified=new Date(System.currentTimeMillis()-(60*1000));
            List<ClientSession> sessions = clientSessionMapper.getActiveSession(lastModified, proId);


            final List<ClientSession> repetitionList=new ArrayList<>();
            repetitionList.addAll(sessions);
            /*
             *  通过排序来去重.
             */
            Comparator<? super ClientSession> comparator=new Comparator<ClientSession>(){
                @Override
                public int compare(ClientSession o1, ClientSession o2) {
                    // 如果应用路径相同且mac地址相同,则判断为同一应用
                    if (o1.getAppPath().equals(o2.getAppPath()) &&
                            o1.getClientMacAddress().equals(o2.getClientMacAddress())) {
                        if(o1.getLastUpdateTime().getTime()>o2.getLastUpdateTime().getTime()){
                            repetitionList.remove(o2);
                        }else{
                            repetitionList.remove(o1);
                        }
                    }
                    return 0;
                }
            };
            Collections.sort(sessions,comparator);

            //TODO 去重之后的实体对象转换成Vo
            for (ClientSession clientSession : repetitionList) {
                ClientSessionVo vo=new ClientSessionVo();
                BeanUtils.copyProperties(clientSession,vo);

                //
                if(System.currentTimeMillis()- vo.getLastUpdateTime().getTime()>30000){
                    vo.setStatusText("离线");
                }else{
                    vo.setStatusText("在线");
                }
                // 计算最后跃时间 文本显示
                vo.setLastActiveTimeText(fromToDay(vo.getLastUpdateTime()));
            }
            return result;
        }

    private String fromToDay(Date date) {
        long now = new Date().getTime() / 1000;
        if (now < 60) {
            return String.format("%s秒前", now);
        } else if (now < 3600) {
            return String.format("%s分钟前", now / 60);
        } else if (now < 24 * 3600) {
            return String.format("%s小时前", now / 3600);
        } else {
            return String.format("%天前", now / 24 * 3600);
        }
    }

}
