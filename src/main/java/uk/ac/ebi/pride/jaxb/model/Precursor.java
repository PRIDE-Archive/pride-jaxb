
package uk.ac.ebi.pride.jaxb.model;

import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The method of precursor ion selection and activation
 * 
 * <p>Java class for precursorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="precursorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ionSelection" type="{}paramType"/>
 *         &lt;element name="activation" type="{}paramType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="msLevel" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="spectrumRef" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "precursorType", propOrder = {
    "ionSelection",
    "activation"
})
public class Precursor
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected Param ionSelection;
    @XmlElement(required = true)
    protected Param activation;
    @XmlAttribute(required = true)
    protected int msLevel;
    @XmlAttribute(name = "spectrumRef", required = true)
    @XmlJavaTypeAdapter(SpectrumAdapter.class)
    protected Spectrum spectrum;

    /**
     * Gets the value of the ionSelection property.
     * 
     * @return
     *     possible object is
     *     {@link Param }
     *     
     */
    public Param getIonSelection() {
        return ionSelection;
    }

    /**
     * Sets the value of the ionSelection property.
     * 
     * @param value
     *     allowed object is
     *     {@link Param }
     *     
     */
    public void setIonSelection(Param value) {
        this.ionSelection = value;
    }

    /**
     * Gets the value of the activation property.
     * 
     * @return
     *     possible object is
     *     {@link Param }
     *     
     */
    public Param getActivation() {
        return activation;
    }

    /**
     * Sets the value of the activation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Param }
     *     
     */
    public void setActivation(Param value) {
        this.activation = value;
    }

    /**
     * Gets the value of the msLevel property.
     *
     * @return
     * @return
     */
    public int getMsLevel() {
        return msLevel;
    }

    /**
     * Sets the value of the msLevel property.
     *
     * @param value
     */
    public void setMsLevel(int value) {
        this.msLevel = value;
    }

    /**
     * Gets the value of the spectrumRef property.
     *
     * @return
     */
    public Spectrum getSpectrum() {
        return spectrum;
    }

    /**
     * Sets the value of the spectrumRef property.
     *
     * @param value
     */
    public void setSpectrum(Spectrum value) {
        this.spectrum = value;
    }

}
