package dd.smda.perception;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Created by Sergey on 14.02.2017.
 */
public class RDFLiteralWrapper {

    private Identifier propertyID;
    private Identifier type;
    private int index;

    public RDFLiteralWrapper(Identifier type, Identifier propertyID, int index) {
        this.type = type;
        this.propertyID = propertyID;
        this.index = index;
    }

    public void addLiteral(Model model, String[] value, Resource resource) {
        Property property = model.getProperty(propertyID.toString());

        if (property == null) {
            property = model.createProperty(propertyID.toString());
        }
        if (!value[index].isEmpty()) {
            resource.addLiteral(property, model.createTypedLiteral(value[index],type.toString()));
        }
    }
}
