
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Description of the components of the mass spectrometer used
 * 
 * <p>Java class for instrumentDescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="instrumentDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="instrumentName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="source" type="{}paramType"/>
 *         &lt;element name="analyzerList" type="{}analyzerListType"/>
 *         &lt;element name="detector" type="{}paramType"/>
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
@XmlType(name = "instrumentDescriptionType", propOrder = {
    "instrumentName",
    "source",
    "analyzerList",
    "detector",
    "additional"
})
public class Instrument
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected String instrumentName;
    @XmlElement(required = true)
    protected Param source;
    @XmlElement(required = true)
    protected AnalyzerList analyzerList;
    @XmlElement(required = true)
    protected Param detector;
    protected Param additional;

    /**
     * Gets the value of the instrumentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstrumentName() {
        return instrumentName;
    }

    /**
     * Sets the value of the instrumentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstrumentName(String value) {
        this.instrumentName = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Param }
     *     
     */
    public Param getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Param }
     *     
     */
    public void setSource(Param value) {
        this.source = value;
    }

    /**
     * Gets the value of the analyzerList property.
     * 
     * @return
     *     possible object is
     *     {@link AnalyzerList }
     *     
     */
    public AnalyzerList getAnalyzerList() {
        return analyzerList;
    }

    /**
     * Sets the value of the analyzerList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnalyzerList }
     *     
     */
    public void setAnalyzerList(AnalyzerList value) {
        this.analyzerList = value;
    }

    /**
     * Gets the value of the detector property.
     * 
     * @return
     *     possible object is
     *     {@link Param }
     *     
     */
    public Param getDetector() {
        return detector;
    }

    /**
     * Sets the value of the detector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Param }
     *     
     */
    public void setDetector(Param value) {
        this.detector = value;
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
