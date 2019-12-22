package dd.translator.programgeneration;

import com.sun.codemodel.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class CodeModelPrinter{

    private CodeModelPrinter(){}

    public static void print(JCodeModel cm){

        Iterator<JPackage> packageIter = cm.packages();

        Map<JPackage, List<JDefinedClass>> toRemove = new HashMap<>();
        while (packageIter.hasNext()) {
            JPackage jPackage = packageIter.next();
            System.out.println("package: " + jPackage.name());
            Iterator<JDefinedClass> classesIter = jPackage.classes();

            while (classesIter.hasNext()) {
                JDefinedClass jDefinedClass = classesIter.next();

//                if(!jDefinedClass.name().equals("Node_cu_reference_on")){
//                    if(toRemove.get(jPackage) == null){
//                        toRemove.put(jPackage, new ArrayList<>());
//                    }
//                    toRemove.get(jPackage).add(jDefinedClass);
//                }else {
//                    System.out.println();
//                }

                System.out.println(
                        "\tclass: "
                                + jDefinedClass.name()
                );
                Iterator<JClass> implIter = jDefinedClass._implements();
                while (implIter.hasNext()) {
                    JClass imp = implIter.next();
                    System.out.println("\t\timplements: " + imp.name());
                }
                System.out.println("\t\textends: " + jDefinedClass._extends());
                System.out.println("\t\tannotations: " + jDefinedClass.annotations());
                System.out.println("\t\tfields[");

                System.out.println("\t\t]");
                System.out.println("\t\tconstructors[");
                Iterator<JMethod> methIter = jDefinedClass.constructors();
                while(methIter.hasNext()){
                    JMethod jMethod= methIter.next();
                    JType type = jMethod.type();
                    System.out.println("\t\t\t"
                            + jMethod.name()
                            + "("
                            + printParms(jMethod.params())
                            + ")"
                            + ":" + type);
                }
                System.out.println("\t\t]");

                for (String fName : jDefinedClass.fields().keySet()) {
                    JType type = jDefinedClass.fields().get(fName).type();
                    System.out.println("\t\t\t" + fName + ":" + type);
                }
                System.out.println("\t\t]");
                System.out.println("\t\tmethods[");
                for (JMethod method : jDefinedClass.methods()) {
                    JType type = method.type();
                    System.out.println("\t\t\t"
                            + method.name()
                            + "("
                            + printParms(method.params())
                            + ")"
                            + ":" + type);
                }
                System.out.println("\t\t]");
            }
        }
        for(JPackage jPackage : toRemove.keySet()){
            for(JDefinedClass jClass : toRemove.get(jPackage)){
                jPackage.remove(jClass);
            }
        }
    }

    private static String printParms(List<JVar> params) {
        StringBuilder sb = null;
        for (JVar var : params) {
            if (sb == null) {
                sb = new StringBuilder();
                sb.append(var.name()).append(":").append(var.type().name());
            } else {
                sb.append(", ").append(var.name()).append(":").append(var.type().name());
            }
        }
        if (sb == null) {
            sb = new StringBuilder();
            sb.append("[]");
        }
        return sb.toString();
    }
}
