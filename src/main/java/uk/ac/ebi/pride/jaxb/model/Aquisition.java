
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for acquisitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acquisitionType">
 *   &lt;complexContent>
 *     &lt;extension base="{}paramType">
 *       &lt;attribute name="acqNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acquisitionType")
public class Aquisition
    extends Param
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    protected int acqNumber;

    /**
     * Gets the value of the acqNumber property.
     * 
     */
    public int getAcqNumber() {
        return acqNumber;
    }

    /**
     * Sets the value of the acqNumber property.
     * 
     */
    public void setAcqNumber(int value) {
        this.acqNumber = value;
    }

}
