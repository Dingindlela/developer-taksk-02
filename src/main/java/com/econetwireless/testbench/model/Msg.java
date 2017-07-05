/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.econetwireless.testbench.model;

import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rise2
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Msg {
    private String id;
    private String source;
    private String payload;
    private String ref;
}
