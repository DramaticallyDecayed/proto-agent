package dd.protosas.computability;

import dd.protosas.computation.Level;
import dd.protosas.presentation.ElementIdent;

import java.util.Collection;

/*
Instead of putting code for polling dependent levels into ident objects we make a separate class that
in some future can be realized as a common semantic register realized as in memory ontology.
 */

/**
 * Created by Sergey on 30.10.2015.
 */
public interface ElementIdentDependencyRegister {

    Collection<Level> getDependentLevels(ElementIdent elementIdent);
}
