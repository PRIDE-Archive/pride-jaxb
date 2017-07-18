package uk.ac.ebi.pride.jaxb.xml;

import org.junit.Test;
import uk.ac.ebi.pride.jaxb.model.CvParam;
import uk.ac.ebi.pride.jaxb.xml.marshaller.PrideXmlMarshaller;
import uk.ac.ebi.pride.jaxb.xml.marshaller.PrideXmlMarshallerFactory;
import uk.ac.ebi.pride.jaxb.xml.unmarshaller.PrideXmlUnmarshaller;
import uk.ac.ebi.pride.jaxb.xml.unmarshaller.PrideXmlUnmarshallerFactory;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: rcote
 * Date: 12/04/13
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class InvalidCharacterTest {

    @Test
    public void testMarshallInvalidCharacter() throws Exception {

        CvParam param = new CvParam();
        param.setAccession("12345");
        param.setCvLabel("test");
        param.setName("invalid character");
        param.setValue("\u0000This \u0001is \u0002a \u0003test \u009Cstring");

        PrideXmlMarshaller marshaller = PrideXmlMarshallerFactory.getInstance().initializeMarshaller();
        StringWriter out = new StringWriter();
        marshaller.marshall(param, out);

        assertEquals(out.getBuffer().toString(), "<cvParam cvLabel=\"test\" accession=\"12345\" name=\"invalid character\" value=\"�This �is �a �test �string\"></cvParam>");

    }

    @Test
    public void testUnmarshallInvalidCharacter() throws Exception {

        String badXml = "<cvParam cvLabel=\"test\" accession=\"12345\" name=\"invalid character\" value=\"\u0000This \u0001is \u0002a \u0003test \u009Cstring\"></cvParam>";
        PrideXmlUnmarshaller unmarshaller = PrideXmlUnmarshallerFactory.getInstance().initializeUnmarshaller();
        CvParam cvParam = unmarshaller.unmarshal(badXml, CvParam.class);

        assertEquals(cvParam.getValue(), "�This �is �a �test �string");

    }

}
