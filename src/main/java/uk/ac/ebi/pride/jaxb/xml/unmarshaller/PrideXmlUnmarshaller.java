package uk.ac.ebi.pride.jaxb.xml.unmarshaller;

import uk.ac.ebi.pride.jaxb.model.PrideXmlObject;
import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;

import javax.xml.bind.JAXBException;

/**
 * PrideXMLUnmarshaller is responsible for unmarshallering raw xml strings into
 * JAXB objects.
 * 
 * @author Florian Reisinger
 *
 * @since 0.1
 */
public interface PrideXmlUnmarshaller {

    <T extends PrideXmlObject> T unmarshal(String xmlSnippet, Class<T> cls) throws JAXBException;

    void setSpectrumAdapter(SpectrumAdapter adapter);
}
