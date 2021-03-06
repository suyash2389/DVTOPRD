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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChartMediator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChartMediator">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/csi/npm/dvreportermodel}Mediator">
 *       &lt;sequence>
 *         &lt;element name="metric" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stats" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChartMediator", propOrder = {
    "metric",
    "stats"
})
@XmlSeeAlso({
    ResDistribMediator.class,
    BaselineMediator.class,
    RatioMediator.class
})
public class ChartMediator
    extends Mediator
{

    @XmlElement(required = true)
    protected String metric;
    @XmlElement(required = true)
    protected String stats;

    /**
     * Gets the value of the metric property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetric() {
        return metric;
    }

    /**
     * Sets the value of the metric property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetric(String value) {
        this.metric = value;
    }

    /**
     * Gets the value of the stats property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStats() {
        return stats;
    }

    /**
     * Sets the value of the stats property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStats(String value) {
        this.stats = value;
    }

}
