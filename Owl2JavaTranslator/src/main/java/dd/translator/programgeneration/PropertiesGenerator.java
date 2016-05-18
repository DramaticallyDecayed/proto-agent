package dd.translator.programgeneration;

import com.sun.codemodel.JDefinedClass;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 18.05.2016.
 */
public class PropertiesGenerator extends ProgramElementGenerator {

    private final String DOMAIN = "domain";
    private final String RANGE = "range";

    public PropertiesGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public void generate(List<String> elementNames) {
        elementNames.stream()
                .map(name -> ProgramGenerationUtils.makeFirsLetterUp(name))
                .map(name -> composeName(name))
                .map(name -> ProgramGenerationUtils.createClass(getPsg().getCm(), name))
                .map(jdc -> addDomainAndRange(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass addDomainAndRange(JDefinedClass jdc) {
        String propertyName = ProgramGenerationUtils.makeFirsLetterLow(jdc.name());
        generateFeature(jdc, propertyName, DOMAIN);
        generateFeature(jdc, propertyName, RANGE);
        return jdc;
    }


    private void generateFeature(JDefinedClass jdc, String propertyName, String feature) {
        SelectQueryHolder sqh = choseQuery(feature, propertyName);
        JDefinedClass featureClass = getPsg()
                .getCm()
                ._getClass(ProgramGenerationUtils.composeName((String) sqh.firstSlice().get("f")));
        ProgramGenerationUtils.addSettersAndGetters4Class(jdc, feature, featureClass);
    }

    private SelectQueryHolder choseQuery(String feature, String propertyName) {
        switch (feature) {
            case DOMAIN:
                return executeQuery(SelectQueryFabric.collectDomains(propertyName));
            case RANGE:
                return executeQuery(SelectQueryFabric.collectRanges(propertyName));
        }
        return null;
    }


    private String composeName(String name) {
        return ProgramStructureGenerator.PACKAGE_PREFIX + "." + "objectproperty" + "." + name;
    }
}
