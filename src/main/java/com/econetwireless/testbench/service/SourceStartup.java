/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.service;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author rise3
 */
public interface SourceStartup {
    
    public void setServerThread(ObjectFactory<SourceManager> sourceManager);
    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) ;
    public void startServer();
}
