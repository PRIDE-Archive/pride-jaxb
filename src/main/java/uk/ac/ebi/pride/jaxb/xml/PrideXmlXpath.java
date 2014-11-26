package uk.ac.ebi.pride.jaxb.xml;

import uk.ac.ebi.pride.jaxb.model.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Note: All classes added must be either null or extending PrideXmlObject
 *
 * User: rwang
 * Date: 26-Apr-2010
 * Time: 16:24:03
 */
public enum PrideXmlXpath {

    EXP_COLLECTION("/ExperimentCollection", ExperimentCollection.class),
    EXP ("/ExperimentCollection/Experiment", Experiment.class),
    EXP_ACC ("/ExperimentCollection/Experiment/ExperimentAccession", null),
    EXP_TITLE ("/ExperimentCollection/Experiment/Title", null),
    EXP_REF ("/ExperimentCollection/Experiment/Reference", Reference.class),
    EXP_SHORTLABEL ("/ExperimentCollection/Experiment/ShortLabel", null),
    EXP_PROTOCOL ("/ExperimentCollection/Experiment/Protocol", Protocol.class),

    MZDATA ("/ExperimentCollection/Experiment/mzData", MzData.class),
    MZDATA_CVLOOKUP ("/ExperimentCollection/Experiment/mzData/cvLookup", CvLookup.class),
    MZDATA_DESC("/ExperimentCollection/Experiment/mzData/description", Description.class),
    MZDATA_DESC_AMDIN ("/ExperimentCollection/Experiment/mzData/description/admin", Admin.class),
    MZDATA_DESC_INSTRUMENT ("/ExperimentCollection/Experiment/mzData/description/instrument", Instrument.class),
    MZDATA_DESC_DATAPROCESSING ("/ExperimentCollection/Experiment/mzData/description/dataProcessing", DataProcessing.class),

    MZDATA_SPECTRUMLIST ("/ExperimentCollection/Experiment/mzData/spectrumList", SpectrumList.class),
    MZDATA_SPECTRUM ("/ExperimentCollection/Experiment/mzData/spectrumList/spectrum", Spectrum.class),

    TWOD ("/ExperimentCollection/Experiment/TwoDimensionalIdentification", TwoDimensionalIdentification.class),
    TWOD_ACC ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/Accession", null),
    TWOD_ACC_VERSION ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/AccessionVersion", null),
    TWOD_SPLICEISOFORM ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/SpliceIsoform", null),
    TWOD_DB ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/Database", null),
    TWOD_DB_VERSION ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/DatabaseVersion", null),
    TWOD_PEPTIDE ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/PeptideItem", PeptideItem.class),
    TWOD_PEPTIDE_SPEC_REF ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/PeptideItem/SpectrumReference", null),
    TWOD_SPEC_REF ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/SpectrumReference", null),
    TWOD_ADDITIONAL ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/additional", Param.class),
    TWOD_SCORE ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/Score", null),
    TWOD_THRESHOLD ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/Threshold", null),
    TWOD_SEARCHENGINE ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/SearchEngine", null),
    TWOD_SEQ_COV ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/SequenceCoverage", null),
    TWOD_GEL ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/Gel", Gel.class),
    TWOD_GELLOCATION ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/GelLocation", GelLocation.class),
    TWOD_MW ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/MolecularWeight", null),
    TWOD_PI ("/ExperimentCollection/Experiment/TwoDimensionalIdentification/pI", null),

    GELFREE ("/ExperimentCollection/Experiment/GelFreeIdentification", GelFreeIdentification.class),
    GELFREE_ACC ("/ExperimentCollection/Experiment/GelFreeIdentification/Accession", null),
    GELFREE_ACC_VERSION ("/ExperimentCollection/Experiment/GelFreeIdentification/AccessionVersion", null),
    GELFREE_SPLICEISOFORM ("/ExperimentCollection/Experiment/GelFreeIdentification/SpliceIsoform", null),
    GELFREE_DB ("/ExperimentCollection/Experiment/GelFreeIdentification/Database", null),
    GELFREE_DB_VERSION ("/ExperimentCollection/Experiment/GelFreeIdentification/DatabaseVersion", null),
    GELFREE_PEPTIDE ("/ExperimentCollection/Experiment/GelFreeIdentification/PeptideItem", PeptideItem.class),
    GELFREE_PEPTIDE_SPEC_REF ("/ExperimentCollection/Experiment/GelFreeIdentification/PeptideItem/SpectrumReference", null),
    GELFREE_SPEC_REF ("/ExperimentCollection/Experiment/GelFreeIdentification/SpectrumReference", null),
    GELFREE_ADDITIONAL ("/ExperimentCollection/Experiment/GelFreeIdentification/additional", Param.class),
    GELFREE_SCORE ("/ExperimentCollection/Experiment/GelFreeIdentification/Score", null),
    GELFREE_THRESHOLD ("/ExperimentCollection/Experiment/GelFreeIdentification/Threshold", null),
    GELFREE_SEARCHENGINE ("/ExperimentCollection/Experiment/GelFreeIdentification/SearchEngine", null),
    GELFREE_SEQ_COV ("/ExperimentCollection/Experiment/GelFreeIdentification/SequenceCoverage", null),

    EXP_ADDITIONAL ("/ExperimentCollection/Experiment/additional", Param.class);

    private final String xpath;

    private final Class type;

    private static final Set<String> xpaths;

    static {
        xpaths = new HashSet<String>();
        for (PrideXmlXpath prideXpath : values()) {
            xpaths.add(prideXpath.getXpath());
        }
    }

    private PrideXmlXpath(String xpath, Class clazz) {
        this.xpath = xpath;
        this.type = clazz;
    }

    public String getXpath() {
        return xpath;
    }

    public Class getClassType() {
        return type;
    }

    public static Set<String> getXpaths() {
        return xpaths;
    }
}
