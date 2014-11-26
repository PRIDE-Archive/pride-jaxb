
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for acqSpecificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acqSpecificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acquisition" type="{}acquisitionType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="spectrumType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="discrete"/>
 *             &lt;enumeration value="continuous"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="methodOfCombination" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acqSpecificationType", propOrder = {
    "acquisition"
})
public class AcqSpecification
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected List<Aquisition> acquisition;
    @XmlAttribute(required = true)
    protected String spectrumType;
    @XmlAttribute(required = true)
    protected String methodOfCombination;
    @XmlAttribute(required = true)
    protected int count;

    /**
     * Gets the value of the acquisition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acquisition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcquisition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aquisition }
     * 
     * 
     */
    public List<Aquisition> getAcquisition() {
        if (acquisition == null) {
            acquisition = new ArrayList<Aquisition>();
        }
        return this.acquisition;
    }

    /**
     * Gets the value of the spectrumType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpectrumType() {
        return spectrumType;
    }

    /**
     * Sets the value of the spectrumType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpectrumType(String value) {
        this.spectrumType = value;
    }

    /**
     * Gets the value of the methodOfCombination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodOfCombination() {
        return methodOfCombination;
    }

    /**
     * Sets the value of the methodOfCombination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodOfCombination(String value) {
        this.methodOfCombination = value;
    }

    /**
     * Gets the value of the count property.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

}
