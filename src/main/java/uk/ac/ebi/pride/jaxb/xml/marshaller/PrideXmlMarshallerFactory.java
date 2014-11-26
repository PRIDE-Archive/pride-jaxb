package uk.ac.ebi.pride.jaxb.xml.marshaller;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import org.apache.log4j.Logger;
import uk.ac.ebi.pride.jaxb.model.ExperimentCollection;
import uk.ac.ebi.pride.jaxb.model.ModelConstants;
import uk.ac.ebi.pride.jaxb.model.PrideXmlObject;
import uk.ac.ebi.pride.jaxb.xml.util.EscapingXMLStreamWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author rcote
 * @author florian
 */
@SuppressWarnings("unused")
public class PrideXmlMarshallerFactory {

    private static final Logger logger = Logger.getLogger(PrideXmlMarshallerFactory.class);

    private static PrideXmlMarshallerFactory instance = new PrideXmlMarshallerFactory();
    private static JAXBContext jc = null;

    private PrideXmlMarshallerFactory() {
    }

    public static PrideXmlMarshallerFactory getInstance() {
        return instance;
    }

    public PrideXmlMarshaller initializeMarshaller() {

        try {
            // Lazy caching of the JAXB Context.
            if (jc == null) {
                jc = JAXBContext.newInstance(ModelConstants.MODEL_PKG);
            }

            //create unmarshaller
            PrideXmlMarshaller pm = new PrideMarshallerImpl();
            logger.debug("Marshaller Initialized");

            return pm;

        } catch (JAXBException e) {
            logger.error("PrideXmlMarshaller.initializeMarshaller", e);
            throw new IllegalStateException("Could not initialize marshaller", e);
        }
    }

    private class PrideMarshallerImpl implements PrideXmlMarshaller {

        private Marshaller marshaller = null;

        private PrideMarshallerImpl() throws JAXBException {
            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        }

        public <T extends PrideXmlObject> String marshall(T object) {
            StringWriter sw = new StringWriter();
            this.marshall(object, sw);
            return sw.toString();
        }

        public <T extends PrideXmlObject> void marshall(T object, OutputStream os) {
            // pass on to the marshall(PrideXmlObject, Writer) method
            this.marshall(object, new OutputStreamWriter(os));
        }

        public <T extends PrideXmlObject> void marshall(T object, Writer out) {
            // pass on to the marshall(PrideXmlObject, XMLStreamWriter) method
            try {
                XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(out);
                this.marshall(object, xmlStreamWriter);
            } catch (XMLStreamException e) {
                logger.error("could not create XMLStreamWriter from provided Writer!", e);
            }
        }

        @SuppressWarnings("unchecked")
        public <T extends PrideXmlObject> void marshall(T object, XMLStreamWriter xmlStreamWriter) {

            if (object == null) {
                throw new IllegalArgumentException("Cannot marshall a NULL object");
            }

            try {
                // Set JAXB_FRAGMENT_PROPERTY to true for all objects that do not have a @XmlRootElement annotation
                if (!(object instanceof ExperimentCollection)) {
                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
                } else {
                    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
                }

                QName aQName = ModelConstants.getQNameForClass(object.getClass());

                // before marshalling out, wrap in a Custom XMLStreamWriter
                // to fix a JAXB bug: http://java.net/jira/browse/JAXB-614
                // also wrapping in IndentingXMLStreamWriter to generate formatted XML
                IndentingXMLStreamWriter writer = new IndentingXMLStreamWriter(new EscapingXMLStreamWriter(xmlStreamWriter));
                marshaller.marshal(new JAXBElement(aQName, object.getClass(), object), writer);

            } catch (JAXBException e) {
                logger.error("PrideXmlMarshaller.marshall", e);
                throw new IllegalStateException("Error while marshalling object:" + object.toString());
            }

        }

    } // end of inner class

}
