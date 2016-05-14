package dd.ontologyinterchanger;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Created by Sergey on 14.05.2016.
 */
public abstract class QueryHolder {

    private final String queryString;

    public QueryHolder(String queryString) {
        this.queryString = queryString;
    }

    abstract public QueryHolder executeQuery(Model model);

    public String getQueryString(){
        return queryString;
    }
}
