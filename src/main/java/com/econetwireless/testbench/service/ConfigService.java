/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.service;

import com.econetwireless.testbench.model.Config;

/**
 *
 * @author rise2
 */
public interface ConfigService {

    public int getInitwait();

    public String getUrl();

    public int getTestperiod();

    public long randomRate(int rate);

    public boolean isRun();

    public void setRun(boolean run);

    public String getAccept();

    public void setAccept(String accept);

    public Config getConfig();

    public Config resetConfig();
}
