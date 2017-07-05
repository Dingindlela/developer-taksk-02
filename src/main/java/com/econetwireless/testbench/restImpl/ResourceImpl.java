/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.restImpl;

import com.econetwireless.testbench.model.Config;
import com.econetwireless.testbench.model.Msg;
import com.econetwireless.testbench.rest.Resource;
import com.econetwireless.testbench.service.ConfigService;
import com.econetwireless.testbench.service.SinkService;
import com.econetwireless.testbench.service.SourceStartup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rise2
 */
@RestController
@RequestMapping("")
public class ResourceImpl implements Resource {

    private ConfigService configService;
    private SinkService sinkService;
    private SourceStartup sourceStartup;

    @Autowired
    @Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Autowired
    @Override
    public void setSinkService(SinkService sinkService) {
        this.sinkService = sinkService;
    }

    @Autowired
    @Override
    public void setSourceStartup(SourceStartup sourceStartup) {
        this.sourceStartup = sourceStartup;
    }

    @Override
    @GetMapping(value = "/config", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Config getConfig(@RequestHeader("Accept") String accept) {
        configService.setAccept(accept);
        Config ret = configService.resetConfig();
        sourceStartup.startServer();
        return ret;
    }

    @Override
    @PostMapping(value = "/sink/msg", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void sink(@RequestBody Msg msg) {
        sinkService.process(msg);
    }
}
