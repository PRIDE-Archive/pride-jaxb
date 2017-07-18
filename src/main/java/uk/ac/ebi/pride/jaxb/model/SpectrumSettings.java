
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Description of the parameters for the mass spectrometer for a given acquisition (or list of)
 * 
 * <p>Java class for spectrumSettingsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spectrumSettingsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acqSpecification" type="{}acqSpecificationType" minOccurs="0"/>
 *         &lt;element name="spectrumInstrument" type="{}spectrumInstrumentType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spectrumSettingsType", propOrder = {
    "acqSpecification",
    "spectrumInstrument"
})
public class SpectrumSettings
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    private AcqSpecification acqSpecification;
    @XmlElement(required = true)
    private SpectrumInstrument spectrumInstrument;

    /**
     * Gets the value of the acqSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link AcqSpecification }
     *     
     */
    public AcqSpecification getAcqSpecification() {
        return acqSpecification;
    }

    /**
     * Sets the value of the acqSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcqSpecification }
     *     
     */
    public void setAcqSpecification(AcqSpecification value) {
        this.acqSpecification = value;
    }

    /**
     * Gets the value of the spectrumInstrument property.
     * 
     * @return
     *     possible object is
     *     {@link SpectrumInstrument }
     *     
     */
    public SpectrumInstrument getSpectrumInstrument() {
        return spectrumInstrument;
    }

    /**
     * Sets the value of the spectrumInstrument property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectrumInstrument }
     *     
     */
    public void setSpectrumInstrument(SpectrumInstrument value) {
        this.spectrumInstrument = value;
    }

}
