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
                for (String str : ds.getDerivative()) {
                    String className = "dd.soccer.perception.perceptingobjects." + str;
                    try {
                        Class c = Class.forName(className);
                        JClass fieldCommonClass = cm.ref(List.class);
                        JClass narrowedFieldCommonClass = fieldCommonClass.narrow(c);

                        String name = str + "List";
                        String finalName = name.substring(0, 1).toLowerCase() + name.substring(1);

                        JClass fieldClass = cm.ref(ArrayList.class);
                        JClass narrowedFieldClass = fieldClass.narrow(c);

                        JFieldVar var = dc.field(4, narrowedFieldCommonClass, finalName, JExpr._new(narrowedFieldClass));

                        dc.method(JMod.PUBLIC, narrowedFieldCommonClass, "get" + name)
                                .body()
                                ._return(var);
                        JMethod setter = dc.method(JMod.PUBLIC,cm.VOID, "set" + name);
                        JVar param = setter.param(narrowedFieldClass, finalName);
                        setter.body().assign(JExpr._this().ref(var), JExpr.ref(finalName));

                        dc.method(JMod.PUBLIC, cm.VOID, "process");

                        System.out.println(c);
                    } catch (ClassNotFoundException e) {
                        System.out.println("do not find class " + className);
                    }
                }
            } else {

            }
        }


        File file = new File(".\\Owl2JavaTranslator\\target\\generated-classes");
        file.mkdirs();
        cm.build(file);

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

}
