//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.06 at 01:17:17 PM IST 
//


package com.ibm.csi.npm.dvreportmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChartWidget complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChartWidget">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/csi/npm/dvreportermodel}Widget">
 *       &lt;sequence>
 *         &lt;element name="drilldown" type="{http://www.ibm.com/csi/npm/dvreportermodel}Drilldown" minOccurs="0"/>
 *         &lt;element name="footer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chartStyle" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="autoResize" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="customLabels" type="{http://www.ibm.com/csi/npm/dvreportermodel}CustomLabels" minOccurs="0"/>
 *         &lt;element name="realTimeParameters" type="{http://www.ibm.com/csi/npm/dvreportermodel}RealTimeParameters" minOccurs="0"/>
 *         &lt;element name="realTimeSupport" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChartWidget", propOrder = {
    "drilldown",
    "footer",
    "title",
    "chartStyle",
    "autoResize",
    "customLabels",
    "realTimeParameters",
    "realTimeSupport"
})
@XmlSeeAlso({
    DistributionBarChartWidget.class,
    StackAreaChartWidget.class,
    BaselineChartWidget.class,
    RankVariationChartWidget.class,
    BarHorizWidget.class,
    PieChartWidget.class,
    AreaChartWidget.class,
    StackBarHorizWidget.class
})
public class ChartWidget
    extends Widget
{

    protected Drilldown drilldown;
    protected String footer;
    protected String title;
    protected int chartStyle;
    protected Boolean autoResize;
    protected CustomLabels customLabels;
    protected RealTimeParameters realTimeParameters;
    protected boolean realTimeSupport;

    /**
     * Gets the value of the drilldown property.
     * 
     * @return
     *     possible object is
     *     {@link Drilldown }
     *     
     */
    public Drilldown getDrilldown() {
        return drilldown;
    }

    /**
     * Sets the value of the drilldown property.
     * 
     * @param value
     *     allowed object is
     *     {@link Drilldown }
     *     
     */
    public void setDrilldown(Drilldown value) {
        this.drilldown = value;
    }

    /**
     * Gets the value of the footer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFooter() {
        return footer;
    }

    /**
     * Sets the value of the footer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFooter(String value) {
        this.footer = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the chartStyle property.
     * 
     */
    public int getChartStyle() {
        return chartStyle;
    }

    /**
     * Sets the value of the chartStyle property.
     * 
     */
    public void setChartStyle(int value) {
        this.chartStyle = value;
    }

    /**
     * Gets the value of the autoResize property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoResize() {
        return autoResize;
    }

    /**
     * Sets the value of the autoResize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoResize(Boolean value) {
        this.autoResize = value;
    }

    /**
     * Gets the value of the customLabels property.
     * 
     * @return
     *     possible object is
     *     {@link CustomLabels }
     *     
     */
    public CustomLabels getCustomLabels() {
        return customLabels;
    }

    /**
     * Sets the value of the customLabels property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomLabels }
     *     
     */
    public void setCustomLabels(CustomLabels value) {
        this.customLabels = value;
    }

    /**
     * Gets the value of the realTimeParameters property.
     * 
     * @return
     *     possible object is
     *     {@link RealTimeParameters }
     *     
     */
    public RealTimeParameters getRealTimeParameters() {
        return realTimeParameters;
    }

    /**
     * Sets the value of the realTimeParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealTimeParameters }
     *     
     */
    public void setRealTimeParameters(RealTimeParameters value) {
        this.realTimeParameters = value;
    }

    /**
     * Gets the value of the realTimeSupport property.
     * 
     */
    public boolean isRealTimeSupport() {
        return realTimeSupport;
    }

    /**
     * Sets the value of the realTimeSupport property.
     * 
     */
    public void setRealTimeSupport(boolean value) {
        this.realTimeSupport = value;
    }

}
