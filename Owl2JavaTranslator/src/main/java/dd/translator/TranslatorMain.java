package dd.translator;


import dd.translator.owlinterplay.TranslatorOntologyHandler;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorMain {

    public static void main(String...args){
        new TranslatorOntologyHandler().checkOntologyInference();
    }
}
