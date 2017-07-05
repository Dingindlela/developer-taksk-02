/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.service;

/**
 *
 * @author rise2
 */
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public interface SourceManager extends Runnable{

    public void setSinkService(SinkService sinkService);
    public void setSourceFactory(ObjectFactory<MsgSource> sourceFactory) ;
    public void setConfigService(ConfigService configService) ;
    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) ;
}
