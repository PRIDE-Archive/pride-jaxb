package uk.ac.ebi.pride.jaxb.xml.extractor;

import org.apache.log4j.Logger;
import psidev.psi.tools.xxindex.index.IndexElement;
import uk.ac.ebi.pride.jaxb.xml.PrideXmlXpath;
import uk.ac.ebi.pride.jaxb.xml.xxindex.XmlIndexer;
import uk.ac.ebi.pride.jaxb.xml.xxindex.XmlIndexerFactory;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PrideXmlExtractor provides a list of methods to retrieve
 * Pride xml snippets.
 * <p/>
 * Note: this implementation assumes there is only one experiment per file.
 * <p/>
 * User: rwang
 * Date: 30-Apr-2010
 * Time: 09:10:56
 */
public class PrideXmlExtractor {

    private static final Logger logger = Logger.getLogger(PrideXmlExtractor.class.getName());

    /**
     * pattern to match ids
     */
    private static final Pattern ID_PATTERN = Pattern.compile("\\sid\\s*=\\s*['\"]([^'\"]*)['\"]", Pattern.CASE_INSENSITIVE);
    /**
     * pattern to match version number
     */
    private static final Pattern VERSION_PATTERN = Pattern.compile("\\sversion\\s*=\\s*['\"]([^'\"]*)['\"]", Pattern.CASE_INSENSITIVE);
    /**
     * pattern to match content enclosed by a pair of xml tags
     */
    private static final Pattern TAG_PATTERN = Pattern.compile("<[^>]+>\\s*([^<>]+)\\s*</[^>]+>", Pattern.CASE_INSENSITIVE);
    /**
     * pattern to match ms level
     */
    private static final Pattern MS_LEVEL_PATTERN = Pattern.compile("\\smslevel\\s*=\\s*['\"]([^'\"]*)['\"]", Pattern.CASE_INSENSITIVE);
    /**
     * This is used to access accessions or ids
     */
    private static final int XML_CHAR_INCREMENT = 300;

    /**
     * These maps provide quick mapping from id/accession to their byte range in the input file
     */
    private Map<String, IndexElement> spectrumIdMap = null;
    private Map<String, IndexElement> gelFreeAccMap = null;
    private Map<String, IndexElement> twoDimAccMap = null;
    private Map<String, List<IndexElement>> identToPeptideMap = null;
    private List<String> identifiedSpectrumList = null;

    /**
     * Pride xml extractor
     */
    private XmlIndexer indexer = null;

    /**
     * Input PRIDE XML file
     */
    private File prideXmlFile = null;

    /**
     * unique id for identifications
     */
    private int identificationId = 0;

    public PrideXmlExtractor(File xml) {
        if (xml == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        } else if (!xml.exists()) {
            throw new IllegalArgumentException("Xml file to be indexed does not exist: " + xml.getAbsolutePath());
        }

        this.prideXmlFile = xml;
        // create indexer
        indexer = XmlIndexerFactory.getInstance().buildIndex(prideXmlFile, PrideXmlXpath.getXpaths());
        // populate all the mappings/caches
        initializeCaches();
    }

    public File getSourceFile() {
        return prideXmlFile;
    }

    private void initializeCaches() {
        // init spectrum id map
        spectrumIdMap = initCacheMap(PrideXmlXpath.MZDATA_SPECTRUM.getXpath(), ID_PATTERN);
        // init gel free accession map
        gelFreeAccMap = initIdentificationCacheMap(PrideXmlXpath.GELFREE.getXpath());
        // init two dimensional accession map
        twoDimAccMap = initIdentificationCacheMap(PrideXmlXpath.TWOD.getXpath());
        // init peptide map
        identToPeptideMap = initPeptideCacheMap();
        // init identified spectrum list
        identifiedSpectrumList = initIdentifiedSpectrumList();
    }

    private Map<String, IndexElement> initCacheMap(String xpath, Pattern idPattern) {
        // 1. create a empty id map
        Map<String, IndexElement> idMap = new LinkedHashMap<String, IndexElement>();
        // 2. get index elements from xpath
        Collection<IndexElement> indexElements = indexer.getIndexElements(xpath);

        for (IndexElement indexElement : indexElements) {
            // get id from index element using pattern
            String id = getIDByPattern(indexElement, idPattern, true);
            if (idMap.containsKey(id)) {
                logger.error("Ambiguous ID Exception: " + xpath + " \nID: " + id);
            } else {
                idMap.put(id, indexElement);
            }
        }
        return idMap;
    }

