package uk.ac.ebi.pride.jaxb.utils;

import uk.ac.ebi.pride.jaxb.model.Spectrum;

/**
 * PrideModelUtils
 * 
 * User: rwang
 * Date: 05-May-2010
 * Time: 16:57:21
 */
public class PrideModelUtils {

    /**
     * Create an empty spectrum with the input id only.
     * @param id    input spectrum id.
     * @return Spectrum empty spectrum with id.
     */
    public static Spectrum createSpectrum(int id) {
        Spectrum spec = new Spectrum();
        spec.setId(id);
        return spec;
    }
}
