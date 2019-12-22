package dd.translator;

import com.sun.codemodel.*;
import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.QuieringUtils;
import dd.translator.programgeneration.ProgramStructureGenerator;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileUtils;
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
        BareModelInterchanger bmi = new BareModelInterchanger(
                Translator.class.getClassLoader().getResource("soccer.rdf").toString(),
                FileUtils.langTurtle);
        //bmi.insertIndividual(BodyState.class, "some_body_state");
        bmi.runInference();
        Map<String, Integer> map = constructCD2LevelMapping(bmi.getOntModel());


        JCodeModel cm = new JCodeModel();
        String packageName = ProgramStructureGenerator.PACKAGE_PREFIX + ".computation.generated";


        JDefinedClass[] classes = new JDefinedClass[map.values().size() + 1];
        for (Integer levelNum : map.values()) {
            try {
                JDefinedClass levelClass = cm._class(packageName + ".Level_" + levelNum);
                //levelClass._extends(LightweightLevel.class);
                JMethod defaultConstructor = levelClass.constructor(JMod.PUBLIC);
                defaultConstructor.body().invoke("super").arg(JExpr.lit(levelNum));
                classes[levelNum] = levelClass;
            } catch (JClassAlreadyExistsException e) {
                System.out.println("Skip existent class...");
            }
        }

        Map<String, List<JDefinedClass>> node = new HashMap<>();
        for (String cd : map.keySet()) {
            Integer levelNum = map.get(cd);
            JDefinedClass nodeClass = cm._class(packageName + ".Node_" + cd);
           // nodeClass._extends(LightweightNode.class);
            JDefinedClass levelClass = cm._getClass(packageName + ".Level_" + levelNum);

            if (node.get(levelClass.name()) == null) {
                node.put(levelClass.name(), new ArrayList<>());
                node.get(levelClass.name()).add(nodeClass);
            } else {
                node.get(levelClass.name()).add(nodeClass);
            }

            JMethod constructor = levelClass.getConstructor(new JType[0]);
            //constructor.body().invoke("addLightweightNode").arg(JExpr._new(nodeClass));
        }


        for (String cd : map.keySet()) {

            JDefinedClass dc = cm._getClass(packageName + ".Node_" + cd);

            DependecySpecification ds = constructCDs(bmi.getOntModel(), cd);
            JMethod constructor = dc.constructor(JMod.PUBLIC);

            JMethod processMethod = dc.method(JMod.PUBLIC, cm.VOID, "process");

            if (ds.getBase().isEmpty()) {
                generateDerivativeStuff(cm, dc, ds);
                continue;
            } else {

                JMethod pullBases = dc.method(JMod.PUBLIC, cm.VOID, "pullBases");

                ArrayList<Integer> levels = new ArrayList<>();
                for (String str : ds.getBase()) {

                    CDLevelPair cdLevelPair = getDonor4Base(bmi.getOntModel(), cd, str);
                    String donorName = cdLevelPair.getCalculationDependency();
                    int levelNum = cdLevelPair.getLevelNumber();

                    JDefinedClass donorClass = cm._getClass(packageName + ".Node_" + donorName);
                    JDefinedClass donorLevel = cm._getClass(packageName + ".Level_" + levelNum);

                    JFieldVar donor;
                    if (!dc.fields().containsKey(donorName)) {
                        donor = dc.field(JMod.PRIVATE, donorClass, donorName);
                    } else {
                        donor = dc.fields().get(donorName);
                    }

                    if (levels.indexOf(levelNum) == -1) {
                        levels.add(levelNum);
                        JVar param = constructor.param(donorLevel, "level_" + levelNum);
                        constructor
                                .body()
                                .assign(
                                        JExpr._this().ref(donor),
                                        JExpr.cast(donorClass, param.invoke("getLightweightNode")
                                                .arg(donorClass.dotclass()))
                                );
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
                processMethod.body().invoke(pullBases);
            }
            generateDerivativeStuff(cm, dc, ds);

        }


        JDefinedClass initializer = cm._class(packageName + ".LevelNodeInitializer");
        JMethod init = initializer.method(JMod.PUBLIC | JMod.STATIC, cm.VOID, "init");
       // JVar levelHolder = init.param(LevelHolder.class, "levelHolder");

        for (JDefinedClass jdf : classes) {
            if (jdf != null) {
                String levelName = jdf.name().toLowerCase();
                JVar level = init.body().decl(jdf, levelName);
                init.body().assign(JExpr.ref(levelName), JExpr._new(jdf));
           //     init.body().invoke(levelHolder, "addLevel").arg(level);
                List<JDefinedClass> ns = node.get(jdf.name());
                for (JDefinedClass n : ns) {
                    JVar[] params = n.constructors().next().listParams();
                    JInvocation invocation = JExpr._new(n);
                    init.body().invoke(level, "addLightweightNode").arg(invocation);
                    if (params.length > 0) {
                        for (JVar p : params) {
                            invocation.arg(p);
                        }
                    }
                }
            }
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
            String className = ProgramStructureGenerator.PACKAGE_PREFIX + ".presentation.entities." + str;
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            String className = ProgramStructureGenerator.PACKAGE_PREFIX + ".dd.airdefence.perception.perceptingobjects." + str;
            try {
                c = Class.forName(className);
            } catch (ClassNotFoundException e1) {
                className = ProgramStructureGenerator.PACKAGE_PREFIX + ".presentation.soccerrelations." + str;
                try {
                    c = Class.forName(className);
                } catch (ClassNotFoundException e2) {
                    System.out.println("do not find class " + str);
                }
            }
        }
        return c;
    }

    private static Map<String, Integer> constructCD2LevelMapping(Model ontModel) {
        ResultSet resultSet = QuieringUtils.getQueryResults(
                ontModel,
                "SELECT ?cd ?levelNumber " +
                        "WHERE {" +
                        "?cd a core2ed:ComputationUnit ." +
                        "?cd core2ed:level ?level ." +
                        "?level core2ed:number ?levelNumber ." +
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

    private static CDLevelPair getDonor4Base(Model ontModel, String cdname, String baseName) {

        ResultSet resultSet = QuieringUtils.getQueryResults(
                ontModel,
                "SELECT ?donor ?donorLevelNumber\n" +
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
        if (resultSet.hasNext()) {
            QuerySolution qs = resultSet.next();
            if (qs.getResource("donor") != null) {
                return new CDLevelPair(
                        qs.getResource("donor").getLocalName(),
                        qs.getLiteral("donorLevelNumber").getInt()
                );
            }
        }
        return null;
    }

}