    private Map<String, IndexElement> initIdentificationCacheMap(String xpath) {
        // 1. create a empty id map
        Map<String, IndexElement> idMap = new LinkedHashMap<String, IndexElement>();
        // 2. get index elements from xpath
        Collection<IndexElement> indexElements = indexer.getIndexElements(xpath);
        // 3. generated Id index

        for (IndexElement indexElement : indexElements) {
            // get id from index element using pattern
            if (idMap.containsKey(identificationId + "")) {
                logger.error("Ambiguous ID Exception: " + xpath + " \nID: " + identificationId);
            } else {
                idMap.put(identificationId + "", indexElement);
            }
            identificationId++;
        }

        return idMap;
    }

    private Map<String, List<IndexElement>> initPeptideCacheMap() {
        // 1. create a empty identification to peptide mapping map
        Map<String, List<IndexElement>> identPeptideMap = new LinkedHashMap<String, List<IndexElement>>();
        // 2. get all gel free peptide index element
        Collection<IndexElement> gelFreePeptideElements = indexer.getIndexElements(PrideXmlXpath.GELFREE_PEPTIDE.getXpath());
        // 3. iterate over each gel free peptide index element
        for (IndexElement gelFreePeptideElement : gelFreePeptideElements) {
            String identId = searchForId(gelFreePeptideElement, gelFreeAccMap);
            addIdentPeptide(identId, gelFreePeptideElement, identPeptideMap);
        }
        // 4. get all two dimensional peptide index element
        Collection<IndexElement> twoDimPeptideElements = indexer.getIndexElements(PrideXmlXpath.TWOD_PEPTIDE.getXpath());
        // 5. iterate over each peptide index element
        for (IndexElement twoDimPeptideElement : twoDimPeptideElements) {
            String identId = searchForId(twoDimPeptideElement, twoDimAccMap);
            addIdentPeptide(identId, twoDimPeptideElement, identPeptideMap);
        }

        return identPeptideMap;
    }

    private List<String> initIdentifiedSpectrumList() {
        // 1. create an empty list for storing identified spectrum ids.
        List<String> identifiedList = new ArrayList<String>();

        // 2. get all gel free peptide spectrum references
        Collection<IndexElement> gelFreeSpectrumRefElements = indexer.getIndexElements(PrideXmlXpath.GELFREE_PEPTIDE_SPEC_REF.getXpath());

        // 3. iterate over each spectrum reference
        for (IndexElement spectrumRefElement : gelFreeSpectrumRefElements) {
            // get id from index element using pattern
            String id = getIDByPattern(spectrumRefElement, TAG_PATTERN, false);
            identifiedList.add(id);
        }

        // 4. get all two dimentional peptide spectrum references
        Collection<IndexElement> twoDimSpectrumRefElements = indexer.getIndexElements(PrideXmlXpath.TWOD_PEPTIDE_SPEC_REF.getXpath());

        // 5. iterate over each spectrum reference
        for (IndexElement twoDimSpectrumRefElement : twoDimSpectrumRefElements) {
            String id = getIDByPattern(twoDimSpectrumRefElement, TAG_PATTERN, false);
            identifiedList.add(id);
        }

        return identifiedList;
    }

    /**
     * Search through a map of id-IndexElement mapping using the a element
     * find the id when the index element fall into the same range.
     *
     * @param element  a index element
     * @param mappings id-indexelement mappings.
     * @return String   id
     */
    private String searchForId(IndexElement element, Map<String, IndexElement> mappings) {
        String id = null;
        for (Map.Entry<String, IndexElement> entry : mappings.entrySet()) {
            IndexElement identElement = entry.getValue();
            if (identElement.getStart() <= element.getStart() && identElement.getStop() >= element.getStop()) {
                id = entry.getKey();
                break;
            }
        }
        return id;
    }

