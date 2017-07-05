/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.service;

/**
 *
 * @author rise3
 */
import org.springframework.web.client.RestTemplate;


public interface MsgSource extends Runnable {
    public String getSummary();
    public void setConfigService(ConfigService configService);
    public void setRestTemplate(RestTemplate restTemplate);
    public void setValues(String source,int rate);
}
