//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.25 at 11:07:41 PM IST 
//


package com.ibm.csi.npm.dashjson;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for axisType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="axisType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vertical" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titleOrientation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxLabelCharCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeZero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="minorTicks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="microTicks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minorLabels" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="labelSeries" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fixUpper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rotation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="majorTickStep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropLabels" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="majorLabels" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="majorTicks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fixLower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "axisType", propOrder = {
    "name",
    "title",
    "vertical",
    "titleOrientation",
    "maxLabelCharCount",
    "includeZero",
    "minorTicks",
    "microTicks",
    "minorLabels",
    "labelSeries",
    "fixUpper",
    "rotation",
    "majorTickStep",
    "dropLabels",
    "majorLabels",
    "majorTicks",
    "fixLower"
})
public class AxisType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String title;
    protected String vertical;
    protected String titleOrientation;
    protected String maxLabelCharCount;
    @XmlElement(required = true)
    protected String includeZero;
    protected String minorTicks;
    protected String microTicks;
    protected String minorLabels;
    protected String labelSeries;
    protected String fixUpper;
    protected String rotation;
    protected String majorTickStep;
    protected String dropLabels;
    protected String majorLabels;
    protected String majorTicks;
    protected String fixLower;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the vertical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVertical() {
        return vertical;
    }

    /**
     * Sets the value of the vertical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVertical(String value) {
        this.vertical = value;
    }

    /**
     * Gets the value of the titleOrientation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleOrientation() {
        return titleOrientation;
    }

    /**
     * Sets the value of the titleOrientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleOrientation(String value) {
        this.titleOrientation = value;
    }

    /**
     * Gets the value of the maxLabelCharCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxLabelCharCount() {
        return maxLabelCharCount;
    }

    /**
     * Sets the value of the maxLabelCharCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxLabelCharCount(String value) {
        this.maxLabelCharCount = value;
    }

    /**
     * Gets the value of the includeZero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeZero() {
        return includeZero;
    }

    /**
     * Sets the value of the includeZero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeZero(String value) {
        this.includeZero = value;
    }

    /**
     * Gets the value of the minorTicks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinorTicks() {
        return minorTicks;
    }

    /**
     * Sets the value of the minorTicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinorTicks(String value) {
        this.minorTicks = value;
    }

    /**
     * Gets the value of the microTicks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicroTicks() {
        return microTicks;
    }

    /**
     * Sets the value of the microTicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicroTicks(String value) {
        this.microTicks = value;
    }

    /**
     * Gets the value of the minorLabels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinorLabels() {
        return minorLabels;
    }

    /**
     * Sets the value of the minorLabels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinorLabels(String value) {
        this.minorLabels = value;
    }

    /**
     * Gets the value of the labelSeries property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelSeries() {
        return labelSeries;
    }

    /**
     * Sets the value of the labelSeries property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelSeries(String value) {
        this.labelSeries = value;
    }

    /**
     * Gets the value of the fixUpper property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixUpper() {
        return fixUpper;
    }

    /**
     * Sets the value of the fixUpper property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixUpper(String value) {
        this.fixUpper = value;
    }

    /**
     * Gets the value of the rotation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRotation() {
        return rotation;
    }

    /**
     * Sets the value of the rotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRotation(String value) {
        this.rotation = value;
    }

    /**
     * Gets the value of the majorTickStep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorTickStep() {
        return majorTickStep;
    }

    /**
     * Sets the value of the majorTickStep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorTickStep(String value) {
        this.majorTickStep = value;
    }

    /**
     * Gets the value of the dropLabels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropLabels() {
        return dropLabels;
    }

    /**
     * Sets the value of the dropLabels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropLabels(String value) {
        this.dropLabels = value;
    }

    /**
     * Gets the value of the majorLabels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorLabels() {
        return majorLabels;
    }

    /**
     * Sets the value of the majorLabels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorLabels(String value) {
        this.majorLabels = value;
    }

    /**
     * Gets the value of the majorTicks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorTicks() {
        return majorTicks;
    }

    /**
     * Sets the value of the majorTicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorTicks(String value) {
        this.majorTicks = value;
    }

    /**
     * Gets the value of the fixLower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixLower() {
        return fixLower;
    }

    /**
     * Sets the value of the fixLower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixLower(String value) {
        this.fixLower = value;
    }

}