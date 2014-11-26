package uk.ac.ebi.pride.jaxb.xml;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ebi.pride.jaxb.model.*;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Test case for PrideXmlAccessor
 * 
 * User: rwang
 * Date: 30-Apr-2010
 * Time: 14:34:08
 */
public class PrideXmlReaderTest {

    private PrideXmlReader ac = null;

    @Before
    public void prepareTest() throws Exception {
        URL url = PrideXmlReaderTest.class.getClassLoader().getResource("test-pride.xml");
        if (url == null) {
            throw new IllegalStateException("no file for input found!");
        }
        File inputFile = new File(url.toURI());
        ac = new PrideXmlReader(inputFile);
    }

    @Test
    public void testSourceFile() throws Exception {
        assertEquals("Input source file should be test-pride.xml", "test-pride.xml", ac.getSourceFile().getName());
    }

    @Test
    public void testExpCollectionVersion() throws Exception {
        assertEquals("Experiment collection version should be 2.1", "2.1", ac.getVersion());
    }

    @Test
    public void testGetExpAccession() throws Exception {
        String expAcc = ac.getExpAccession();
        assertEquals("Experiment accession", expAcc, "12345");
    }

    @Test
    public void testGetExpTitle() throws Exception {
        String title = ac.getExpTitle();
        assertEquals("Experiment title", title, "aTitle");
    }

    @Test
    public void testGetReferences() throws Exception {
        List<Reference> refs = ac.getReferences();
        assertEquals("Number of references", refs.size(), 2);

        Reference ref1 = refs.get(0);
        assertEquals("Reference 1's reference line: ", ref1.getRefLine(), "Martens L, Van Damme P, Van Damme J, Staes A, Timmerman E, Ghesquiere B, Thomas GR, Vandekerckhove J, Gevaert K (2005), ?The human platelet proteome mapped by peptide-centric proteomics: a functional protein profile?, Proteomics, 5, 3193-3204.");
        Param params = ref1.getAdditional();
        assertEquals("Reference 1's PubMed accession: ", params.getCvParam().get(0).getAccession(), "16038019");
        assertEquals(params.getCvParam().get(0).getCvLabel(), "PubMed");

        Reference ref2 = refs.get(1);
        assertEquals("Reference 2's reference line: ", ref2.getRefLine(), "Gevaert K, Ghesquiere B, Staes A, Martens L, Van Damme J, Thomas GR, Vandekerckhove J (2004), ?Reversible labeling of cysteine-containing peptides allows their chromatographic isolation for non-gel proteome studies?, Proteomics 4, 897-908.");
        params = ref2.getAdditional();
        assertEquals("Reference 2's PubMed accession: ", params.getCvParam().get(0).getAccession(), "15048972");
        assertEquals(params.getCvParam().get(0).getCvLabel(), "PubMed");       
    }

    @Test
    public void testGetExpShortLabel() throws Exception {
        String sl = ac.getExpShortLabel();
        assertEquals("Experiment short label", sl, "aShortLabel");
    }

    @Test
    public void testGetProtocol() throws Exception {
        Protocol prot = ac.getProtocol();
        assertEquals("Protocol Name", prot.getProtocolName(), "In Gel Protein Digestion");
        assertEquals("Number of protocol steps", prot.getProtocolSteps().getStepDescription().size(), 3);
        assertEquals("Protocol step description accession", prot.getProtocolSteps().getStepDescription().get(2).getCvParam().get(0).getAccession(), "PRIDE:0000160");
        assertEquals("Protocol step description value", prot.getProtocolSteps().getStepDescription().get(2).getCvParam().get(0).getValue(), "Trypsin");
    }

    @Test
    public void testGetAdditionalParams() throws Exception {
        Param additionalParam = ac.getAdditionalParams();
        assertEquals("Number of additional parameters", additionalParam.getCvParam().size(), 2);
        assertEquals("Additional parameters accession", additionalParam.getCvParam().get(0).getAccession(), "PRIDE:0000175");
        assertEquals("Additional parameters name", additionalParam.getCvParam().get(0).getValue(), "PRIDE Converter v2.0-SNAPSHOT");
    }

    @Test
    public void testGetCvLookups() throws Exception {
        List<CvLookup> cls = ac.getCvLookups();
        assertEquals("Cv lookup ", cls.get(0).getAddress(), "http://psidev.sourceforge.net/ontology/");
    }