    private void addIdentPeptide(String identId, IndexElement peptideElement, Map<String, List<IndexElement>> identPeptideMap) {
        List<IndexElement> elements = identPeptideMap.get(identId);
        if (elements == null) {
            elements = new ArrayList<IndexElement>();
            identPeptideMap.put(identId, elements);
        }
        elements.add(peptideElement);
    }

    public String getExpCollectionVersionString() {
        Collection<IndexElement> indexElements = indexer.getIndexElements(PrideXmlXpath.EXP_COLLECTION.getXpath());
        String version = null;
        for (IndexElement indexElement : indexElements) {
            version = getIDByPattern(indexElement, VERSION_PATTERN, true);
            break;
        }
        return version;
    }

    public String getExpAccXmlString() {
        return getFirstXmlString(PrideXmlXpath.EXP_ACC.getXpath());
    }

    public String getExpTitleXmlString() {
        return getFirstXmlString(PrideXmlXpath.EXP_TITLE.getXpath());
    }

    public List<String> getReferenceXmlStrings() {
        return indexer.getXmlStringList(PrideXmlXpath.EXP_REF.getXpath());
    }

    public String getExpShortLabelXmlString() {
        return getFirstXmlString(PrideXmlXpath.EXP_SHORTLABEL.getXpath());
    }

    public String getProtocolXmlString() {
        return getFirstXmlString(PrideXmlXpath.EXP_PROTOCOL.getXpath());
    }

    public String getAdditionalParamXmlString() {
        return getFirstXmlString(PrideXmlXpath.EXP_ADDITIONAL.getXpath());
    }

    public List<String> getCvLookupXmlStrings() {
        return indexer.getXmlStringList(PrideXmlXpath.MZDATA_CVLOOKUP.getXpath());
    }

    public String getDescriptionXmlString() {
        return getFirstXmlString(PrideXmlXpath.MZDATA_DESC.getXpath());
    }

    public String getAdminXmlString() {
        return getFirstXmlString(PrideXmlXpath.MZDATA_DESC_AMDIN.getXpath());
    }

    public String getInstrumentXmlString() {
        return getFirstXmlString(PrideXmlXpath.MZDATA_DESC_INSTRUMENT.getXpath());
    }

    public String getDataProcessingXmlString() {
        return getFirstXmlString(PrideXmlXpath.MZDATA_DESC_DATAPROCESSING.getXpath());
    }

    public List<String> getSpectrumIds() {
        return new ArrayList<String>(spectrumIdMap.keySet());
    }

    public String getSpectrumXmlString(String id) {
        String result = null;

        if (spectrumIdMap != null && spectrumIdMap.containsKey(id)) {
            result = indexer.getXmlByIndexElement(spectrumIdMap.get(id));
        }

        return result;
    }

    /**
     * Get all the spectrum id, index element map
     *
     * @return Map<String, IndexElement>   spectrum id to index element map
     */
    public Map<String, IndexElement> getSpectrumIndices() {
        return new HashMap<String, IndexElement>(spectrumIdMap);
    }

    /**
     * Get the ms level of a given spectrum
     *
     * @param id spectrum id
     * @return int ms level
     */
    public int getSpectrumMsLevel(String id) {
        int msLevel = -1;

        if (spectrumIdMap != null && spectrumIdMap.containsKey(id)) {
            String msLevelStr = getIDByPattern(spectrumIdMap.get(id), MS_LEVEL_PATTERN, false);
            msLevel = Integer.parseInt(msLevelStr);
        }

        return msLevel;
    }

    /**
     * Check whether the spectrum has been used to identify a peptide
     *
     * @param id spectrum id
     * @return boolean true if it is an identified spectrum.
     */
    public boolean isIdentifiedSpectrum(String id) {
        return identifiedSpectrumList.contains(id);
    }

    public List<String> getIdentIds() {
        List<String> ids = new ArrayList<String>(gelFreeAccMap.keySet());
        ids.addAll(twoDimAccMap.keySet());
        return ids;
    }

    public String getIdentXmlString(String id) {
        String result = getGelFreeIdentXmlString(id);
        if (result == null) {
            result = getTwoDimIdentXmlString(id);
        }

        return result;
    }

    public boolean hasGelFreeIdentId(String id) {
        return gelFreeAccMap.containsKey(id);
    }

