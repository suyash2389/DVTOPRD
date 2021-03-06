//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.06 at 01:17:17 PM IST 
//


package com.ibm.csi.npm.dvreportmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomLabels complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomLabels">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metricsLabels" type="{http://www.ibm.com/csi/npm/dvreportermodel}ChartMetricLabel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="otherLabels" type="{http://www.ibm.com/csi/npm/dvreportermodel}ChartOtherLabel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="propertiesLabels" type="{http://www.ibm.com/csi/npm/dvreportermodel}ChartPropertiesLabel" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomLabels", propOrder = {
    "metricsLabels",
    "otherLabels",
    "propertiesLabels"
})
public class CustomLabels {

    protected List<ChartMetricLabel> metricsLabels;
    protected List<ChartOtherLabel> otherLabels;
    protected List<ChartPropertiesLabel> propertiesLabels;

    /**
     * Gets the value of the metricsLabels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metricsLabels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetricsLabels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChartMetricLabel }
     * 
     * 
     */
    public List<ChartMetricLabel> getMetricsLabels() {
        if (metricsLabels == null) {
            metricsLabels = new ArrayList<ChartMetricLabel>();
        }
        return this.metricsLabels;
    }

    /**
     * Gets the value of the otherLabels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherLabels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherLabels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChartOtherLabel }
     * 
     * 
     */
    public List<ChartOtherLabel> getOtherLabels() {
        if (otherLabels == null) {
            otherLabels = new ArrayList<ChartOtherLabel>();
        }
        return this.otherLabels;
    }

    /**
     * Gets the value of the propertiesLabels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertiesLabels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertiesLabels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChartPropertiesLabel }
     * 
     * 
     */
    public List<ChartPropertiesLabel> getPropertiesLabels() {
        if (propertiesLabels == null) {
            propertiesLabels = new ArrayList<ChartPropertiesLabel>();
        }
        return this.propertiesLabels;
    }

}
