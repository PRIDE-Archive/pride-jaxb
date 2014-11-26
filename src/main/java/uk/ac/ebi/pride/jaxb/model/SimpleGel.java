
package uk.ac.ebi.pride.jaxb.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * The SimpleGel element (complex) extends the GelType element to provide a concrete implementation of gel, holding a reference to a gel image (if available).
 * 
 * <p>Java class for simpleGelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="simpleGelType">
 *   &lt;complexContent>
 *     &lt;extension base="{}gelType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "simpleGelType")
public class SimpleGel
    extends Gel
    implements Serializable
{

    private final static long serialVersionUID = 100L;

}
