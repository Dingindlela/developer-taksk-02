/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.serviceImpl;

import com.econetwireless.testbench.model.Config;
import com.econetwireless.testbench.service.ConfigService;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 *
 * @author rise2
 */
@Service
public class ConfigServiceImpl implements ConfigService{

    @Value("${exchange.url}")
    private String url;
    @Value("${exchange.testperiod}")
    private int testperiod;
    @Value("${exchange.initwait}")
    private int initwait;
    private volatile boolean run=true;
    private Config config;
    private String accept=MediaType.APPLICATION_XML_VALUE;
    private final Random random=new Random(System.currentTimeMillis());

    @Override
    public int getInitwait() {
        return initwait;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getTestperiod() {
        return testperiod;
    }
    
    @Override
    public long randomRate(int rate){
        long pace=1000/rate;
        double ret = pace+random.nextGaussian()*Math.sqrt(pace);
        return (long) ret;
    }

    @Override
    public boolean isRun() {
        return run;
    }

    @Override
    public void setRun(boolean run) {
        this.run = run;
    }

    @Override
    public String getAccept() {
        return accept;
    }

    @Override
    public void setAccept(String accept) {
        if(accept.contains(" ")||accept.contains(",")){
            return;
        }
        this.accept = accept;
    }

    public Config getConfig() {
        if(config==null){
            return resetConfig();
        }
        return config;
    }
    @Override
    public Config resetConfig() {
        config=new Config();
        Map<String,Integer> configs=new HashMap<>();
        int count=7+(int)(random.nextDouble()*10);
        for(int i=0;i<count;i++){
            configs.put("source"+i, 7+(int)(random.nextDouble()*20));
        }
        config.setConfig(configs);
        config.setSinkRate((int)(configs.values().stream().mapToInt(Integer::intValue).sum()*0.6));
        return config;
    }
}
