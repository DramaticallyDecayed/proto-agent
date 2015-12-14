package dd.protosas.presentation;


import commonmodel.ElementState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 25.09.2015.
 */
public class ElementIdent <E extends ElementState> {

    private E element;

    public ElementIdent(){
    }

    public ElementIdent(E element) {
        this.element = element;
    }

    public void updateElement(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

}