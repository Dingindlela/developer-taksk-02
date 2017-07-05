/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.serviceImpl;

import com.econetwireless.testbench.model.Msg;
import com.econetwireless.testbench.service.ConfigService;
import com.econetwireless.testbench.service.SinkService;
import java.nio.file.Files;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rise2
 */
@Service
@Slf4j
public class SinkServiceImpl implements SinkService{
    private int ignored=0;
    private int processed=0;
    private ConfigService configService;
    private Date startTime=new Date();
    private int count=0;
    private int target=0;
    private Date lastSend;
@Autowired
@Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public Date getLastSend() {
        return lastSend;
    }
    
    @Override
    public void process(Msg msg) {
        preProcess();
        String logmsg="";
        //log.info("count:"+count+"\ttarget:"+target);
        if(++count>target){
            logmsg=logMsg(msg)+" --:ignored";
            ignored++;
            log.info(logmsg);
            return;
        }
            logmsg=logMsg(msg)+" --:accepted";
            processed++;
            log.info(logmsg);
    }
    private String logMsg(Msg msg){
        return "Msg:: id="+msg.getId()+" payload:"+msg.getPayload()+" source:"+msg.getSource();
        //return  String.format("Msg:: id={} payload:{} source:{}",msg.getId(),msg.getPayload(),msg.getSource());
    }
    private void preProcess(){
        lastSend=new Date();
        //log.info("lapse: "+(lastSend.getTime()-startTime.getTime()));
        if(target==0||lastSend.getTime()-startTime.getTime()>=1000){
        //log.info("lapse: >1000");
            startTime=lastSend;
            count=0;
            target=configService.getConfig().getSinkRate();
        }        
    }
    @Override
    public String summary(){
        return (processed+ignored)==0?"Nothing received at sink":
         "Processed: "+processed+"\tIgnored:"+ignored+"\t Success:"+String.format("%.2f",(processed*100.0/(processed+ignored)));
    }
}
