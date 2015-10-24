package dd.protosas.computation.levelnode;

import dd.protosas.presentation.ElementIdent;

/**
 * Created by Sergey on 23.10.2015.
 */
public interface INode {

    void process();
    void notifyOnBaseInput(ElementIdent ident);

}
