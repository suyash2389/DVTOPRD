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
 * <p>Java class for SourceDestination complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SourceDestination">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="destinationProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationPropertyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourcePropertyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SourceDestination", propOrder = {
    "destinationProperty",
    "destinationPropertyLabel",
    "sourceProperty",
    "sourcePropertyLabel"
})
public class SourceDestination {

    protected String destinationProperty;
    protected String destinationPropertyLabel;
    protected String sourceProperty;
    protected String sourcePropertyLabel;

    /**
     * Gets the value of the destinationProperty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationProperty() {
        return destinationProperty;
    }

    /**
     * Sets the value of the destinationProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationProperty(String value) {
        this.destinationProperty = value;
    }

    /**
     * Gets the value of the destinationPropertyLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationPropertyLabel() {
        return destinationPropertyLabel;
    }

    /**
     * Sets the value of the destinationPropertyLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationPropertyLabel(String value) {
        this.destinationPropertyLabel = value;
    }

    /**
     * Gets the value of the sourceProperty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceProperty() {
        return sourceProperty;
    }

    /**
     * Sets the value of the sourceProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceProperty(String value) {
        this.sourceProperty = value;
    }

    /**
     * Gets the value of the sourcePropertyLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePropertyLabel() {
        return sourcePropertyLabel;
    }

    /**
     * Sets the value of the sourcePropertyLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePropertyLabel(String value) {
        this.sourcePropertyLabel = value;
    }

}
