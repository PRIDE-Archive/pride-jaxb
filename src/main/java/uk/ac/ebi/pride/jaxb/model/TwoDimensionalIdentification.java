
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This is a concrete implementation of the abstract Identification element.  This is used to represent identifications based upon 2D Gel Electrophoresis.  Details of the gel, location of the spot, pI and molecular weight are included in this identification element.
 * 
 * <p>Java class for twoDimensionalIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="twoDimensionalIdentificationType">
 *   &lt;complexContent>
 *     &lt;extension base="{}identificationType">
 *       &lt;sequence>
 *         &lt;element name="Gel" type="{}simpleGelType" minOccurs="0"/>
 *         &lt;element name="GelLocation" type="{}pointType" minOccurs="0"/>
 *         &lt;element name="MolecularWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pI" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "twoDimensionalIdentificationType", propOrder = {
    "gel",
    "gelLocation",
    "molecularWeight",
    "pi"
})
public class TwoDimensionalIdentification
    extends Identification
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "Gel")
    protected SimpleGel gel;
    @XmlElement(name = "GelLocation")
    protected GelLocation gelLocation;
    @XmlElement(name = "MolecularWeight")
    protected Double molecularWeight;
    @XmlElement(name = "pI")
    protected Double pi;

    /**
     * Gets the value of the gel property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleGel }
     *     
     */
    public SimpleGel getGel() {
        return gel;
    }

    /**
     * Sets the value of the gel property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleGel }
     *     
     */
    public void setGel(SimpleGel value) {
        this.gel = value;
    }

    /**
     * Gets the value of the gelLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GelLocation }
     *     
     */
    public GelLocation getGelLocation() {
        return gelLocation;
    }

    /**
     * Sets the value of the gelLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GelLocation }
     *     
     */
    public void setGelLocation(GelLocation value) {
        this.gelLocation = value;
    }

    /**
     * Gets the value of the molecularWeight property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMolecularWeight() {
        return molecularWeight;
    }

    /**
     * Sets the value of the molecularWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMolecularWeight(Double value) {
        this.molecularWeight = value;
    }

    /**
     * Gets the value of the pi property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPI() {
        return pi;
    }

    /**
     * Sets the value of the pi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPI(Double value) {
        this.pi = value;
    }

}
