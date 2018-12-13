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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dashboardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dashboardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="blockHeight" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bootstrapCols" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalrows" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalcols" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localized" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stores" type="{http://www.ibm.com/csi/npm/dashjson}storeType" maxOccurs="unbounded"/>
 *         &lt;element name="filterbar" type="{http://www.ibm.com/csi/npm/dashjson}filterbarType"/>
 *         &lt;element name="widgets" type="{http://www.ibm.com/csi/npm/dashjson}widgetType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dashboardType", propOrder = {
    "id",
    "title",
    "description",
    "blockHeight",
    "bootstrapCols",
    "totalrows",
    "totalcols",
    "localized",
    "stores",
    "filterbar",
    "widgets"
})
public class DashboardType {

    @XmlElement(required = true, defaultValue = "Dashboard1")
    protected String id;
    @XmlElement(required = true, defaultValue = "Dashboard1")
    protected String title;
    @XmlElement(required = true, defaultValue = "Dashboard1")
    protected String description;
    @XmlElement(required = true, defaultValue = "10")
    protected String blockHeight;
    @XmlElement(required = true, defaultValue = "60")
    protected String bootstrapCols;
    @XmlElement(required = true, defaultValue = "2")
    protected String totalrows;
    @XmlElement(required = true, defaultValue = "2")
    protected String totalcols;
    @XmlElement(defaultValue = "true")
    protected String localized;
    @XmlElement(required = true)
    protected List<StoreType> stores;
    @XmlElement(required = true)
    protected FilterbarType filterbar;
    @XmlElement(required = true)
    protected List<WidgetType> widgets;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the blockHeight property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockHeight() {
        return blockHeight;
    }

    /**
     * Sets the value of the blockHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockHeight(String value) {
        this.blockHeight = value;
    }

    /**
     * Gets the value of the bootstrapCols property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBootstrapCols() {
        return bootstrapCols;
    }

    /**
     * Sets the value of the bootstrapCols property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBootstrapCols(String value) {
        this.bootstrapCols = value;
    }

    /**
     * Gets the value of the totalrows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalrows() {
        return totalrows;
    }

    /**
     * Sets the value of the totalrows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalrows(String value) {
        this.totalrows = value;
    }

    /**
     * Gets the value of the totalcols property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalcols() {
        return totalcols;
    }

    /**
     * Sets the value of the totalcols property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalcols(String value) {
        this.totalcols = value;
    }

    /**
     * Gets the value of the localized property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalized() {
        return localized;
    }

    /**
     * Sets the value of the localized property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalized(String value) {
        this.localized = value;
    }

    /**
     * Gets the value of the stores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreType }
     * 
     * 
     */
    public List<StoreType> getStores() {
        if (stores == null) {
            stores = new ArrayList<StoreType>();
        }
        return this.stores;
    }

    /**
     * Gets the value of the filterbar property.
     * 
     * @return
     *     possible object is
     *     {@link FilterbarType }
     *     
     */
    public FilterbarType getFilterbar() {
        return filterbar;
    }

    /**
     * Sets the value of the filterbar property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterbarType }
     *     
     */
    public void setFilterbar(FilterbarType value) {
        this.filterbar = value;
    }

    /**
     * Gets the value of the widgets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the widgets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWidgets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WidgetType }
     * 
     * 
     */
    public List<WidgetType> getWidgets() {
        if (widgets == null) {
            widgets = new ArrayList<WidgetType>();
        }
        return this.widgets;
    }

}
