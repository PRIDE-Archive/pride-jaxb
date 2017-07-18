package uk.ac.ebi.pride.jaxb.xml;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.tools.xxindex.index.IndexElement;
import uk.ac.ebi.pride.jaxb.model.*;
import uk.ac.ebi.pride.jaxb.utils.FileUtils;
import uk.ac.ebi.pride.jaxb.xml.adapter.SpectrumAdapter;
import uk.ac.ebi.pride.jaxb.xml.extractor.PrideXmlExtractor;
import uk.ac.ebi.pride.jaxb.xml.unmarshaller.PrideXmlUnmarshaller;
import uk.ac.ebi.pride.jaxb.xml.unmarshaller.PrideXmlUnmarshallerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: rwang
 * Date: 30-Apr-2010
 * Time: 12:13:31
 */
public class PrideXmlReader {

    private static final Logger logger = LoggerFactory.getLogger(PrideXmlReader.class.getName());
    /**
     * pattern to match the content of a element
     */
    private static final Pattern ELEMENT_CONTENT_PATTERN = Pattern.compile("\\s*\\<[^\\>]+\\>([^\\<]+)\\<\\/[^\\>]+\\>\\s*");
    /**
     * internal unmashaller
     */
    private PrideXmlUnmarshaller unmarshaller = null;
    /**
     * internal xml extractor
     */
    private PrideXmlExtractor extractor = null;

    /**
     * This constructor should be called to access Pride XML on the Internet.
     *
     * @param url URL of the PRIDE xml file.
     */
    public PrideXmlReader(URL url) {
        this(FileUtils.getFileFromURL(url));
    }

    public PrideXmlReader(File xml) {
        if (xml == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        } else if (!xml.exists()) {
            throw new IllegalArgumentException("Xml file to be indexed does not exist: " + xml.getAbsolutePath());
        }

        // create extractor
        this.extractor = new PrideXmlExtractor(xml);

        // create unmarshaller
        this.unmarshaller = PrideXmlUnmarshallerFactory.getInstance().initializeUnmarshaller();

        // add spectrum adapter
        unmarshaller.setSpectrumAdapter(new SpectrumAdapter(extractor));
    }

    /**
     * Get the original input xml source file.
     *
     * @return File input file object
     */
    public File getSourceFile() {
        return extractor.getSourceFile();
    }

    public String getVersion() {
        return extractor.getExpCollectionVersionString();
    }

    public String getExpAccession() {
        String expAccXml = extractor.getExpAccXmlString();
        return findByPattern(expAccXml, ELEMENT_CONTENT_PATTERN, 1);
    }

    public String getExpTitle() {
        String titleXml = extractor.getExpTitleXmlString();
        return findByPattern(titleXml, ELEMENT_CONTENT_PATTERN, 1);
    }

    @SuppressWarnings("unchecked")
    public List<Reference> getReferences() {
        List<Reference> refs = new ArrayList<>();

        List<String> referenceXmlStrings = extractor.getReferenceXmlStrings();
        for (String refXml : referenceXmlStrings) {
            refs.add(this.<Reference>unmarshalXmlToPrideObject(refXml, PrideXmlXpath.EXP_REF.getClassType()));
        }

        return refs;
    }

    public String getExpShortLabel() {
        String shorLabelXml = extractor.getExpShortLabelXmlString();
        return findByPattern(shorLabelXml, ELEMENT_CONTENT_PATTERN, 1);
    }

    @SuppressWarnings("unchecked")
    public Protocol getProtocol() {
        return this.<Protocol>unmarshalXmlToPrideObject(extractor.getProtocolXmlString(),
                PrideXmlXpath.EXP_PROTOCOL.getClassType());
    }

    @SuppressWarnings("unchecked")
    public Param getAdditionalParams() {
        return this.<Param>unmarshalXmlToPrideObject(extractor.getAdditionalParamXmlString(),
                PrideXmlXpath.EXP_ADDITIONAL.getClassType());
    }

    @SuppressWarnings("unchecked")
    public List<CvLookup> getCvLookups() {
        List<CvLookup> cvLookups = new ArrayList<>();
        List<String> cvLookupStrings = extractor.getCvLookupXmlStrings();
        for (String cvLookupString : cvLookupStrings) {
            cvLookups.add(this.<CvLookup>unmarshalXmlToPrideObject(cvLookupString,
                    PrideXmlXpath.MZDATA_CVLOOKUP.getClassType()));
        }
        return cvLookups;
    }

    @SuppressWarnings("unchecked")
    public Description getDescription() {
        return this.<Description>unmarshalXmlToPrideObject(extractor.getDescriptionXmlString(),
                PrideXmlXpath.MZDATA_DESC.getClassType());
    }

