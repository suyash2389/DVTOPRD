//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.06 at 01:17:17 PM IST 
//


package com.ibm.csi.npm.dvreportmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GTSNearRealTime complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GTSNearRealTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RefreshRate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="enable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="secondsToWait" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GTSNearRealTime", propOrder = {
    "refreshRate",
    "enable",
    "secondsToWait"
})
public class GTSNearRealTime {

    @XmlElement(name = "RefreshRate")
    protected int refreshRate;
    protected boolean enable;
    protected int secondsToWait;

    /**
     * Gets the value of the refreshRate property.
     * 
     */
    public int getRefreshRate() {
        return refreshRate;
    }

    /**
     * Sets the value of the refreshRate property.
     * 
     */
    public void setRefreshRate(int value) {
        this.refreshRate = value;
    }

    /**
     * Gets the value of the enable property.
     * 
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets the value of the enable property.
     * 
     */
    public void setEnable(boolean value) {
        this.enable = value;
    }

    /**
     * Gets the value of the secondsToWait property.
     * 
     */
    public int getSecondsToWait() {
        return secondsToWait;
    }

    /**
     * Sets the value of the secondsToWait property.
     * 
     */
    public void setSecondsToWait(int value) {
        this.secondsToWait = value;
    }

}
