package dd.protosas.computation.levelnode;


import commonmodel.ElementState;
import dd.protosas.presentation.ElementIdent;

import java.util.TreeMap;

/**
 * Created by Sergey on 30.09.2015.
 */
public interface NodeMethod {
    ElementState process(TreeMap<String, ElementIdent> baseInput);
}
