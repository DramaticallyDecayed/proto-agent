package dd.translator.programgeneration;

import dd.ontologyinterchanger.SelectQueryHolder;
import dd.translator.owlinterplay.SelectQueryFabric;
import dd.translator.owlinterplay.TranslatorOntologyHandler;

/**
 * Created by Sergey on 14.05.2016.
 */
public final class SubPackageNameService {

    private SubPackageNameService() {
    }

    public static String findPackageNameFor(String className) {
        SelectQueryHolder sqh = TranslatorOntologyHandler.INSTANCE.
                executeQuery(SelectQueryFabric.findCoreClass(className));
        return ((String) sqh.firstSlice().get("sc")).toLowerCase();
    }
}
