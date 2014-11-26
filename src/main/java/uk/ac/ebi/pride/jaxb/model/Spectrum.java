
package uk.ac.ebi.pride.jaxb.model;

import uk.ac.ebi.pride.jaxb.utils.BinaryDataUtils;
import uk.ac.ebi.pride.jaxb.utils.CvTermReference;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;


/**
 * The structure tha captures the generation of a peak list (including the underlying acquisitions)
 * 
 * <p>Java class for spectrumType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spectrumType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spectrumDesc" type="{}spectrumDescType"/>
 *         &lt;element name="supDesc" type="{}supDescType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mzArrayBinary" type="{}mzArrayBinaryType"/>
 *         &lt;element name="intenArrayBinary" type="{}intenArrayBinaryType"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="supDataArrayBinary" type="{}supDataBinaryType"/>
 *           &lt;element name="supDataArray" type="{}supDataType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spectrumType", propOrder = {
    "spectrumDesc",
    "supDesc",
    "mzArrayBinary",
    "intenArrayBinary",
    "supDataArrayBinaryOrSupDataArray"
})
public class Spectrum
    implements Serializable, PrideXmlObject
{

    private final static long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected SpectrumDesc spectrumDesc;
    protected List<SupDesc> supDesc;
    @XmlElement(required = true)
    protected MzArrayBinary mzArrayBinary;
    @XmlElement(required = true)
    protected IntenArrayBinary intenArrayBinary;
    @XmlElements({
        @XmlElement(name = "supDataArray", type = SupDataArray.class),
        @XmlElement(name = "supDataArrayBinary", type = SupDataBinary.class)
    })
    protected List<PrideXmlObject> supDataArrayBinaryOrSupDataArray;
    @XmlAttribute(required = true)
    protected int id;

    /**
     * Gets the value of the spectrumDesc property.
     * 
     * @return
     *     possible object is
     *     {@link SpectrumDesc }
     *     
     */
    public SpectrumDesc getSpectrumDesc() {
        return spectrumDesc;
    }

    /**
     * Sets the value of the spectrumDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpectrumDesc }
     *     
     */
    public void setSpectrumDesc(SpectrumDesc value) {
        this.spectrumDesc = value;
    }

    /**
     * Gets the value of the supDesc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supDesc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupDesc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupDesc }
     * 
     * 
     */
    public List<SupDesc> getSupDesc() {
        if (supDesc == null) {
            supDesc = new ArrayList<SupDesc>();
        }
        return this.supDesc;
    }

    /**
     * Gets the value of the mzArrayBinary property.
     * 
     * @return
     *     possible object is
     *     {@link MzArrayBinary }
     *     
     */
    public MzArrayBinary getMzArrayBinary() {
        return mzArrayBinary;
    }

    /**
     * This is convinent method for returning a number array of mz data.
     *
     * @return Number[] mz array.
     */
    public Number[] getMzNumberArray() {
        return getNumberArray(mzArrayBinary.getData());
    }

    /**
     * Sets the value of the mzArrayBinary property.
     * 
     * @param value
     *     allowed object is
     *     {@link MzArrayBinary }
     *     
     */
    public void setMzArrayBinary(MzArrayBinary value) {
        this.mzArrayBinary = value;
    }

    /**
     * Gets the value of the intenArrayBinary property.
     * 
     * @return
     *     possible object is
     *     {@link IntenArrayBinary }
     *     
     */
    public IntenArrayBinary getIntenArrayBinary() {
        return intenArrayBinary;
    }

    /**
     * This is convinenet method for returning a number array of intensity data.
     *
     * @return Number[] intensity array.
     */
    public Number[] getIntentArray() {
        return getNumberArray(intenArrayBinary.getData());
    }

    /**
     * Convert a data object to number array.
     *
     * @param rawData
     * @return
     */
    private Number[] getNumberArray(Data rawData) {
        //get the binary
        byte[] binary = rawData.getValue();

        //check precision
        CvTermReference dataType = "32".equals(rawData.getPrecision()) ?
                                        CvTermReference.FLOAT_32_BIT : CvTermReference.FLOAT_64_BIT;

        //check endianess
        ByteOrder order = "big".equals(rawData.getEndian()) ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;

        Number[] numberArr = BinaryDataUtils.toNumberArray(binary, dataType, order);

        return numberArr;
    }

    /**
     * Sets the value of the intenArrayBinary property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntenArrayBinary }
     *     
     */
    public void setIntenArrayBinary(IntenArrayBinary value) {
        this.intenArrayBinary = value;
    }

    /**
     * Gets the value of the supDataArrayBinaryOrSupDataArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supDataArrayBinaryOrSupDataArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupDataArrayBinaryOrSupDataArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupDataArray }
     * {@link SupDataBinary }
     * 
     * 
     */
    public List<PrideXmlObject> getSupDataArrayBinaryOrSupDataArray() {
        if (supDataArrayBinaryOrSupDataArray == null) {
            supDataArrayBinaryOrSupDataArray = new ArrayList<PrideXmlObject>();
        }
        return this.supDataArrayBinaryOrSupDataArray;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}