    @Test
    public void testGetDescription() throws Exception {
        Description desc = ac.getDescription();

        Admin admin = desc.getAdmin();
        // sample name
        assertEquals("Sample name", admin.getSampleName(), "Example Sample Set");
        // sample desc
        assertEquals("Sample description", admin.getSampleDescription().getCvParam().get(0).getName(), "lung");
        // contact
        assertEquals("Contact", admin.getContact().get(0).getContactInfo(), "florian@ebi.ac.uk");

        Instrument instrument = desc.getInstrument();
        // instrument name
        assertEquals("Instrument name", instrument.getInstrumentName(), "ThermoFinnigan LCQ Duo");
        // source
        assertEquals("Instrument source", instrument.getSource().getCvParam().get(0).getName(), "Electrospray Ionization");
        // analyzer
        assertEquals("Instrument analyer", instrument.getAnalyzerList().getAnalyzer().get(0).getCvParam().get(0).getName(), "Ion Trap");
        // detector
        assertEquals("Instrument detector", instrument.getDetector().getCvParam().get(0).getName(), "Electron Multiplier Tube");

    }

    @Test
    public void testGetAdmin() throws Exception {
        Admin admin = ac.getAdmin();
        // sample name
        assertEquals("Sample name", admin.getSampleName(), "Example Sample Set");
        // sample desc
        assertEquals("Sample description", admin.getSampleDescription().getCvParam().get(0).getName(), "lung");
        // contact
        assertEquals("Contact", admin.getContact().get(0).getContactInfo(), "florian@ebi.ac.uk");
    }

    @Test
    public void testGetInstrument() throws Exception {
        Instrument instrument = ac.getInstrument();
        // instrument name
        assertEquals("Instrument name", instrument.getInstrumentName(), "ThermoFinnigan LCQ Duo");
        // source
        assertEquals("Instrument source", instrument.getSource().getCvParam().get(0).getName(), "Electrospray Ionization");
        // analyzer
        assertEquals("Instrument analyer", instrument.getAnalyzerList().getAnalyzer().get(0).getCvParam().get(0).getName(), "Ion Trap");
        // detector
        assertEquals("Instrument detector", instrument.getDetector().getCvParam().get(0).getName(), "Electron Multiplier Tube");
    }

    @Test
    public void testGetDataProcessing() throws Exception {
        DataProcessing dataProc = ac.getDataProcessing();
        // software name
        assertEquals("Software name", dataProc.getSoftware().getName(), "Xcalibur");
        // software version
        assertEquals("Software version", dataProc.getSoftware().getVersion(), "1.2 SP1");
        // processing method
        assertEquals("Processing method", dataProc.getProcessingMethod().getCvParam().get(0).getAccession(), "PSI:1000035");
    }

    @Test
    public void testGetSpectrumIds() throws Exception {
        List<String> specIds = ac.getSpectrumIds();
        // number of spectrum
        assertEquals("Number of spectrum", specIds.size(), 100);
        // spectrum id
        assertEquals("Spectrum Id: 1", specIds.contains("1"), true);
        // spectrum id
        assertEquals("Spectrum Id: 100", specIds.contains("100"), true);
    }

    @Test
    public void testGetSpectrumById() throws Exception {
        Spectrum spec = ac.getSpectrumById("1");
        // spectrum settings
        assertEquals("Spectrum settings instrument mz range start", spec.getSpectrumDesc().getSpectrumSettings().getSpectrumInstrument().getMzRangeStart(), new Float(221.0));
        // precursor (ionSelection, comments)
        assertEquals("Precursor list", spec.getSpectrumDesc().getPrecursorList().getPrecursor().get(0).getMsLevel(), 1);
        assertEquals("Ion selection", spec.getSpectrumDesc().getPrecursorList().getPrecursor().get(0).getIonSelection().getCvParam().get(0).getName(), "ChargeState");
        // mz array binary
        assertEquals("Mz array precision", spec.getMzArrayBinary().getData().getPrecision(), "64");
        assertEquals("Mz array endian", spec.getMzArrayBinary().getData().getEndian(), "little");
    }

    @Test
    public void testIsIdentifiedSpectrum() throws Exception {
        boolean exists = ac.isIdentifiedSpectrum("1");
        assertTrue("Spectrum 1 should be identified", exists);

        exists = ac.isIdentifiedSpectrum("2345");
        assertTrue("Spectrum 2345 should not be identified", !exists);
    }

