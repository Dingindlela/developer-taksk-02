/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.rest;

import com.econetwireless.testbench.model.Config;
import com.econetwireless.testbench.model.Msg;
import com.econetwireless.testbench.service.ConfigService;
import com.econetwireless.testbench.service.SinkService;
import com.econetwireless.testbench.service.SourceStartup;

/**
 *
 * @author rise2
 */
public interface Resource {

    public void setConfigService(ConfigService configService);

    public void setSinkService(SinkService sinkService);

    public void setSourceStartup(SourceStartup sourceStartup);

    public Config getConfig(String accept);

    public void sink(Msg msg);
}
