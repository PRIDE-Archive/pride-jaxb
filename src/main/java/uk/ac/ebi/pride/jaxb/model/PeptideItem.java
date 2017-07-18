
package uk.ac.ebi.pride.jaxb.model;

import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * The Peptide element (complex) holds details of each peptide that has been used to arrive at the identification above.  In a future version of PRIDE it should be possible to include peptides that have not been linked to a protein.
 * 
 * <p>Java class for peptideType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="peptideType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sequence" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Start" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="End" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="SpectrumReference" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="ModificationItem" type="{}modificationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FragmentIon" type="{}fragmentIonType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "peptideType", propOrder = {
    "sequence",
    "start",
    "end",
    "spectrum",
    "modificationItem",
    "fragmentIon",
    "additional"
})
public class PeptideItem
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "Sequence", required = true)
    private String sequence;
    @XmlElement(name = "Start")
    @XmlSchemaType(name = "nonNegativeInteger")
    private BigInteger start;
    @XmlElement(name = "End")
    @XmlSchemaType(name = "nonNegativeInteger")
    private BigInteger end;
    @XmlElement(name = "SpectrumReference")
    @XmlJavaTypeAdapter(SpectrumAdapter.class)
    @XmlSchemaType(name = "nonNegativeInteger")
    private Spectrum spectrum;
    @XmlElement(name = "ModificationItem")
    private List<ModificationItem> modificationItem;
    @XmlElement(name = "FragmentIon")
    private List<FragmentIon> fragmentIon;
    private Param additional;

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequence(String value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStart(BigInteger value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEnd(BigInteger value) {
        this.end = value;
    }

    /**
     * Gets the value of the spectrumReference property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public Spectrum getSpectrum() {
        return spectrum;
    }

    /**
     * Sets the value of the spectrumReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSpectrum(Spectrum value) {
        this.spectrum = value;
    }

    /**
     * Gets the value of the modificationItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modificationItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModificationItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModificationItem }
     * 
     * 
     */
    public List<ModificationItem> getModificationItem() {
        if (modificationItem == null) {
            modificationItem = new ArrayList<>();
        }
        return this.modificationItem;
    }

    /**
     * Gets the value of the fragmentIon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fragmentIon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFragmentIon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FragmentIon }
     * 
     * 
     */
    public List<FragmentIon> getFragmentIon() {
        if (fragmentIon == null) {
            fragmentIon = new ArrayList<>();
        }
        return this.fragmentIon;
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
