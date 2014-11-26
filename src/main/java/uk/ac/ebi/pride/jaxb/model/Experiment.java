
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for experimentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="experimentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExperimentAccession" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Reference" type="{}referenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ShortLabel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Protocol" type="{}protocolType"/>
 *         &lt;element name="mzData" type="{}mzDataType"/>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;choice>
 *             &lt;element name="GelFreeIdentification" type="{}gelFreeIdentificationType" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="TwoDimensionalIdentification" type="{}twoDimensionalIdentificationType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/choice>
 *         &lt;/sequence>
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
@XmlType(name = "experimentType", propOrder = {
    "experimentAccession",
    "title",
    "reference",
    "shortLabel",
    "protocol",
    "mzData",
    "gelFreeIdentificationOrTwoDimensionalIdentification",
    "additional"
})
public class Experiment
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "ExperimentAccession")
    protected String experimentAccession;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Reference")
    protected List<Reference> reference;
    @XmlElement(name = "ShortLabel", required = true)
    protected String shortLabel;
    @XmlElement(name = "Protocol", required = true)
    protected Protocol protocol;
    @XmlElement(required = true)
    protected MzData mzData;
    @XmlElements({
        @XmlElement(name = "GelFreeIdentification", type = GelFreeIdentification.class),
        @XmlElement(name = "TwoDimensionalIdentification", type = TwoDimensionalIdentification.class)
    })
    protected List<Identification> gelFreeIdentificationOrTwoDimensionalIdentification;
    protected Param additional;

    /**
     * Gets the value of the experimentAccession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExperimentAccession() {
        return experimentAccession;
    }

    /**
     * Sets the value of the experimentAccession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExperimentAccession(String value) {
        this.experimentAccession = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reference }
     * 
     * 
     */
    public List<Reference> getReference() {
        if (reference == null) {
            reference = new ArrayList<Reference>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the shortLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortLabel() {
        return shortLabel;
    }

    /**
     * Sets the value of the shortLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortLabel(String value) {
        this.shortLabel = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link Protocol }
     *     
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link Protocol }
     *     
     */
    public void setProtocol(Protocol value) {
        this.protocol = value;
    }

    /**
     * Gets the value of the mzData property.
     * 
     * @return
     *     possible object is
     *     {@link MzData }
     *     
     */
    public MzData getMzData() {
        return mzData;
    }

    /**
     * Sets the value of the mzData property.
     * 
     * @param value
     *     allowed object is
     *     {@link MzData }
     *     
     */
    public void setMzData(MzData value) {
        this.mzData = value;
    }

    /**
     * Gets the value of the gelFreeIdentificationOrTwoDimensionalIdentification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gelFreeIdentificationOrTwoDimensionalIdentification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGelFreeIdentificationOrTwoDimensionalIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GelFreeIdentification }
     * {@link TwoDimensionalIdentification }
     * 
     * 
     */
    public List<Identification> getGelFreeIdentificationOrTwoDimensionalIdentification() {
        if (gelFreeIdentificationOrTwoDimensionalIdentification == null) {
            gelFreeIdentificationOrTwoDimensionalIdentification = new ArrayList<Identification>();
        }
        return this.gelFreeIdentificationOrTwoDimensionalIdentification;
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
