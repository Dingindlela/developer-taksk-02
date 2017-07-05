/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.serviceImpl;

import com.econetwireless.testbench.service.SourceManager;
import com.econetwireless.testbench.service.SourceStartup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 *
 * @author rise3
 */
@Service
@Slf4j
public class SourceStartupImpl implements SourceStartup{
    
    private ObjectFactory<SourceManager> factory;
    private SourceManager sourceManager;
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    @Override
    public void setServerThread(ObjectFactory<SourceManager> sourceManager) {
        this.factory = sourceManager;
    }

    @Autowired
    @Override
    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }
    
    @Override
    public void startServer(){
        sourceManager = factory.getObject();
        log.info("Starting source manager:"+sourceManager);
        taskExecutor.execute(sourceManager);
    }
}
