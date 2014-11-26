
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Description of the process of performing an acquisition
 * 
 * <p>Java class for spectrumDescType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spectrumDescType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spectrumSettings" type="{}spectrumSettingsType"/>
 *         &lt;element name="precursorList" type="{}precursorListType" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spectrumDescType", propOrder = {
    "spectrumSettings",
    "precursorList",
    "comments"
})
public class SpectrumDesc
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected SpectrumSettings spectrumSettings;
    protected PrecursorList precursorList;
    protected List<String> comments;

    /**
     * Gets the value of the spectrumSettings property.
     * 
     * @return
     *     possible object is
     *     {@link SpectrumSettings }
     *     
     */
    public SpectrumSettings getSpectrumSettings() {
        return spectrumSettings;
    }

    /**
     * Sets the value of the spectrumSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectrumSettings }
     *     
     */
    public void setSpectrumSettings(SpectrumSettings value) {
        this.spectrumSettings = value;
    }

    /**
     * Gets the value of the precursorList property.
     * 
     * @return
     *     possible object is
     *     {@link PrecursorList }
     *     
     */
    public PrecursorList getPrecursorList() {
        return precursorList;
    }

    /**
     * Sets the value of the precursorList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrecursorList }
     *     
     */
    public void setPrecursorList(PrecursorList value) {
        this.precursorList = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComments() {
        if (comments == null) {
            comments = new ArrayList<String>();
        }
        return this.comments;
    }

}