    @SuppressWarnings("unchecked")
    public Admin getAdmin() {
        return this.<Admin>unmarshalXmlToPrideObject(extractor.getAdminXmlString(),
                PrideXmlXpath.MZDATA_DESC_AMDIN.getClassType());
    }

    @SuppressWarnings("unchecked")
    public Instrument getInstrument() {
        return this.<Instrument>unmarshalXmlToPrideObject(extractor.getInstrumentXmlString(),
                PrideXmlXpath.MZDATA_DESC_INSTRUMENT.getClassType());
    }

    @SuppressWarnings("unchecked")
    public DataProcessing getDataProcessing() {
        return this.<DataProcessing>unmarshalXmlToPrideObject(extractor.getDataProcessingXmlString(),
                PrideXmlXpath.MZDATA_DESC_DATAPROCESSING.getClassType());
    }

    public List<String> getSpectrumIds() {
        return extractor.getSpectrumIds();
    }

    @SuppressWarnings("unchecked")
    public Spectrum getSpectrumById(String id) {
        return this.<Spectrum>unmarshalXmlToPrideObject(extractor.getSpectrumXmlString(id),
                PrideXmlXpath.MZDATA_SPECTRUM.getClassType());
    }

    public int getSpectrumMsLevel(String id) {
        return extractor.getSpectrumMsLevel(id);
    }

    public Map<String, IndexElement> getSpectrumIndices() {
        return extractor.getSpectrumIndices();
    }

    public boolean isIdentifiedSpectrum(String id) {
        return extractor.isIdentifiedSpectrum(id);
    }

    public List<String> getIdentIds() {
        return extractor.getIdentIds();
    }

    public Identification getIdentById(String id) {
        Identification ident = null;

        if (extractor.hasGelFreeIdentId(id)) {
            ident = getGelFreeIdentById(id);
        } else if (extractor.hasTwoDimIdentId(id)) {
            ident = getTwoDimIdentById(id);
        }

        return ident;
    }

    public List<String> getGelFreeIdentIds() {
        return extractor.getGelFreeIdentIds();
    }

    @SuppressWarnings("unchecked")
    public GelFreeIdentification getGelFreeIdentById(String id) {
        GelFreeIdentification ident = this.<GelFreeIdentification>unmarshalXmlToPrideObject(extractor.getGelFreeIdentXmlString(id),
                PrideXmlXpath.GELFREE.getClassType());
        if (ident != null) {
            ident.setId(id);
        }

        return ident;
    }

    public List<String> getTwoDimIdentIds() {
        return extractor.getTwoDimIdentIds();
    }

    @SuppressWarnings("unchecked")
    public TwoDimensionalIdentification getTwoDimIdentById(String id) {
        TwoDimensionalIdentification ident = this.<TwoDimensionalIdentification>unmarshalXmlToPrideObject(extractor.getTwoDimIdentXmlString(id),
                PrideXmlXpath.TWOD.getClassType());
        if (ident != null) {
            ident.setId(id);
        }

        return ident;
    }

    public int getNumberOfPeptides() {
        return extractor.getNumberOfPeptides();
    }

    public int getNumberOfPeptides(String identId) {
        return extractor.getNumberOfPeptides(identId);
    }

    public PeptideItem getPeptide(String identId, int index) {
        PeptideItem peptide = null;
        String xml = extractor.getPeptideXmlString(identId, index);
        if (xml != null) {
            peptide = unmarshalXmlToPrideObject(xml, PeptideItem.class);
        }
        return peptide;
    }

    public List<PeptideItem> getPeptides(String identId) {
        List<PeptideItem> peptides = new ArrayList<>();
        List<String> xmls = extractor.getPeptideXmlStrings(identId);
        if (xmls != null) {
            for (String xml : xmls) {
                peptides.add(unmarshalXmlToPrideObject(xml, PeptideItem.class));
            }
        }
        return peptides;
    }

    private <T extends PrideXmlObject> T unmarshalXmlToPrideObject(String xml, Class<T> classType) {
        try {
            return unmarshaller.unmarshal(xml, classType);
        } catch (JAXBException e) {
            logger.error("PrideXmlAccessor unmarshal xml to Pride Object" + classType, e);
            throw new IllegalStateException("Could not convert unmarshal xml to " + classType);
        }
    }

    private String findByPattern(String str, Pattern pattern, int matchIndex) {
        String result = null;

        if (str != null) {
            Matcher m = pattern.matcher(str);
            if (m.find()) {
                result = m.group(matchIndex);
            }
        }

        if (result != null) {
            result = result.trim();
        }

        return result;
    }
}
