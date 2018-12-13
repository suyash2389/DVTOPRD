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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MatrixMediator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MatrixMediator">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/csi/npm/dvreportermodel}Mediator">
 *       &lt;sequence>
 *         &lt;element name="sourceDestination" type="{http://www.ibm.com/csi/npm/dvreportermodel}SourceDestination"/>
 *         &lt;element name="formulas" type="{http://www.ibm.com/csi/npm/dvreportermodel}MatricMediatorFormula" maxOccurs="unbounded"/>
 *         &lt;element name="matrixType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceFilters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatrixMediator", propOrder = {
    "sourceDestination",
    "formulas",
    "matrixType",
    "resourceFilters"
})
public class MatrixMediator
    extends Mediator
{

    @XmlElement(required = true)
    protected SourceDestination sourceDestination;
    @XmlElement(required = true)
    protected List<MatricMediatorFormula> formulas;
    protected String matrixType;
    protected String resourceFilters;

    /**
     * Gets the value of the sourceDestination property.
     * 
     * @return
     *     possible object is
     *     {@link SourceDestination }
     *     
     */
    public SourceDestination getSourceDestination() {
        return sourceDestination;
    }

    /**
     * Sets the value of the sourceDestination property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceDestination }
     *     
     */
    public void setSourceDestination(SourceDestination value) {
        this.sourceDestination = value;
    }

    /**
     * Gets the value of the formulas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formulas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormulas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatricMediatorFormula }
     * 
     * 
     */
    public List<MatricMediatorFormula> getFormulas() {
        if (formulas == null) {
            formulas = new ArrayList<MatricMediatorFormula>();
        }
        return this.formulas;
    }

    /**
     * Gets the value of the matrixType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatrixType() {
        return matrixType;
    }

    /**
     * Sets the value of the matrixType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatrixType(String value) {
        this.matrixType = value;
    }

    /**
     * Gets the value of the resourceFilters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceFilters() {
        return resourceFilters;
    }

    /**
     * Sets the value of the resourceFilters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceFilters(String value) {
        this.resourceFilters = value;
    }

}