
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The ReferenceType element (abstract) provides an implementation of a simple link to n journal publications for which this experiment provides supporting evidence.
 * 
 * <p>Java class for referenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="referenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RefLine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additional" type="{}paramType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "referenceType", propOrder = {
    "refLine",
    "additional"
})
public class Reference
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "RefLine", required = true)
    protected String refLine;
    protected Param additional;

    /**
     * Gets the value of the refLine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefLine() {
        return refLine;
    }

    /**
     * Sets the value of the refLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefLine(String value) {
        this.refLine = value;
    }

    /**
     * Gets the value of the additional property.
     * 
     * @return
     *     possible object is
     *     {@link Param }
     *     
     */
    public Param getAdditional() {
        return additional;
    }

    /**
     * Sets the value of the additional property.
     * 
     * @param value
     *     allowed object is
     *     {@link Param }
     *     
     */
    public void setAdditional(Param value) {
        this.additional = value;
    }

}
