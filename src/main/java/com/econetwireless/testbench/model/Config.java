/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author rise2
 */
@Data
@XmlRootElement
public class Config {
    private Map<String,Integer> config;
    private int sinkRate;
}
