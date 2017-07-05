/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.service;

import com.econetwireless.testbench.model.Msg;
import java.util.Date;

/**
 *
 * @author rise2
 */
public interface SinkService {
public void setConfigService(ConfigService configService);
    public Date getLastSend();

    public void process(Msg msg);

    public String summary();
}
