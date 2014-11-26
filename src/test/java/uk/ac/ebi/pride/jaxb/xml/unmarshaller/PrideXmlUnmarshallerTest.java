package uk.ac.ebi.pride.jaxb.xml.unmarshaller;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pride.jaxb.model.PeptideItem;
import uk.ac.ebi.pride.jaxb.model.Spectrum;
import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;
import uk.ac.ebi.pride.jaxb.xml.extractor.PrideXmlExtractor;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: rwang
 * Date: 04-May-2010
 * Time: 17:07:30
 */
public class PrideXmlUnmarshallerTest {
    private PrideXmlUnmarshaller um = null;
    /** This is a copy from test-pride.xml, if the file was changed, then this string needs to reflect the changes */
    private final static String peptideXmlStr = "\t\t\t<PeptideItem>\n" +
            "\t\t\t\t<Sequence>LFTFHADICTLPDTEK</Sequence>\n" +
            "\t\t\t\t<Start>529</Start>\n" +
            "\t\t\t\t<End>544</End>\n" +
            "\t\t\t\t<SpectrumReference>83</SpectrumReference>\n" +
            "\t\t\t\t<ModificationItem>\n" +
            "\t\t\t\t\t<ModLocation>9</ModLocation>\n" +
            "\t\t\t\t\t<ModAccession>MOD:01214</ModAccession>\n" +
            "\t\t\t\t\t<ModDatabase>MOD</ModDatabase>\n" +
            "\t\t\t\t\t<ModMonoDelta>57.021464</ModMonoDelta>\n" +
            "\t\t\t\t\t<additional>\n" +
            "\t\t\t\t\t\t<cvParam cvLabel=\"MOD\" accession=\"MOD:01214\" name=\"iodoacetamide -site C\" value=\"57.021464\" />\n" +
            "\t\t\t\t\t</additional>\n" +
            "\t\t\t\t</ModificationItem><additional>\n" +
            "\t\t\t\t\t<cvParam cvLabel=\"PRIDE\" accession=\"PRIDE:0000185\" name=\"OMSSA E-value\" value=\"1.67202225564315E-5\" />\n" +
            "\t\t\t\t\t<cvParam cvLabel=\"PRIDE\" accession=\"PRIDE:0000186\" name=\"OMSSA P-value\" value=\"1.49688653146208E-8\" />\n" +
            "\t\t\t\t\t<cvParam cvLabel=\"PRIDE\" accession=\"PRIDE:0000065\" name=\"Upstream flanking sequence\" value=\"Q\" />\n" +
            "\t\t\t\t\t<cvParam cvLabel=\"PRIDE\" accession=\"PRIDE:0000066\" name=\"Downstream flanking sequence\" value=\"K\" />\n" +
            "\t\t\t\t</additional>\n" +
            "\t\t\t</PeptideItem>";
    @Before
    public void prepareTest() {
        um = PrideXmlUnmarshallerFactory.getInstance().initializeUnmarshaller();
    }


    @Test
    public void testUnmarshalXmlSnippet() throws JAXBException {
        PeptideItem peptide = um.unmarshal(peptideXmlStr, PeptideItem.class);
        Spectrum spec = peptide.getSpectrum();
        assertEquals("PeptideItem with empty spectrum: Spectrum ID is not null", spec.getId(), 83);
        assertNull("PeptideItem with empty spectrum: Spectrum Mz Array is null", spec.getMzArrayBinary());
    }

    @Test
    public void testAdaptedUnmarshalXmlSnippet() throws JAXBException, URISyntaxException {
        URL url = PrideXmlUnmarshallerTest.class.getClassLoader().getResource("test-pride.xml");
        if (url == null) {
            throw new IllegalStateException("no file for input found!");
        }
        File inputFile = new File(url.toURI());
        PrideXmlExtractor ext = new PrideXmlExtractor(inputFile);
        SpectrumAdapter specAdapter = new SpectrumAdapter(ext);
        um.setSpectrumAdapter(specAdapter);
        PeptideItem peptide = um.unmarshal(peptideXmlStr, PeptideItem.class);
        Spectrum spec = peptide.getSpectrum();
        assertEquals("PeptideItem with empty spectrum: Spectrum ID is not null", spec.getId(), 83);
        assertNotNull("PeptideItem with empty spectrum: Spectrum Mz Array is null", spec.getMzArrayBinary());
    }
}
