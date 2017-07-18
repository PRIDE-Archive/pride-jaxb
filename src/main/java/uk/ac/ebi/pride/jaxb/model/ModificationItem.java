
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * The Modification element definition; complex type again, NOTE the fact that it cannot have a generated marshaller/unmarshaller for its data storage/reading! 
 * 
 * <p>Java class for modificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModLocation" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="ModAccession">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ModDatabase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModDatabaseVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModMonoDelta" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ModAvgDelta" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "modificationType", propOrder = {
    "modLocation",
    "modAccession",
    "modDatabase",
    "modDatabaseVersion",
    "modMonoDelta",
    "modAvgDelta",
    "additional"
})
public class ModificationItem
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "ModLocation")
    @XmlSchemaType(name = "nonNegativeInteger")
    private BigInteger modLocation;
    @XmlElement(name = "ModAccession", required = true)
    private String modAccession;
    @XmlElement(name = "ModDatabase", required = true)
    private String modDatabase;
    @XmlElement(name = "ModDatabaseVersion")
    private String modDatabaseVersion;
    @XmlElement(name = "ModMonoDelta")
    private List<String> modMonoDelta;
    @XmlElement(name = "ModAvgDelta")
    private List<String> modAvgDelta;
    private Param additional;

    /**
     * Gets the value of the modLocation property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getModLocation() {
        return modLocation;
    }

    /**
     * Sets the value of the modLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setModLocation(BigInteger value) {
        this.modLocation = value;
    }

    /**
     * Gets the value of the modAccession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModAccession() {
        return modAccession;
    }

    /**
     * Sets the value of the modAccession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModAccession(String value) {
        this.modAccession = value;
    }

    /**
     * Gets the value of the modDatabase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModDatabase() {
        return modDatabase;
    }

    /**
     * Sets the value of the modDatabase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModDatabase(String value) {
        this.modDatabase = value;
    }

    /**
     * Gets the value of the modDatabaseVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModDatabaseVersion() {
        return modDatabaseVersion;
    }

    /**
     * Sets the value of the modDatabaseVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModDatabaseVersion(String value) {
        this.modDatabaseVersion = value;
    }

    /**
     * Gets the value of the modMonoDelta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modMonoDelta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModMonoDelta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getModMonoDelta() {
        if (modMonoDelta == null) {
            modMonoDelta = new ArrayList<>();
        }
        return this.modMonoDelta;
    }

    /**
     * Gets the value of the modAvgDelta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modAvgDelta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModAvgDelta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getModAvgDelta() {
        if (modAvgDelta == null) {
            modAvgDelta = new ArrayList<>();
        }
        return this.modAvgDelta;
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
