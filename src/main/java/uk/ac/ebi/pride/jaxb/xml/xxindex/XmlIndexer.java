package uk.ac.ebi.pride.jaxb.xml.xxindex;

import psidev.psi.tools.xxindex.index.IndexElement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * PrideXMLIndexer should be able to index the given Pride XML file and
 * provides methods to access different sections of the XML file.
 *
 * Note: no IOException is thrown, this is to keep the consistency, xxindex
 * catches some of the IOExceptions.
 *
 * @author Florian Reisinger
 * @author rwang
 * @since 0.1
 */
public interface XmlIndexer {

    /**
     * Get an iterator of xml strings matched by the input xpath.
     * @param xpath input xpath.
     * @return Iterator<String> an iterator of xml strings.
     */
    Iterator<String> getXmlStringIterator(String xpath);


    /**
     * Get an list of xml strings, this should only used to limited
     * selection of elements.
     * @param xpath input xpath.
     * @return List<String> an list of xml strings.
     */
    List<String> getXmlStringList(String xpath);

    /**
     * Get the number of elements matched by the input xpath.
     * @param xpath xpath expression.
     * @return int  number of elements.
     */
    int getCount(String xpath);

    /**
     * Get an collection of IndexElements based on the input xpath.
     * @param xpath xpath expression.
     * @return Collection<IndexElement> an collection of IndexElements.
     */
    Collection<IndexElement> getIndexElements(String xpath);

    /**
     * Get an iterator of xml string matched by the xpath within the range of start and stop.
     * @param xpath input xpath.
     * @param start start position of the file.
     * @param stop  stop position of the file.
     * @return Iterator<String>  an iterator of xml strings.
     */
    Iterator<String> getXmlStringWithinRange(String xpath, long start, long stop);

    /**
     * Get a collection of xpaths predefined for indexing.
     * @return Collection<String>   an collection of xpath strings.
     */
    Collection<String> getXpath();

    /**
     * Get the xml string by index element.
     * @param indexElement  index element.
     * @return String   xml string.
     */
    String getXmlByIndexElement(IndexElement indexElement);

    /**
     * Get an snippet of xml with the start and stop range.
     * @param start start of the range.
     * @param stop  stop of the range.
     * @return String   xml string.
     */
    String getXmlSnippet(long start, long stop);
}

