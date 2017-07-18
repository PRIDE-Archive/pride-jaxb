
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spectrumInstrumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spectrumInstrumentType">
 *   &lt;complexContent>
 *     &lt;extension base="{}paramType">
 *       &lt;attribute name="msLevel" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="mzRangeStart" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="mzRangeStop" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spectrumInstrumentType")
public class SpectrumInstrument
    extends Param
    implements Serializable
{

    private final static long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    private int msLevel;
    @XmlAttribute
    private Float mzRangeStart;
    @XmlAttribute
    private Float mzRangeStop;

    /**
     * Gets the value of the msLevel property.
     * 
     */
    public int getMsLevel() {
        return msLevel;
    }

    /**
     * Sets the value of the msLevel property.
     * 
     */
    public void setMsLevel(int value) {
        this.msLevel = value;
    }

    /**
     * Gets the value of the mzRangeStart property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMzRangeStart() {
        return mzRangeStart;
    }

    /**
     * Sets the value of the mzRangeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMzRangeStart(Float value) {
        this.mzRangeStart = value;
    }

    /**
     * Gets the value of the mzRangeStop property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMzRangeStop() {
        return mzRangeStop;
    }

    /**
     * Sets the value of the mzRangeStop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMzRangeStop(Float value) {
        this.mzRangeStop = value;
    }

}
