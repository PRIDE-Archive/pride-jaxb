
package uk.ac.ebi.pride.jaxb.model;

import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * The abstract Identification element definition; this is a complex type! Also note the different Collections!  There are extended elements based on this one, please see below.
 * 
 * <p>Java class for identificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="identificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Accession">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AccessionVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpliceIsoform" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Database" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DatabaseVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeptideItem" type="{}peptideType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpectrumReference" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="additional" type="{}paramType" minOccurs="0"/>
 *         &lt;element name="Score" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Threshold" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="SearchEngine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SequenceCoverage" type="{}coverageType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identificationType", propOrder = {
    "accession",
    "accessionVersion",
    "spliceIsoform",
    "database",
    "databaseVersion",
    "peptideItem",
    "spectrum",
    "additional",
    "score",
    "threshold",
    "searchEngine",
    "sequenceCoverage"
})
@XmlSeeAlso({
    TwoDimensionalIdentification.class,
    GelFreeIdentification.class
})
public abstract class Identification implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    /** unique id for identification */
    @XmlTransient
    private String id;
    @XmlElement(name = "Accession", required = true)
    protected String accession;
    @XmlElement(name = "AccessionVersion")
    protected String accessionVersion;
    @XmlElement(name = "SpliceIsoform")
    protected String spliceIsoform;
    @XmlElement(name = "Database", required = true)
    protected String database;
    @XmlElement(name = "DatabaseVersion")
    protected String databaseVersion;
    @XmlElement(name = "PeptideItem")
    protected List<PeptideItem> peptideItem;
    @XmlElement(name = "SpectrumReference")
    @XmlJavaTypeAdapter(SpectrumAdapter.class)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Spectrum spectrum;
    protected Param additional;
    @XmlElement(name = "Score")
    protected Double score;
    @XmlElement(name = "Threshold")
    protected Double threshold;
    @XmlElement(name = "SearchEngine")
    protected String searchEngine;
    @XmlElement(name = "SequenceCoverage")
    protected Double sequenceCoverage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null ) {
            throw new IllegalArgumentException("Identification Id can not be null");
        } else {
            this.id = id;
        }
    }

    /**
     * Gets the value of the accession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccession() {
        return accession;
    }

    /**
     * Sets the value of the accession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccession(String value) {
        this.accession = value;
    }

    /**
     * Gets the value of the accessionVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessionVersion() {
        return accessionVersion;
    }

    /**
     * Sets the value of the accessionVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessionVersion(String value) {
        this.accessionVersion = value;
    }

    /**
     * Gets the value of the spliceIsoform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpliceIsoform() {
        return spliceIsoform;
    }

    /**
     * Sets the value of the spliceIsoform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpliceIsoform(String value) {
        this.spliceIsoform = value;
    }

    /**
     * Gets the value of the database property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Sets the value of the database property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabase(String value) {
        this.database = value;
    }

    /**
     * Gets the value of the databaseVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseVersion() {
        return databaseVersion;
    }

    /**
     * Sets the value of the databaseVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseVersion(String value) {
        this.databaseVersion = value;
    }

    /**
     * Gets the value of the peptideItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peptideItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeptideItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeptideItem }
     * 
     * 
     */
    public List<PeptideItem> getPeptideItem() {
        if (peptideItem == null) {
            peptideItem = new ArrayList<PeptideItem>();
        }
        return this.peptideItem;
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

    /**
     * Gets the value of the score property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getScore() {
        return score;
    }

    /**
     * Sets the value of the score property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setScore(Double value) {
        this.score = value;
    }

    /**
     * Gets the value of the threshold property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of the threshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setThreshold(Double value) {
        this.threshold = value;
    }

    /**
     * Gets the value of the searchEngine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchEngine() {
        return searchEngine;
    }

    /**
     * Sets the value of the searchEngine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchEngine(String value) {
        this.searchEngine = value;
    }

    /**
     * Gets the value of the sequenceCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSequenceCoverage() {
        return sequenceCoverage;
    }

    /**
     * Sets the value of the sequenceCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSequenceCoverage(Double value) {
        this.sequenceCoverage = value;
    }

}
