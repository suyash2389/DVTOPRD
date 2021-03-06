//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.06 at 01:17:17 PM IST 
//


package com.ibm.csi.npm.dvreportmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trending complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trending">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trendingPeriodFromUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trendingPeriodSettings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayTrend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upgradeCondition" type="{http://www.ibm.com/csi/npm/dvreportermodel}UpgradeCondition" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trending", propOrder = {
    "trendingPeriodFromUrl",
    "trendingPeriodSettings",
    "displayTrend",
    "upgradeCondition"
})
public class Trending {

    protected String trendingPeriodFromUrl;
    protected String trendingPeriodSettings;
    protected String displayTrend;
    protected UpgradeCondition upgradeCondition;

    /**
     * Gets the value of the trendingPeriodFromUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrendingPeriodFromUrl() {
        return trendingPeriodFromUrl;
    }

    /**
     * Sets the value of the trendingPeriodFromUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrendingPeriodFromUrl(String value) {
        this.trendingPeriodFromUrl = value;
    }

    /**
     * Gets the value of the trendingPeriodSettings property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrendingPeriodSettings() {
        return trendingPeriodSettings;
    }

    /**
     * Sets the value of the trendingPeriodSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrendingPeriodSettings(String value) {
        this.trendingPeriodSettings = value;
    }

    /**
     * Gets the value of the displayTrend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayTrend() {
        return displayTrend;
    }

    /**
     * Sets the value of the displayTrend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayTrend(String value) {
        this.displayTrend = value;
    }

    /**
     * Gets the value of the upgradeCondition property.
     * 
     * @return
     *     possible object is
     *     {@link UpgradeCondition }
     *     
     */
    public UpgradeCondition getUpgradeCondition() {
        return upgradeCondition;
    }

    /**
     * Sets the value of the upgradeCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpgradeCondition }
     *     
     */
    public void setUpgradeCondition(UpgradeCondition value) {
        this.upgradeCondition = value;
    }

}
