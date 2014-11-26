package uk.ac.ebi.pride.jaxb.utils;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: rwang
 * Date: 20-Jul-2010
 * Time: 14:22:36
 */
public enum CvTermReference {

    FLOAT_16_BIT ("MS", "MS:1000520", "16-bit float", "MS:1000518"),
    FLOAT_32_BIT ("MS", "MS:1000521", "32-bit float", "MS:1000518"),
    FLOAT_64_BIT ("MS", "MS:1000523", "64-bit float", "MS:1000518"),
    INT_32_BIT ("MS", "MS:1000519", "32-bit integer", "MS:1000518"),
    INT_64_BIT ("MS", "MS:1000522", "64-bit integer", "MS:1000518");

    private final String cvLabel;
    private final String accession;
    private final String name;
    private final String parentAccession;

    private CvTermReference(String cvLabel, String accession, String name, String parentAccession) {
        this.cvLabel = cvLabel;
        this.accession = accession;
        this.name = name;
        this.parentAccession = parentAccession;
    }

    public String getCvLabel() {
        return cvLabel;
    }

    public String getAccession() {
        return accession;
    }

    public String getName() {
        return name;
    }

    public Collection<String> getParentAccessions() {
        return Arrays.asList(parentAccession.split(";"));
    }

    /**
     * Get Cv term by accession.
     * @param accession controlled vocabulary accession.
     * @return CvTermReference  Cv term.
     */
    public static CvTermReference getCvRefByAccession(String accession) {
        CvTermReference cvTerm = null;

        CvTermReference[] cvTerms = CvTermReference.values();
        for(CvTermReference cv : cvTerms) {
            if (cv.getAccession().equals(accession)) {
                cvTerm = cv;
            }
        }

        return cvTerm;
    }

    /**
     * Check whether the accession exists in the enum.
     * @param accession controlled vocabulary accession
     * @return boolean  true if exists
     */
    public static boolean hasAccession(String accession) {
        boolean result = false;

        CvTermReference[] cvTerms = CvTermReference.values();
        for(CvTermReference cv : cvTerms) {
            if (cv.getAccession().equals(accession)) {
               result = true;
            }
        }

        return result;
    }

    /**
     * Check whether two accessions are parent-child relationship.
     * @param parentAcc parent accession.
     * @param childAcc  child accession.
     * @return boolean  true if it is parent-child relationship.
     */
    public static boolean isChild(String parentAcc, String childAcc) {
        boolean isChild = false;
        CvTermReference childCvTerm = getCvRefByAccession(childAcc);
        if (childCvTerm != null && childCvTerm.getParentAccessions().contains(parentAcc)) {
            isChild = true;
        }
        return isChild;
    }
}
