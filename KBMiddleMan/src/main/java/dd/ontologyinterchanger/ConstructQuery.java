package dd.ontologyinterchanger;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Created by Sergey on 29.05.2016.
 */
public class ConstructQuery extends QueryHolder {

    private Model result;

    public ConstructQuery(String queryString) {
        super(queryString);
    }

    @Override
    public QueryHolder executeQuery(Model model) {
        result = QuieringUtils.constructQuery(model, getQueryString());
        return this;
    }

    public Model getResult() {
        return result;
    }
}
