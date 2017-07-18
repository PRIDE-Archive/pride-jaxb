
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines a coordinate system for describing the position of a spot on a gel.
 * 
 * <p>Java class for pointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XCoordinate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="YCoordinate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pointType", propOrder = {
    "xCoordinate",
    "yCoordinate"
})
public class GelLocation
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "XCoordinate")
    private double xCoordinate;
    @XmlElement(name = "YCoordinate")
    private double yCoordinate;

    /**
     * Gets the value of the xCoordinate property.
     * 
     */
    public double getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Sets the value of the xCoordinate property.
     * 
     */
    public void setXCoordinate(double value) {
        this.xCoordinate = value;
    }

    /**
     * Gets the value of the yCoordinate property.
     * 
     */
    public double getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Sets the value of the yCoordinate property.
     * 
     */
    public void setYCoordinate(double value) {
        this.yCoordinate = value;
    }

}
