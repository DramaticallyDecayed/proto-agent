package dd.translator;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileUtils;
import com.sun.codemodel.*;
import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QuieringUtils;
import dd.protosas.computation.LightweightLevel;
import dd.protosas.computation.LightweightNode;
import dd.soccer.perception.perceptingobjects.BodyState;
import org.topbraid.spin.system.SPINModuleRegistry;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by Sergey on 17.01.2016.
 */
public class Translator {

    public static void main(String... args) throws JClassAlreadyExistsException, IOException {
        SPINModuleRegistry.get().init();
        BareModelInterchanger bmi = new BareModelInterchanger(Translator.class.getClassLoader().getResource("soccer.rdf").toString(), FileUtils.langTurtle);
        bmi.insertIndividual(BodyState.class, "some_body_state");
        bmi.runInference();
        Map<String, Integer> map = constructCD2LevelMapping(bmi.getOntModel());


        JCodeModel cm = new JCodeModel();
        String packageName = "dd.protosas.generated";

        for (Integer levelNum : map.values()) {
            try {
                JDefinedClass levelClass = cm._class(packageName + ".Level_" + levelNum);
                levelClass._extends(LightweightLevel.class);
                JMethod defaultConstructor = levelClass.constructor(0);
                defaultConstructor.body().invoke("super").arg(JExpr.lit(levelNum));
            } catch (JClassAlreadyExistsException e) {
                System.out.println("Skip existent class...");
            }
        }

        for (String cd : map.keySet()) {
            Integer levelNum = map.get(cd);
            JDefinedClass nodeClass = cm._class(packageName + ".Node_" + cd);
            nodeClass._extends(LightweightNode.class);
            JDefinedClass levelClass = cm._getClass(packageName + ".Level_" + levelNum);
            JMethod constructor = levelClass.getConstructor(new JType[0]);
            constructor.body().invoke("addLightweightNode").arg(JExpr._new(nodeClass));
        }


        for (String cd : map.keySet()) {

            JDefinedClass dc = cm._getClass(packageName + ".Node_" + cd);

            DependecySpecification ds = constructCDs(bmi.getOntModel(), cd);


            if (ds.getBase().isEmpty()) {
                generateDerivativeStuff(cm, dc, ds);
                continue;
            } else {

                JMethod pullBases = dc.method(JMod.PUBLIC, cm.VOID, "pullBases");
                for (String str : ds.getBase()) {

                    String donorName = getDonor4Base(bmi.getOntModel(), cd, str);
                    JDefinedClass donorClass = cm._getClass(packageName + ".Node_" + donorName);

                    JFieldVar donor;
                    if(!dc.fields().containsKey(donorName)) {
                        donor = dc.field(JMod.PRIVATE, donorClass, donorName);
                    }else{
                        donor = dc.fields().get(donorName);
                    }


                    str = upperCaseFirstLetter(str);
                    Class c = loadClassByName(str);

                    JClass fieldCommonClass = cm.ref(List.class);
                    JClass narrowedFieldCommonClass = fieldCommonClass.narrow(c);

                    String name = str + "List";
                    String finalName = lowerCaseFirstLetter(name);

                    JClass fieldClass = cm.ref(ArrayList.class);

                    JFieldVar var = dc.field(JMod.PRIVATE, narrowedFieldCommonClass, finalName);


                        pullBases.body().assign(JExpr._this().ref(var),
                                donor.invoke("get" + name));





                }
            }
            generateDerivativeStuff(cm, dc, ds);
        }


        File file = new File(".\\Owl2JavaTranslator\\target\\generated-classes");
        file.mkdirs();
        cm.build(file);

    }

