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
 * <p>Java class for TableFormulaWithoutTH complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TableFormulaWithoutTH">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ibm.com/csi/npm/dvreportermodel}TableFormula">
 *       &lt;sequence>
 *         &lt;element name="formula" type="{http://www.ibm.com/csi/npm/dvreportermodel}MeticsFormat"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableFormulaWithoutTH", propOrder = {
    "formula"
})
public class TableFormulaWithoutTH
    extends TableFormula
{

    @XmlElement(required = true)
    protected MeticsFormat formula;

    /**
     * Gets the value of the formula property.
     * 
     * @return
     *     possible object is
     *     {@link MeticsFormat }
     *     
     */
    public MeticsFormat getFormula() {
        return formula;
    }

    /**
     * Sets the value of the formula property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeticsFormat }
     *     
     */
    public void setFormula(MeticsFormat value) {
        this.formula = value;
    }

}
