
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for protocolType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="protocolType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProtocolName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProtocolSteps" type="{}protocolStepsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "protocolType", propOrder = {
    "protocolName",
    "protocolSteps"
})
public class Protocol
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "ProtocolName", required = true)
    private String protocolName;
    @XmlElement(name = "ProtocolSteps")
    private ProtocolSteps protocolSteps;

    /**
     * Gets the value of the protocolName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolName() {
        return protocolName;
    }

    /**
     * Sets the value of the protocolName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolName(String value) {
        this.protocolName = value;
    }

    /**
     * Gets the value of the protocolSteps property.
     * 
     * @return
     *     possible object is
     *     {@link ProtocolSteps }
     *     
     */
    public ProtocolSteps getProtocolSteps() {
        return protocolSteps;
    }

    /**
     * Sets the value of the protocolSteps property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProtocolSteps }
     *     
     */
    public void setProtocolSteps(ProtocolSteps value) {
        this.protocolSteps = value;
    }

}
