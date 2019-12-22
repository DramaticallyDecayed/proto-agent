package dd.translator.programgeneration;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.pipeline.worldmodel.Relation;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectPropertyFromTemplateGenerator extends ProgramElementGenerator {

    private final String DOMAIN = "Domain";
    private final String RANGE = "Range";

    public ObjectPropertyFromTemplateGenerator(ProgramStructureGenerator psg) {
        super(psg);
    }

    @Override
    public List<String> receiveData() {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.executeQuery(
                SelectQueryFabric.collectObjectProperties());
        return sqh.getDisk("r");
    }

    @Override
    public void generate() {
        List<String> elementNames = receiveData();
        elementNames.stream()
                .map(name -> ProgramGenerationUtils.makeFirsLetterUp(name))
                .map(name -> composeName(name))
                .map(name -> ProgramGenerationUtils.createClass(getPsg().getCm(), name))
                .map(jdc -> addDomainAndRange(jdc))
                .map(jdc -> link2SAS(jdc))
                .collect(Collectors.toList());
    }

    private JDefinedClass addDomainAndRange(JDefinedClass jdc) {
        String propertyName = ProgramGenerationUtils.makeFirsLetterLow(jdc.name());


        JClass jClassExtends = getPsg().getCm().ref(Relation.class)
                .narrow(generateFeature(jdc, propertyName, DOMAIN))
                .narrow(generateFeature(jdc, propertyName, RANGE));
        jdc._extends(jClassExtends);
        return jdc;
    }

    private JDefinedClass generateFeature(JDefinedClass jdc, String propertyName, String feature) {
        SelectQueryHolder sqh = choseQuery(feature, propertyName);
        JDefinedClass featureClass = getPsg()
                .getCm()
                ._getClass(ProgramGenerationUtils.composeName((String) sqh.firstSlice().get("f")));
        JMethod get = jdc.method(JMod.PUBLIC, featureClass, "get" + feature);
        get.body()._return(JExpr._super().invoke("get" + feature));
        get.annotate(Override.class);
        JMethod set = jdc.method(JMod.PUBLIC, getPsg().getCm().VOID, "set" + feature);
        JVar param = set.param(featureClass, ProgramGenerationUtils.makeFirsLetterLow(featureClass.name()));
        set.body().invoke(JExpr._super(), set).arg(param);
        set.annotate(Override.class);
        return featureClass;
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

    public static String composeName(String name) {
        return ProgramStructureGenerator.PACKAGE_PREFIX + "." + "objectproperty" + "." + name;
    }
}
