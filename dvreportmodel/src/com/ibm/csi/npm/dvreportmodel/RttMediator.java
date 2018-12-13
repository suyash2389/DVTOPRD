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
 * <p>Java class for RttMediator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RttMediator">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/csi/npm/dvreportermodel}Mediator">
 *       &lt;sequence>
 *         &lt;element name="metrics" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="violationType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="properties" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sort" type="{http://www.ibm.com/csi/npm/dvreportermodel}TableMediatorSort"/>
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
@XmlType(name = "RttMediator", propOrder = {
    "metrics",
    "violationType",
    "pageSize",
    "properties",
    "sort",
    "stats"
})
public class RttMediator
    extends Mediator
{

    @XmlElement(required = true)
    protected String metrics;
    @XmlElement(required = true)
    protected String violationType;
    protected int pageSize;
    @XmlElement(required = true)
    protected String properties;
    @XmlElement(required = true)
    protected TableMediatorSort sort;
    @XmlElement(required = true)
    protected String stats;

    /**
     * Gets the value of the metrics property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetrics() {
        return metrics;
    }

    /**
     * Sets the value of the metrics property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetrics(String value) {
        this.metrics = value;
    }

    /**
     * Gets the value of the violationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViolationType() {
        return violationType;
    }

    /**
     * Sets the value of the violationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViolationType(String value) {
        this.violationType = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProperties(String value) {
        this.properties = value;
    }

    /**
     * Gets the value of the sort property.
     * 
     * @return
     *     possible object is
     *     {@link TableMediatorSort }
     *     
     */
    public TableMediatorSort getSort() {
        return sort;
    }

    /**
     * Sets the value of the sort property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableMediatorSort }
     *     
     */
    public void setSort(TableMediatorSort value) {
        this.sort = value;
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