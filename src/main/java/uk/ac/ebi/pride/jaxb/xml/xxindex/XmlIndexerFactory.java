package uk.ac.ebi.pride.jaxb.xml.xxindex;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.tools.xxindex.*;
import psidev.psi.tools.xxindex.index.IndexElement;
import psidev.psi.tools.xxindex.index.XpathIndex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Factory class to construct an implementation of xml indexer interface.
 *
 * @author Florian Reisinger
 * @author rwang
 * @since 0.1
 */
public class XmlIndexerFactory {

    private static final Logger logger = LoggerFactory.getLogger(XmlIndexerFactory.class);

    private static final XmlIndexerFactory instance = new XmlIndexerFactory();

    private XmlIndexerFactory() {
    }

    public static XmlIndexerFactory getInstance() {
        return instance;
    }

    /**
     * Build an implementation of XmlIndexer.
     *
     * @param xmlFile input xml file.
     * @param xpaths  xpaths used to generate index.
     * @return XmlIndexer  return an XmlIndexer.
     */
    public XmlIndexer buildIndex(File xmlFile, Set<String> xpaths) {
        return new XmlIndexerImpl(xmlFile, xpaths);
    }

    /**
     * Implementation of XmlIndexer.
     * ToDo: this is just a wrapper implementation, could this be added into xxindex?
     */
    private class XmlIndexerImpl implements XmlIndexer {

        /**
         * input xml file to read
         */
        private File xmlFile = null;
        /**
         * xpathAccess is used to generate index on the input xml file
         */
        private XpathAccess xpathAccess = null;
        /**
         * xmlExtrator is used to extract xml strings
         */
        private XmlElementExtractor xmlExtractor = null;
        /**
         * xml index
         */
        private XpathIndex index = null;

        /**
         * XmlIndexerImpl
         *
         * @param aXmlFile input xml file.
         * @param xpaths   xpaths used to generate index.
         */
        private XmlIndexerImpl(File aXmlFile, Set<String> xpaths) {
            if (aXmlFile == null) {
                throw new IllegalStateException("XML File to index must not be null.");
            }
            if (!aXmlFile.exists()) {
                throw new IllegalStateException("XML File to index does not exist: " + xmlFile.getAbsolutePath());
            }

            // store file reference
            xmlFile = aXmlFile;

            try {
                // generate XXINDEX
                xpathAccess = new StandardXpathAccess(xmlFile, xpaths);

                // check if we have a compressed file
                if (xmlFile.getName().endsWith(".gz")) {
                    // create xml element extractor
                    xmlExtractor = new GzXmlElementExtractor();
                } else {
                    // create xml element extractor
                    xmlExtractor = new FastXmlElementExtractor(xmlFile);
                }


                String encoding = xmlExtractor.detectFileEncoding(xmlFile.toURI().toURL());
                if (encoding != null) {
                    xmlExtractor.setEncoding(encoding);
                }

                // create index
                index = xpathAccess.getIndex();

            } catch (IOException e) {
                logger.error("PrideIndexFactory$PrideIndexImpl.PrideIndexImpl", e);
                throw new IllegalStateException("Could not generate index file for: " + xmlFile, e);
            }

        }

        public String getXmlByIndexElement(IndexElement indexElement) {
            if (indexElement != null) {
                return readXml(indexElement.getStart(), indexElement.getStop());
            } else {
                throw new IllegalStateException("Attempting to read NULL ByteRange");
            }
        }

        public List<String> getXmlStringList(String xpath) {
            List<String> xmlList = new ArrayList<>();
            Iterator<String> xmlIter = getXmlStringIterator(xpath);
            while (xmlIter.hasNext()) {
                xmlList.add(xmlIter.next());
            }
            return xmlList;
        }

        public String getXmlSnippet(long start, long stop) {
            return readXml(start, stop);
        }

        public Iterator<String> getXmlStringIterator(String xpathExpression) {
            return xpathAccess.getXmlSnippetIterator(xpathExpression);
        }

        public int getCount(String xpathExpression) {
            return index.getElementCount(xpathExpression);
        }

        public Set<String> getXpath() {
            return index.getKeys();
        }

        public List<IndexElement> getIndexElements(String xpathExpression) {
            return index.getElements(xpathExpression);
        }

        public Iterator<String> getXmlStringWithinRange(String xpath, long start, long stop) {
            return xpathAccess.getXmlSnippetIterator(xpath, start, stop);
        }

        /**
         * IOException are handled here.
         *
         * @param start start of the xml string to read.
         * @param stop  stop of the xml string to read.
         * @return String   xml string.
         */
        private String readXml(long start, long stop) {
            try {
                return xmlExtractor.readString(start, stop, xmlFile);
            } catch (IOException e) {
                logger.error("PrideIndexFactory$PrideIndexImpl.readXML", e);
                throw new IllegalStateException("Could not extract XML from file: " + xmlFile, e);
            }
        }
    }
}
