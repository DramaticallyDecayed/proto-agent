package dd.translator.programgeneration;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;

/**
 * Created by Sergey on 15.05.2016.
 */
public final class ProgramGenerationUtils {
    private ProgramGenerationUtils(){

    }

    public static JDefinedClass addGetter(JDefinedClass getterOwner, String getterName, Class getterType) {
        getterOwner.method(JMod.NONE, getterType, "get" + makeFirsLetterUp(getterName));
        return getterOwner;
    }

    public static String composeName(String className) {
        return ProgramStructureGenerator.PACKAGE_PREFIX
                + "."
                + SubPackageNameService.findPackageNameFor(className)
                + "."
                + className;
    }

    private static String makeFirsLetterUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