    @Test
    public void testPrecursorSpectrumRef() throws Exception {
        Spectrum spec = ac.getSpectrumById("1");
        Spectrum precursorSpec = spec.getSpectrumDesc().getPrecursorList().getPrecursor().get(0).getSpectrum();
        assertNotNull("Precursor spectrum should not be null", precursorSpec);
        assertEquals("Precursor reference spectrum", precursorSpec.getSpectrumDesc().getSpectrumSettings().getSpectrumInstrument().getMzRangeStart(), new Float(321.0));

        spec = ac.getSpectrumById("2");
        precursorSpec = spec.getSpectrumDesc().getPrecursorList().getPrecursor().get(0).getSpectrum();
        assertNotNull("Precursor's spectrum reference should be null", precursorSpec);
        assertEquals("Precursor's spectrum reference", precursorSpec.getId(), 0);
    }

    public void testGetIdentAccs() throws Exception {
        assertTrue("One identification should exists", ac.getIdentIds().contains("2"));
        assertTrue("Number of identification is not correct", ac.getIdentIds().size() == 3);
    }

    public void testGetIdentByAcc() throws Exception {
        Identification ident = ac.getIdentById("0");
        // database
        assertEquals("Gel Free database", ident.getDatabase(), "swissprot");
        // peptide item sequence
        assertEquals("Peptide item sequence", ident.getPeptideItem().get(0).getSequence(), "LFTFHADICTLPDTEK");
        // peptide item modification
        assertEquals("Peptide modification", ident.getPeptideItem().get(0).getModificationItem().get(0).getModAccession(), "MOD:01214");
        // fragment ion
        assertEquals("Fragmented ions", ident.getPeptideItem().get(0).getFragmentIon().get(0).getCvParam().get(0).getName(), "product ion charge");
    }

    @Test
    public void testGetGelFreeIdentAccs() throws Exception {
        assertTrue("One Gel free identification should exists", ac.getGelFreeIdentIds().contains("0"));
        assertTrue("No Gel free identifications", ac.getGelFreeIdentIds().size()==1);
    }

    @Test
    public void testGetGelFreeIdentByAcc() throws Exception {
        GelFreeIdentification ident = ac.getGelFreeIdentById("0");
        // database
        assertEquals("Gel Free database", ident.getDatabase(), "swissprot");
        // peptide item sequence
        assertEquals("Peptide item sequence", ident.getPeptideItem().get(0).getSequence(), "LFTFHADICTLPDTEK");
        // peptide item modification
        assertEquals("Peptide modification", ident.getPeptideItem().get(0).getModificationItem().get(0).getModAccession(), "MOD:01214");
        // fragment ion
        assertEquals("Fragmented ions", ident.getPeptideItem().get(0).getFragmentIon().get(0).getCvParam().get(0).getName(), "product ion charge");
    }

    @Test
    public void testGetTwoDimIdentAccs() throws Exception {
        assertTrue("One two dimensional identification should exists", ac.getTwoDimIdentIds().contains("1"));
        // number of two dimensional identifications
        assertTrue("No two dimensional identifications", ac.getTwoDimIdentIds().size()==2);

    }

    @Test
    public void testGetTwoDimIdentByAcc() throws Exception {
        TwoDimensionalIdentification ident = ac.getTwoDimIdentById("1");
        // database
        assertEquals("Tow dim database", ident.getDatabase(), "IPI mouse");
        // peptide item sequence
        assertEquals("Peptide item sequence", ident.getPeptideItem().get(0).getSequence(), "GPAGEPMGPEAGSK");
        // peptide item modification
        assertEquals("Peptide modification", ident.getPeptideItem().get(0).getModificationItem().get(0).getModAccession(), "0123");
        // Gel location
        assertTrue("Gel location x coordinate", ident.getGelLocation().getXCoordinate() == 3.141593E0);
        // sequence coverage
        assertEquals("Sequence coverage", ident.getSequenceCoverage(), new Double(1.0E0));
    }

    @Test
    public void testMultipleTwoDimIdent() throws Exception {
        TwoDimensionalIdentification ident1 = ac.getTwoDimIdentById("1");
        TwoDimensionalIdentification ident2 = ac.getTwoDimIdentById("2");

        // check the protein accessions are the same
        assertEquals("Protein accessions should be the same", ident1.getAccession(), ident2.getAccession());

        // check the protein accession version
        assertEquals("Protein one's accession version should be 1", ident1.getAccessionVersion(), "1");

        // check the protein accession version
        assertEquals("Protein two's accession version should be 2", ident2.getAccessionVersion(), "2");
    }

    @Test
    public void testMsLevel() throws Exception {
        assertEquals(0, ac.getSpectrumMsLevel("2"));
    }
}
