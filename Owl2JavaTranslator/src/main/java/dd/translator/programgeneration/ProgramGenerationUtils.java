package dd.translator.programgeneration;

import com.sun.codemodel.*;
import dd.translator.WrappedTranslatorException;

/**
 * Created by Sergey on 15.05.2016.
 */
public final class ProgramGenerationUtils {
    private ProgramGenerationUtils() {

    }

    public static JDefinedClass addSettersAndGetters4Interface(
            JDefinedClass newInterface,
            String paramName,
            Class paramType) {
        addGetter(newInterface, paramName, paramType, JMod.NONE);
        addSetter(newInterface, paramName, paramType, JMod.NONE);
        return newInterface;
    }

    public static String composeName(String className) {
        return ProgramStructureGenerator.PACKAGE_PREFIX
                + "."
                + SubPackageNameService.findPackageNameFor(className)
                + "."
                + className;
    }

    public static String interfaceName2ClassName(String interfaceName) {
        return interfaceName + "C";
    }

    public static String className2InterfaceName(String className) {
        return className.substring(0, className.length() - 1);
    }

    public static String makeFirsLetterUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String makeFirsLetterLow(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static JFieldVar addSettersAndGetters4Class(
            JDefinedClass c,
            String paramName,
            Class paramType) {

        JFieldVar paramField = c.field(JMod.PRIVATE, paramType, paramName);
        addGetter(c, paramName, paramType, JMod.PUBLIC, paramField);
        createFieldWithSetter(c, paramName, paramType, paramField);
        return paramField;
    }

    public static JFieldVar addSettersAndGetters4Class(
            JDefinedClass c,
            String paramName,
            JType paramType) {
        JFieldVar paramField = c.field(JMod.PRIVATE, paramType, paramName);
        addGetter(c, paramName, paramType, JMod.PUBLIC, paramField);
        createFieldWithSetter(c, paramName, paramType, paramField);
        return paramField;
    }

    public static void createFieldWithSetter(JDefinedClass c,
                                             String paramName,
                                             Class paramType,
                                             JFieldVar paramField) {
        JMethod setter = addSetter(c, paramName, paramType, JMod.PUBLIC);
        setter.body().assign(JExpr._this().ref(paramField), JExpr.ref(paramName));
    }

    public static void createFieldWithSetter(JDefinedClass c,
                                             String paramName,
                                             JType paramType,
                                             JFieldVar paramField) {
        JMethod setter = addSetter(c, paramName, paramType, JMod.PUBLIC);
        setter.body().assign(JExpr._this().ref(paramField), JExpr.ref(paramName));
    }

    private static JMethod addSetter(JDefinedClass structure, String paramName, Class paramType, int modifier) {
        JMethod setter = structure.method(modifier, void.class, "set" + makeFirsLetterUp(paramName));
        setter.param(paramType, paramName);
        return setter;
    }

    private static JMethod addSetter(JDefinedClass structure, String paramName, JType paramType, int modifier) {
        JMethod setter = structure.method(modifier, void.class, "set" + makeFirsLetterUp(paramName));
        setter.param(paramType, paramName);
        return setter;
    }

    private static void addGetter(JDefinedClass getterOwner, String getterName, Class getterType, int aPublic) {
        getterOwner.method(aPublic, getterType, "get" + makeFirsLetterUp(getterName));
    }

    private static void addGetter(JDefinedClass getterOwner, String getterName, Class getterType, int aPublic, JFieldVar paramField) {
        getterOwner.method(aPublic, getterType, "get" + makeFirsLetterUp(getterName)).body()
                ._return(paramField);
    }

    public static JMethod addGetter(JDefinedClass getterOwner, String getterName, JType getterType, int aPublic, JFieldVar paramField) {
        JMethod getter = getterOwner.method(aPublic, getterType, "get" + makeFirsLetterUp(getterName));
        getter.body()
                ._return(paramField);
        return getter;
    }

    public static JDefinedClass createClass(JCodeModel jcm, String className) {
        try {
            return jcm._class(className, ClassType.CLASS);
        } catch (JClassAlreadyExistsException e) {
            throw new WrappedTranslatorException(WorldEntityClassGenerator.class.getSimpleName(), e);
        }
    }

    public static JDefinedClass createInterface(JCodeModel jcm, String className) {
        try {
            return jcm._class(className, ClassType.INTERFACE);
        } catch (JClassAlreadyExistsException e) {
            throw new WrappedTranslatorException(WorldEntityClassGenerator.class.getSimpleName(), e);
        }
    }
}
