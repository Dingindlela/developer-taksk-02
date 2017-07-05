/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.serviceImpl;

/**
 *
 * @author rise3
 */
import com.econetwireless.testbench.service.ConfigService;
import com.econetwireless.testbench.service.MsgSource;
import com.econetwireless.testbench.service.SinkService;
import com.econetwireless.testbench.service.SourceManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SourceManagerImpl implements SourceManager {

    private ObjectFactory<MsgSource> sourceFactory;
    private ThreadPoolTaskExecutor taskExecutor;
    private final List<MsgSource> sourceList=new ArrayList<>();
    private ConfigService configService;
    private SinkService sinkService;

       @Autowired
    @Override
    public void setSinkService(SinkService sinkService) {
        this.sinkService = sinkService;
    }
    @Autowired
    @Override
    public void setSourceFactory(ObjectFactory<MsgSource> sourceFactory) {
        this.sourceFactory = sourceFactory;
    }

    @Autowired
    @Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
    @Autowired
    @Override
    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(configService.getInitwait());
            configService.setRun(true);
            for(String source:configService.getConfig().getConfig().keySet()){
                MsgSource msgSource=  sourceFactory.getObject();
                msgSource.setValues(source, configService.getConfig().getConfig().get(source));
                sourceList.add(msgSource);
                taskExecutor.execute(msgSource);
            }        
            Thread.sleep(configService.getTestperiod());
            configService.setRun(false);
            while (sinkService!=null &&sinkService.getLastSend()!=null&& new Date().getTime()-sinkService.getLastSend().getTime()<2000) {
            Thread.sleep(configService.getInitwait());
            }
            log.info("Msg Sink Complete:");
            log.info(sinkService.summary());
            sourceList.stream().forEach((msgSource) -> {
                log.info(msgSource.getSummary());
            });
        } catch (InterruptedException ex) {
            log.error("Thread Error:", ex);
        }
    }
}
