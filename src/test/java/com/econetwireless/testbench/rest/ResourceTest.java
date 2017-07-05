/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.rest;

import com.econetwireless.testbench.model.Config;
import com.econetwireless.testbench.model.Msg;
import com.econetwireless.testbench.restImpl.ResourceImpl;
import com.econetwireless.testbench.service.ConfigService;
import com.econetwireless.testbench.service.SinkService;
import com.econetwireless.testbench.service.SourceStartup;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

/**
 *
 * @author rise2
 */
public class ResourceTest {

    private Resource resource;
    private ConfigService configService;
    private SinkService sinkService;
    private SourceStartup sourceStartup;
    
    public ResourceTest() {
    }
@Before
public void setup(){    
         configService =mock(ConfigService.class);
         sinkService =mock(SinkService.class);
         sourceStartup =mock(SourceStartup.class);
         resource=new ResourceImpl();
            resource.setConfigService(configService);
            resource.setSinkService(sinkService);
            resource.setSourceStartup(sourceStartup);
}
        
    /**
     * Test of getConfig method, of class Resource.
     */
    @Test
    public void testGetConfig() {
        String accept="t";
        resource.getConfig(accept);
        verify(configService,times(1)).setAccept(accept);
        verify(configService,times(1)).resetConfig();
        verify(sourceStartup,times(1)).startServer();
    }

    /**
     * Test of sink method, of class Resource.
     */
    @Test
    public void testSink() {
        Msg msg=new Msg();
        resource.sink(msg);
        verify(sinkService,times(1)).process(msg);
    }
    
}