    public List<String> getGelFreeIdentIds() {
        return new ArrayList<String>(gelFreeAccMap.keySet());
    }

    public String getGelFreeIdentXmlString(String id) {
        String result = null;

        if (gelFreeAccMap != null && gelFreeAccMap.containsKey(id)) {
            result = indexer.getXmlByIndexElement(gelFreeAccMap.get(id));
        }

        return result;
    }

    public boolean hasTwoDimIdentId(String id) {
        return twoDimAccMap.containsKey(id);
    }

    public List<String> getTwoDimIdentIds() {
        return new ArrayList<String>(twoDimAccMap.keySet());
    }

    public String getTwoDimIdentXmlString(String id) {
        String result = null;

        if (twoDimAccMap != null && twoDimAccMap.containsKey(id)) {
            result = indexer.getXmlByIndexElement(twoDimAccMap.get(id));
        }

        return result;
    }

    /**
     * Get the number of peptides.
     *
     * @return int  the count of entries
     */
    public int getNumberOfPeptides() {
        int cnt = 0;
        // gel free
        Collection<IndexElement> gelFreePeptides = indexer.getIndexElements(PrideXmlXpath.GELFREE_PEPTIDE.getXpath());
        cnt += gelFreePeptides == null ? 0 : gelFreePeptides.size();
        // two dim
        Collection<IndexElement> twoDimPeptides = indexer.getIndexElements(PrideXmlXpath.TWOD_PEPTIDE.getXpath());
        cnt += twoDimPeptides == null ? 0 : twoDimPeptides.size();

        return cnt;
    }

    /**
     * Get number of peptides for an identification
     *
     * @param identId identification id
     * @return int  number of peptide
     */
    public int getNumberOfPeptides(String identId) {
        int cnt = 0;

        List<IndexElement> elements = identToPeptideMap.get(identId);
        if (elements != null) {
            cnt = elements.size();
        }

        return cnt;
    }

    /**
     * Get the corresponding xml string for a peptide.
     *
     * @param identId identification id.
     * @param index   peptide index within the identification
     * @return String   xml string
     */
    public String getPeptideXmlString(String identId, int index) {
        String xml = null;

        List<IndexElement> elements = identToPeptideMap.get(identId);
        if (elements != null && index >= 0 && index < elements.size()) {
            xml = indexer.getXmlByIndexElement(elements.get(index));
        }

        return xml;
    }

    /**
     * Get all peptide xml strings within an identifiation.
     *
     * @param identId identificatio id
     * @return List<String> a list of peptide xml strings
     */
    public List<String> getPeptideXmlStrings(String identId) {
        List<String> peptides = new ArrayList<String>();
        List<IndexElement> elements = identToPeptideMap.get(identId);
        for (IndexElement element : elements) {
            peptides.add(indexer.getXmlByIndexElement(element));
        }
        return peptides;
    }

    /**
     * @param xpath
     * @return
     */
    public Iterator<String> getPrideXmlEntries(String xpath) {
        return indexer.getXmlStringIterator(xpath);
    }


    /**
     * Get the XML of the first element matched by xpath and within the experiment range.
     *
     * @param xpath xpath to find the element.
     * @return String   XML string matched.
     */
    private String getFirstXmlString(String xpath) {
        String xml = null;

        Iterator<String> xmlStrs = indexer.getXmlStringIterator(xpath);

        if (xmlStrs != null && xmlStrs.hasNext()) {
            xml = xmlStrs.next();
        }
        return xml;
    }

    /**
     * Find the id from xml string based indexElement's range.
     *
     * @param indexElement index element.
     * @param pattern      This pattern should only match once.
     * @return String   id or accession.
     */
    private String getIDByPattern(IndexElement indexElement, Pattern pattern, boolean quickMatch) {
        String id = null;

        // first get the XML string (only the first bit) from which to extract the ID
        long start = indexElement.getStart();
        long stop = quickMatch ? start + XML_CHAR_INCREMENT : indexElement.getStop();
        String xml = indexer.getXmlSnippet(start, stop);
        // then apply the regex pattern to find the ID
        Matcher m = pattern.matcher(xml);
        if (m.find()) {
            id = m.group(1);
        }

        return id;
    }
}
