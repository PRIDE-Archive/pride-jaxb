
package uk.ac.ebi.pride.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * A placeholder for fragment ion parameters.
 * 
 * <p>Java class for fragmentIonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fragmentIonType">
 *   &lt;complexContent>
 *     &lt;extension base="{}paramType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fragmentIonType")
public class FragmentIon
    extends Param
    implements Serializable
{

    private final static long serialVersionUID = 100L;

    /**
     * convenient method for accessing the charge.
     *
     * @return Integer  charge is signed integer.
     */
    public Integer getCharge() {
        Integer charge = null;

        CvParam param = this.getCvParamByAcc("PRIDE:0000204");
        if (param != null) {
            charge = Integer.parseInt(param.getValue());
        }

        return charge;
    }

    /**
     * convenient method for accessing the ion type.
     *
     * @return String  ion type.
     */
    public String getIonType() {
        String ionType = null;

        CvParam param = this.getCvParamByAcc("PRIDE:0000198");
        if (param != null) {
            ionType = param.getValue();
        }

        return ionType;
    }

    /**
     * convenient method for accessing the m/z.
     *
     * @return Double  m/z.
     */
    public Double getMz() {
        Double mz = null;

        CvParam param = this.getCvParamByAcc("PRIDE:0000188");
        if (param != null) {
            mz = Double.parseDouble(param.getValue());
        }

        return mz;
    }

    /**
     * convenient method for accessing the intensity.
     *
     * @return Double   intensity.
     */
    public Double getIntensity() {
        Double itent = null;

        CvParam param = this.getCvParamByAcc("PRIDE:0000189");
        if (param != null) {
            itent = Double.parseDouble(param.getValue());
        }

        return itent;
    }

    /**
     * convenient method for accessing the mass error.
     *
     * @return Double  mass error.
     */
    public Double getMassError() {
        Double mr = null;

        CvParam param = this.getCvParamByAcc("PRIDE:0000190");
        if (param != null) {
            mr = Double.parseDouble(param.getValue());
        }

        return mr;
    }
}
