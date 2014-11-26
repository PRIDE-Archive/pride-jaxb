package uk.ac.ebi.pride.jaxb.model;

import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: rcote
 * Date: 13-Aug-2010
 * Time: 14:17:05
 * To change this template use File | Settings | File Templates.
 */
public class ModelConstants {

    public static final String MODEL_PKG = "uk.ac.ebi.pride.jaxb.model";
    public static final String PRIDE_NS = "";

    private static Map<Class, QName> modelQNames = new HashMap<Class, QName>();

    static {

        modelQNames.put(AcqSpecification.class, new QName(PRIDE_NS, "acqSpecification"));
        modelQNames.put(Admin.class, new QName(PRIDE_NS, "admin"));
        modelQNames.put(AnalyzerList.class, new QName(PRIDE_NS, "analyzerList"));
        modelQNames.put(Aquisition.class, new QName(PRIDE_NS, "acquisition"));
        modelQNames.put(Contact.class, new QName(PRIDE_NS, "contact"));
        modelQNames.put(CvLookup.class, new QName(PRIDE_NS, "cvLookup"));
        modelQNames.put(CvParam.class, new QName(PRIDE_NS, "cvParam"));
        modelQNames.put(Data.class, new QName(PRIDE_NS, "data"));
        modelQNames.put(DataProcessing.class, new QName(PRIDE_NS, "dataProcessing"));
        modelQNames.put(Description.class, new QName(PRIDE_NS, "description"));
        modelQNames.put(ExperimentCollection.class, new QName(PRIDE_NS, "ExperimentCollection"));
        modelQNames.put(Experiment.class, new QName(PRIDE_NS, "Experiment"));
        modelQNames.put(FragmentIon.class, new QName(PRIDE_NS, "FragmentIon"));
        modelQNames.put(GelFreeIdentification.class, new QName(PRIDE_NS, "GelFreeIdentification"));
        modelQNames.put(Gel.class, new QName(PRIDE_NS, "Gel"));
        modelQNames.put(GelLocation.class, new QName(PRIDE_NS, "GelLocation"));
        modelQNames.put(Instrument.class, new QName(PRIDE_NS, "instrument"));
        modelQNames.put(IntenArrayBinary.class, new QName(PRIDE_NS, "intenArrayBinary"));
        modelQNames.put(ModificationItem.class, new QName(PRIDE_NS, "ModificationItem"));
        modelQNames.put(MzArrayBinary.class, new QName(PRIDE_NS, "mzArrayBinary"));
        modelQNames.put(MzData.class, new QName(PRIDE_NS, "mzData"));
        modelQNames.put(Param.class, new QName(PRIDE_NS, "additional"));
        modelQNames.put(PeptideItem.class, new QName(PRIDE_NS, "PeptideItem"));
        modelQNames.put(Precursor.class, new QName(PRIDE_NS, "precursor"));
        modelQNames.put(PrecursorList.class, new QName(PRIDE_NS, "precursorList"));
        modelQNames.put(Protocol.class, new QName(PRIDE_NS, "Protocol"));
        modelQNames.put(ProtocolSteps.class, new QName(PRIDE_NS, "ProtocolSteps"));
        modelQNames.put(Reference.class, new QName(PRIDE_NS, "Reference"));
        modelQNames.put(SampleDescription.class, new QName(PRIDE_NS, "sampleDescription"));
        modelQNames.put(Software.class, new QName(PRIDE_NS, "software"));
        modelQNames.put(SourceFile.class, new QName(PRIDE_NS, "sourceFile"));
        modelQNames.put(SpectrumDesc.class, new QName(PRIDE_NS, "spectrumDesc"));
        modelQNames.put(SpectrumInstrument.class, new QName(PRIDE_NS, "spectrumInstrument"));
        modelQNames.put(Spectrum.class, new QName(PRIDE_NS, "spectrum"));
        modelQNames.put(SpectrumList.class, new QName(PRIDE_NS, "spectrumList"));
        modelQNames.put(SpectrumSettings.class, new QName(PRIDE_NS, "spectrumSettings"));
        modelQNames.put(SupDataArray.class, new QName(PRIDE_NS, "supDataArray"));
        modelQNames.put(SupDataBinary.class, new QName(PRIDE_NS, "supDataBinary"));
        modelQNames.put(SupDataDesc.class, new QName(PRIDE_NS, "supDataDesc"));
        modelQNames.put(SupDesc.class, new QName(PRIDE_NS, "supDesc"));
        modelQNames.put(TwoDimensionalIdentification.class, new QName(PRIDE_NS, "TwoDimensionalIdentification"));
        modelQNames.put(UserParam.class, new QName(PRIDE_NS, "userParam"));

        //now make set unmodifiable
        modelQNames = Collections.unmodifiableMap(modelQNames);

    }


    public static boolean isRegisteredClass(Class cls) {
        return modelQNames.containsKey(cls);
    }

    public static QName getQNameForClass(Class cls) {
        if (isRegisteredClass(cls)) {
            return modelQNames.get(cls);
        } else {
            throw new IllegalStateException("No QName registered for class: " + cls);
        }
    }

    public static String getElementNameForClass(Class cls) {
        if (isRegisteredClass(cls)) {
            return modelQNames.get(cls).getLocalPart();
        } else {
            throw new IllegalStateException("No QName registered for class: " + cls);
        }
    }

    public static Class getClassForElementName(String name) {
        for (Map.Entry<Class, QName> entry : modelQNames.entrySet()) {
            if (entry.getValue().getLocalPart().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }


}
