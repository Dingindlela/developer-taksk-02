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
import com.econetwireless.testbench.model.Msg;
import com.econetwireless.testbench.service.ConfigService;
import com.econetwireless.testbench.service.MsgSource;
import java.util.Arrays;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@Scope("prototype")
@Slf4j
public class MsgSourceImpl implements MsgSource {

    private String source = "";
    private int rate = 1;
    private int total = 0;
    private ConfigService configService;
    private RestTemplate restTemplate;

    @Override
    public String getSummary() {
        return "From: " + source + " total:" + total;
    }

    @Autowired
    @Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Autowired
    @Override
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void setValues(String source, int rate) {
        this.rate = rate;
        this.source = source;
    }

    @Override
    public void run() {
        while (true) {
            wait_rate();
            if (!configService.isRun()) {
                return;
            }
            sendMsg();
            total++;
        }
    }

    private void wait_rate() {
        try {
            Thread.sleep(configService.randomRate(rate));
        } catch (InterruptedException ex) {
            log.error("Thread Error", ex);
        }
    }

    private void sendMsg() {
        Msg msg = new Msg(null, source, "" + total + " " + new Date(), source + "_" + total);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.parseMediaType(configService.getAccept()));
        try {
            ResponseEntity<Msg> response = restTemplate.postForEntity(configService.getUrl(), new HttpEntity<>(msg, headers), Msg.class);
            //log.info("Source msg:" + response.getStatusCodeValue());
        } catch (ResourceAccessException ex) {
            log.info("Source Error:" + ex.getMessage());
        }
    }
}
