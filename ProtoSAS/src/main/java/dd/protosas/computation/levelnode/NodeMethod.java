package dd.protosas.computation.levelnode;

import dd.protosas.presentation.Element;
import dd.protosas.presentation.ElementIdent;

import java.util.TreeMap;

/**
 * Created by Sergey on 30.09.2015.
 */
public interface NodeMethod {
    Element process(TreeMap<String, ElementIdent> baseInput);
}
