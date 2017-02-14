package dd.smda.perception;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Created by Sergey on 14.02.2017.
 */
class RDFPropertyWrapper {
    private Identifier propertyID;
    private RDFNodeWrapper object;

    public RDFPropertyWrapper(Identifier propertyID){
        this.propertyID = propertyID;
    }

    public void addProperty(Model model, String[] value, Resource resource) {
        Property property = model.getProperty(propertyID.toString());
        if(property == null){
            property = model.createProperty(propertyID.toString());
        }
        resource.addProperty(property, object.addResource(model, value));
    }

    public RDFNodeWrapper addPropertyObject(RDFNodeWrapper object){
        this.object = object;
        return object;
    }

}
