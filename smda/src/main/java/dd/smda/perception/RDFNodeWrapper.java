package dd.smda.perception;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Sergey on 14.02.2017.
 */
class RDFNodeWrapper {

    private Identifier type;
    private String prefix;
    private Integer index;
    private Function<String, String> expression;
    private List<RDFPropertyWrapper> propertyWrappers = new ArrayList<>();
    private List<RDFLiteralWrapper> literalWrappers = new ArrayList<>();

    public RDFNodeWrapper(String prefix, int index){
        this.index = index;
        this.prefix = prefix;
        expression = s -> s;
    }

    public RDFNodeWrapper(Function<String, String> expression, String prefix, int index){
        this(prefix, index);
        this.expression = expression;
    }

    public RDFNodeWrapper(String prefix, int index, Identifier type){
        this(prefix, index);
        this.type = type;
    }

    public RDFNodeWrapper(Function<String, String> expression,
                          String prefix,
                          int index,
                          Identifier type){
        this(expression, prefix, index);
        this.type = type;
    }

    public Resource addResource(Model model, String[] value) {
        Resource resource = model.getResource(prefix + expression.apply(value[index]));

        if (resource == null) {
            resource = model.createResource(prefix + expression.apply(value[index]));
        }

        for (RDFPropertyWrapper propertyWrapper : propertyWrappers) {
            propertyWrapper.addProperty(model, value, resource);
        }

        for (RDFLiteralWrapper literalWrapper : literalWrappers) {
            literalWrapper.addLiteral(model, value, resource);
        }

        if(type != null) {
            resource.addProperty(RDF.type,
                    model.createResource(type.toString())
            );
        }

        return resource;
    }

    public RDFPropertyWrapper addProperty(RDFPropertyWrapper property) {
        propertyWrappers.add(property);
        return property;
    }

    public RDFNodeWrapper addLiteral(RDFLiteralWrapper literal) {
        literalWrappers.add(literal);
        return this;
    }

}