    private static void generateDerivativeStuff(JCodeModel cm, JDefinedClass dc, DependecySpecification ds) {
        for (String str : ds.getDerivative()) {


            str = upperCaseFirstLetter(str);

            Class c = loadClassByName(str);

            JClass fieldCommonClass = cm.ref(List.class);
            JClass narrowedFieldCommonClass = fieldCommonClass.narrow(c);

            String name = str + "List";
            String finalName = lowerCaseFirstLetter(name);

            JClass fieldClass = cm.ref(ArrayList.class);
            JClass narrowedFieldClass = fieldClass.narrow(c);

            JFieldVar var = dc.field(JMod.PRIVATE, narrowedFieldCommonClass, finalName, JExpr._new(narrowedFieldClass));

            dc.method(JMod.PUBLIC, narrowedFieldCommonClass, "get" + name)
                    .body()
                    ._return(var);
            JMethod setter = dc.method(JMod.PUBLIC, cm.VOID, "set" + name);
            JVar param = setter.param(narrowedFieldClass, finalName);
            setter.body().assign(JExpr._this().ref(var), JExpr.ref(finalName));

            dc.method(JMod.PUBLIC, cm.VOID, "process");
            System.out.println(c);

        }
    }

    private static String upperCaseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static String lowerCaseFirstLetter(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private static Class loadClassByName(String str) {
        Class c = null;
        try {
            String className = "dd.soccer.sas.presentation.entities." + str;
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            String className = "dd.soccer.perception.perceptingobjects." + str;
            try {
                c = Class.forName(className);
            } catch (ClassNotFoundException e1) {
                System.out.println("do not find class " + str);
            }
        }
        return c;
    }

    private static Map<String, Integer> constructCD2LevelMapping(Model ontModel) {
        ResultSet resultSet = QuieringUtils.getQueryResults(
                ontModel,
                "SELECT ?cd ?levelNumber\n" +
                        "WHERE {\n" +
                        "    ?cd a core:CalculationDependency .\n" +
                        "\t?cd core:level ?level .\n" +
                        "\t?level core:number ?levelNumber.\n" +
                        "} ORDER BY ?levelNumber"
        );
        Map<String, Integer> cd2levelMapping = new HashMap<>();
        for (; resultSet.hasNext(); ) {
            QuerySolution qs = resultSet.next();
            cd2levelMapping.put(qs.getResource("cd").getLocalName(),
                    (Integer) qs.getLiteral("levelNumber").getValue());
        }
        return cd2levelMapping;
    }

    private static DependecySpecification constructCDs(Model ontModel, String cdname) {
        ResultSet resultSet = QuieringUtils.getQueryResults(
                ontModel,
                "SELECT ?cd ?base ?derivative\n" +
                        "WHERE {\n" +
                        "    BIND(:" + cdname + " AS ?cd).\n" +
                        "\tOPTIONAL{\n" +
                        "\t\t?cd core:hasBase ?base\n" +
                        "\t}\n" +
                        "\t?cd core:hasDerivative ?derivative\n" +
                        "}"
        );

        List<String> base = new ArrayList<>();
        Set<String> derivative = new HashSet<>();
        DependecySpecification ds = new DependecySpecification();
        for (; resultSet.hasNext(); ) {
            QuerySolution qs = resultSet.next();
            if (qs.getResource("base") != null) {
                ds.addBase(qs.getResource("base").getLocalName());
            }
            ds.addDerivative(qs.getResource("derivative").getLocalName());
        }
        return ds;
    }

    private static String getDonor4Base(Model ontModel, String cdname, String baseName) {
        ResultSet resultSet = QuieringUtils.getQueryResults(
                ontModel,
                "SELECT ?donor\n" +
                        "WHERE {\n" +
                        "BIND(:" + cdname + " AS ?cd).\n" +
                        "BIND(:" + baseName + " AS ?base).\n" +
                        "?donor core:hasDerivative ?base.\n" +
                        "?cd core:level ?cdLevel .\n" +
                        "?cdLevel core:number ?cdLevelNumber .\n" +
                        "?donor core:level ?donorLevel .\n" +
                        "?donorLevel core:number ?donorLevelNumber .\n" +
                        "FILTER (?cdLevelNumber > ?donorLevelNumber) .\n" +
                        "} ORDER BY DESC(?donorLevelNumber)  LIMIT 1"
        );
        if(resultSet.hasNext()) {
            QuerySolution qs = resultSet.next();
            if (qs.getResource("donor") != null) {
                return qs.getResource("donor").getLocalName();
            }
        }
        return null;
    }

}
