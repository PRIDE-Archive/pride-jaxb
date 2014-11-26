package uk.ac.ebi.pride.jaxb.xml.marshaller;

import uk.ac.ebi.pride.jaxb.model.PrideXmlObject;

import javax.xml.stream.XMLStreamWriter;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: rcote
 * Date: 13-Aug-2010
 * Time: 14:13:49
 * To change this template use File | Settings | File Templates.
 */
public interface PrideXmlMarshaller {

    public <T extends PrideXmlObject> String marshall(T object);

    public <T extends PrideXmlObject> void marshall(T object, OutputStream os);

    public <T extends PrideXmlObject> void marshall(T object, Writer out);

    public <T extends PrideXmlObject> void marshall(T object, XMLStreamWriter writer);
}
