package dd.translator.programgeneration.nodecreators;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import dd.translator.programgeneration.ProgramGenerationUtils;

/**
 * Created by Sergey on 27.05.2016.
 */
public class ObjectProperty {

    private String propertyName;
    private JDefinedClass propertyClass;
    private JDefinedClass propertyDomain;
    private JDefinedClass propertyRange;
    private JDefinedClass inverseProperty;
    private JMethod domainGetMethod;
    private JMethod domainSetMethod;
    private JMethod rangeGetMethod;
    private JMethod rangeSetMethod;

    public ObjectProperty(String propertyName) {
        this.propertyName = ProgramGenerationUtils.makeFirsLetterLow(propertyName);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public JDefinedClass getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(JDefinedClass propertyClass) {
        this.propertyClass = propertyClass;
    }

    public JDefinedClass getPropertyDomain() {
        return propertyDomain;
    }

    public void setPropertyDomain(JDefinedClass propertyDomain) {
        this.propertyDomain = propertyDomain;
    }

    public JDefinedClass getPropertyRange() {
        return propertyRange;
    }

    public void setPropertyRange(JDefinedClass propertyRange) {
        this.propertyRange = propertyRange;
    }

    public JDefinedClass getInverseProperty() {
        return inverseProperty;
    }

    public void setInverseProperty(JDefinedClass inverseProperty) {
        this.inverseProperty = inverseProperty;
    }

    public boolean hasInverse() {
        return inverseProperty != null ? true : false;
    }

    public JMethod getDomainGetMethod() {
        return domainGetMethod;
    }

    public void setDomainGetMethod(JMethod domainGetMethod) {
        this.domainGetMethod = domainGetMethod;
    }

    public JMethod getDomainSetMethod() {
        return domainSetMethod;
    }

    public void setDomainSetMethod(JMethod domainSetMethod) {
        this.domainSetMethod = domainSetMethod;
    }

    public JMethod getRangeGetMethod() {
        return rangeGetMethod;
    }

    public void setRangeGetMethod(JMethod rangeGetMethod) {
        this.rangeGetMethod = rangeGetMethod;
    }

    public JMethod getRangeSetMethod() {
        return rangeSetMethod;
    }

    public void setRangeSetMethod(JMethod rangeSetMethod) {
        this.rangeSetMethod = rangeSetMethod;
    }
}
