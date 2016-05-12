package dd.translator;


import dd.translator.owlinterplay.OntologyHandler;

/**
 * Created by Sergey on 10.05.2016.
 */
public class Main {

    public static void main(String...args){
        new OntologyHandler().init("soccer2ed.rdf");
    }
}
