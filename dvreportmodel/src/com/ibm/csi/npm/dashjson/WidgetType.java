//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.25 at 11:07:41 PM IST 
//


package com.ibm.csi.npm.dashjson;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for widgetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="widgetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rowspan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="colspan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xpos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ypos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toolbar" type="{http://www.ibm.com/csi/npm/dashjson}widgettoolbarType"/>
 *         &lt;element name="style" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dynamicseries" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="properties" type="{http://www.ibm.com/csi/npm/dashjson}propertiesType"/>
 *         &lt;element name="columns" type="{http://www.ibm.com/csi/npm/dashjson}columnType" maxOccurs="unbounded"/>
 *         &lt;element name="colorRangeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plots" type="{http://www.ibm.com/csi/npm/dashjson}plotType" maxOccurs="unbounded"/>
 *         &lt;element name="axes" type="{http://www.ibm.com/csi/npm/dashjson}axisType" maxOccurs="unbounded"/>
 *         &lt;element name="series" type="{http://www.ibm.com/csi/npm/dashjson}seriesType" maxOccurs="unbounded"/>
 *         &lt;element name="chartidentity" type="{http://www.ibm.com/csi/npm/dashjson}chartIdentityType" maxOccurs="unbounded"/>
 *         &lt;element name="legend" type="{http://www.ibm.com/csi/npm/dashjson}legendType" minOccurs="0"/>
 *         &lt;element name="tooltip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isReverse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="layoutClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="labelStyle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prevValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="differenceFontSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="badgelabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="badgeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "widgetType", propOrder = {
    "id",
    "title",
    "type",
    "rowspan",
    "colspan",
    "xpos",
    "ypos",
    "toolbar",
    "style",
    "dynamicseries",
    "properties",
    "columns",
    "colorRangeId",
    "plots",
    "axes",
    "series",
    "chartidentity",
    "legend",
    "tooltip",
    "isReverse",
    "layoutClass",
    "labelStyle",
    "prevValue",
    "unit",
    "differenceFontSize",
    "value",
    "badgelabel",
    "badgeType"
})
public class WidgetType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true, defaultValue = "")
    protected String title;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true, defaultValue = "1")
    protected String rowspan;
    @XmlElement(required = true, defaultValue = "1")
    protected String colspan;
    @XmlElement(required = true)
    protected String xpos;
    @XmlElement(required = true)
    protected String ypos;
    @XmlElement(required = true)
    protected WidgettoolbarType toolbar;
    @XmlElement(required = true, defaultValue = "height: 160px;width:98.5%;")
    protected String style;
    @XmlElement(required = true, defaultValue = "true")
    protected String dynamicseries;
    @XmlElement(required = true)
    protected PropertiesType properties;
    @XmlElement(required = true)
    protected List<ColumnType> columns;
    @XmlElement(defaultValue = "tnpm7")
    protected String colorRangeId;
    @XmlElement(required = true)
    protected List<PlotType> plots;
    @XmlElement(required = true)
    protected List<AxisType> axes;
    @XmlElement(required = true)
    protected List<SeriesType> series;
    @XmlElement(required = true)
    protected List<ChartIdentityType> chartidentity;
    protected LegendType legend;
    @XmlElement(defaultValue = "true")
    protected String tooltip;
    @XmlElement(defaultValue = "true")
    protected String isReverse;
    @XmlElement(defaultValue = "noBorder")
    protected String layoutClass;
    @XmlElement(defaultValue = "width:100%;float:left;height:100%;font-size:13px;color:rgb(102,102,102) !important;text-align:left;background-color:#ffffff;border-bottom:2px solid #b7c1d1;padding:10px 0 0 0;")
    protected String labelStyle;
    protected String prevValue;
    protected String unit;
    protected String differenceFontSize;
    @XmlElement(defaultValue = "")
    protected String value;
    protected String badgelabel;
    @XmlElement(defaultValue = "titleBadge")
    protected String badgeType;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the rowspan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowspan() {
        return rowspan;
    }

    /**
     * Sets the value of the rowspan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowspan(String value) {
        this.rowspan = value;
    }

    /**
     * Gets the value of the colspan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColspan() {
        return colspan;
    }

    /**
     * Sets the value of the colspan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColspan(String value) {
        this.colspan = value;
    }

    /**
     * Gets the value of the xpos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpos() {
        return xpos;
    }

    /**
     * Sets the value of the xpos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpos(String value) {
        this.xpos = value;
    }

    /**
     * Gets the value of the ypos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYpos() {
        return ypos;
    }

    /**
     * Sets the value of the ypos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYpos(String value) {
        this.ypos = value;
    }

    /**
     * Gets the value of the toolbar property.
     * 
     * @return
     *     possible object is
     *     {@link WidgettoolbarType }
     *     
     */
    public WidgettoolbarType getToolbar() {
        return toolbar;
    }

    /**
     * Sets the value of the toolbar property.
     * 
     * @param value
     *     allowed object is
     *     {@link WidgettoolbarType }
     *     
     */
    public void setToolbar(WidgettoolbarType value) {
        this.toolbar = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    /**
     * Gets the value of the dynamicseries property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDynamicseries() {
        return dynamicseries;
    }

    /**
     * Sets the value of the dynamicseries property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDynamicseries(String value) {
        this.dynamicseries = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setProperties(PropertiesType value) {
        this.properties = value;
    }

    /**
     * Gets the value of the columns property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the columns property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColumns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ColumnType }
     * 
     * 
     */
    public List<ColumnType> getColumns() {
        if (columns == null) {
            columns = new ArrayList<ColumnType>();
        }
        return this.columns;
    }

    /**
     * Gets the value of the colorRangeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColorRangeId() {
        return colorRangeId;
    }

    /**
     * Sets the value of the colorRangeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColorRangeId(String value) {
        this.colorRangeId = value;
    }

    /**
     * Gets the value of the plots property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plots property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlots().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlotType }
     * 
     * 
     */
    public List<PlotType> getPlots() {
        if (plots == null) {
            plots = new ArrayList<PlotType>();
        }
        return this.plots;
    }

    /**
     * Gets the value of the axes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the axes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAxes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AxisType }
     * 
     * 
     */
    public List<AxisType> getAxes() {
        if (axes == null) {
            axes = new ArrayList<AxisType>();
        }
        return this.axes;
    }

    /**
     * Gets the value of the series property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the series property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SeriesType }
     * 
     * 
     */
    public List<SeriesType> getSeries() {
        if (series == null) {
            series = new ArrayList<SeriesType>();
        }
        return this.series;
    }

    /**
     * Gets the value of the chartidentity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chartidentity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChartidentity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChartIdentityType }
     * 
     * 
     */
    public List<ChartIdentityType> getChartidentity() {
        if (chartidentity == null) {
            chartidentity = new ArrayList<ChartIdentityType>();
        }
        return this.chartidentity;
    }

    /**
     * Gets the value of the legend property.
     * 
     * @return
     *     possible object is
     *     {@link LegendType }
     *     
     */
    public LegendType getLegend() {
        return legend;
    }

    /**
     * Sets the value of the legend property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegendType }
     *     
     */
    public void setLegend(LegendType value) {
        this.legend = value;
    }

    /**
     * Gets the value of the tooltip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Sets the value of the tooltip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTooltip(String value) {
        this.tooltip = value;
    }

    /**
     * Gets the value of the isReverse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsReverse() {
        return isReverse;
    }

    /**
     * Sets the value of the isReverse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsReverse(String value) {
        this.isReverse = value;
    }

    /**
     * Gets the value of the layoutClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayoutClass() {
        return layoutClass;
    }

    /**
     * Sets the value of the layoutClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayoutClass(String value) {
        this.layoutClass = value;
    }

    /**
     * Gets the value of the labelStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelStyle() {
        return labelStyle;
    }

    /**
     * Sets the value of the labelStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelStyle(String value) {
        this.labelStyle = value;
    }

    /**
     * Gets the value of the prevValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevValue() {
        return prevValue;
    }

    /**
     * Sets the value of the prevValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevValue(String value) {
        this.prevValue = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the differenceFontSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDifferenceFontSize() {
        return differenceFontSize;
    }

    /**
     * Sets the value of the differenceFontSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDifferenceFontSize(String value) {
        this.differenceFontSize = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the badgelabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBadgelabel() {
        return badgelabel;
    }

    /**
     * Sets the value of the badgelabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBadgelabel(String value) {
        this.badgelabel = value;
    }

    /**
     * Gets the value of the badgeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBadgeType() {
        return badgeType;
    }

    /**
     * Sets the value of the badgeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBadgeType(String value) {
        this.badgeType = value;
    }

}
