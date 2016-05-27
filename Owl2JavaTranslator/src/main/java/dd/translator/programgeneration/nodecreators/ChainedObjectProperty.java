package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.JDefinedClass;

/**
 * Created by Sergey on 27.05.2016.
 */
public class ChainedObjectProperty extends ObjectProperty {

    private JDefinedClass firstPart;
    private JDefinedClass secondPart;

    public ChainedObjectProperty(String propertyName) {
        super(propertyName);
    }

    public JDefinedClass getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(JDefinedClass firstPart) {
        this.firstPart = firstPart;
    }

    public JDefinedClass getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(JDefinedClass secondPart) {
        this.secondPart = secondPart;
    }
}
