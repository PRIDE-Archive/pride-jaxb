package uk.ac.ebi.pride.jaxb.xml.unmarshaller;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import uk.ac.ebi.pride.jaxb.model.ModelConstants;
import uk.ac.ebi.pride.jaxb.model.PrideXmlObject;
import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;
import uk.ac.ebi.pride.jaxb.xml.util.EscapingXMLUtilities;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;

/**
 * @author Florian Reisinger
 * @author rwang
 * @since 0.1
 */
public class PrideXmlUnmarshallerFactory {

    private static final Logger logger = Logger.getLogger(PrideXmlUnmarshallerFactory.class);

    private static PrideXmlUnmarshallerFactory instance = new PrideXmlUnmarshallerFactory();
    private static JAXBContext jc = null;

    private PrideXmlUnmarshallerFactory() {
    }

    public static PrideXmlUnmarshallerFactory getInstance() {
        return instance;
    }

    public PrideXmlUnmarshaller initializeUnmarshaller() {

        try {
            // Lazy caching of the JAXB Context.
            if (jc == null) {
                jc = JAXBContext.newInstance(ModelConstants.MODEL_PKG);
            }

            //create unmarshaller
            PrideXmlUnmarshaller pum = new PrideUnmarshallerImpl();
            logger.debug("Unmarshaller Initialized");

            return pum;

        } catch (JAXBException e) {
            logger.error("UnmarshallerFactory.initializeUnmarshaller", e);
            throw new IllegalStateException("Could not initialize unmarshaller", e);
        }
    }

    private class PrideUnmarshallerImpl implements PrideXmlUnmarshaller {

        private Unmarshaller unmarshaller = null;

        private PrideUnmarshallerImpl() throws JAXBException {
            unmarshaller = jc.createUnmarshaller();
        }

        /**
         * Add synchronization feature, unmarshaller is not thread safe by default.
         *
         * @param xmlSnippet raw xml string
         * @param cls        class type to unmarshall to.
         * @param <T>        an instance of class type.
         * @return T    return an instance of class type.
         * @throws JAXBException
         */
        public synchronized <T extends PrideXmlObject> T unmarshal(String xmlSnippet, Class<T> cls) throws JAXBException {
            if (xmlSnippet == null || cls == null) {
                return null;
            }

            //need to clean up XML to ensure that there are no weird control characters
            String cleanXML = EscapingXMLUtilities.escapeCharacters(xmlSnippet);
            JAXBElement<T> holder = unmarshaller.unmarshal(new SAXSource(new InputSource(new StringReader(cleanXML))), cls);
            return holder.getValue();
        }

        public synchronized void setSpectrumAdapter(SpectrumAdapter adapter) {
            unmarshaller.setAdapter(adapter);
        }
    }
}
