package uk.ac.ebi.pride.jaxb.xml.adapter;

import uk.ac.ebi.pride.jaxb.model.Spectrum;
import uk.ac.ebi.pride.jaxb.utils.PrideModelUtils;
import uk.ac.ebi.pride.jaxb.xml.extractor.PrideXmlExtractor;
import uk.ac.ebi.pride.jaxb.xml.unmarshaller.PrideXmlUnmarshaller;
import uk.ac.ebi.pride.jaxb.xml.unmarshaller.PrideXmlUnmarshallerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigInteger;

/**
 * ToDo: use caching here?
 *
 * User: rwang
 * Date: 16-Mar-2010
 * Time: 10:37:47
 */
public class SpectrumAdapter extends XmlAdapter<BigInteger, Spectrum> {

    private PrideXmlExtractor extractor = null;

    public SpectrumAdapter() {

    }

    public SpectrumAdapter(PrideXmlExtractor prideXmlExtractor) {
        this.extractor = prideXmlExtractor;
    }

    @Override
    public Spectrum unmarshal(BigInteger id) throws Exception {
        if (extractor != null) {
            // get spectrum xml string
            String xml = extractor.getSpectrumXmlString(id.toString());
            if (xml != null) {
                // unmarshall the spectrum object
                PrideXmlUnmarshaller um = PrideXmlUnmarshallerFactory.getInstance().initializeUnmarshaller();
                return um.unmarshal(xml, Spectrum.class);
            }
        }
        // when the referenced spectrum is not found, return an empty spectrum with id only.
        return PrideModelUtils.createSpectrum(id.intValue());
    }

    @Override
    public BigInteger marshal(Spectrum spectrum) throws Exception {
        if (spectrum !=  null) {
            return new BigInteger(spectrum.getId()+"");
        }
        return null;
    }
}

