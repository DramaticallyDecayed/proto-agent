package dd.protosas.presentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 25.09.2015.
 */
public class ElementIdent <E extends Element> {

    private String name;
    private E element;
    private List<ElementIdent> subsribers = new ArrayList<>();


    public ElementIdent(String name) {
        this.name = name;
    }

    public ElementIdent(E element) {
        this.element = element;
        this.name = element.getName();
    }

    public void updateElement(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public List<ElementIdent> getSubsribers(){
        return subsribers;
    }

}