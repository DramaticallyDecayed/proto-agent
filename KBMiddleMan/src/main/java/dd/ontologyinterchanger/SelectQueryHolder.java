package dd.ontologyinterchanger;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.*;

/**
 * TODO: think about annotation usage for representing query variables and generation of the corresponding getters
 * <p>
 * Created by Sergey on 14.05.2016.
 */
public class SelectQueryHolder extends QueryHolder implements Iterable<Map<String, Object>> {

    private List<Object[]> result;
    private Map<String, Integer> name2IndexMapping;

    public SelectQueryHolder(String queryString) {
        super(queryString);
    }

    @Override
    public SelectQueryHolder executeQuery(Model model) {
        fillWithResult(QuieringUtils.getQueryResults(model, getQueryString()));
        return this;
    }

    private void fillWithResult(ResultSet resultSet) {
        List<String> list = resultSet.getResultVars();
        mapNames2Indeces(list);
        fillWithResult(resultSet, list);
    }

    private void fillWithResult(ResultSet resultSet, List<String> list) {
        result = new ArrayList<>();
        for (; resultSet.hasNext(); ) {
            QuerySolution qs = resultSet.next();
            result.add(new Object[list.size()]);
            for (String name : list) {
                addResultItem(qs, name);
            }
        }
    }

    private void addResultItem(QuerySolution qs, String name) {
        result.get(result.size() - 1)[name2IndexMapping.get(name)] = decodeNodeType(qs, name);
    }

    private Object decodeNodeType(QuerySolution qs, String name) {
        RDFNode nd = qs.get(name);
        if (nd.isLiteral()) {
            return decodeLiteralType(qs.getLiteral(name));
        } else if (nd.isResource()) {
            return qs.getResource(name).getLocalName();
        }
        return null;
    }

    private Object decodeLiteralType(Literal literal) {
        String dataTypeURI = literal.getDatatypeURI();
        if (dataTypeURI.contains("string")) {
            return literal.getString();
        } else if (dataTypeURI.contains("integer")) {
            return literal.getInt();
        } else if (dataTypeURI.contains("float")) {
            return literal.getDouble();
        } else if (dataTypeURI.contains("boolean")) {
            return literal.getBoolean();
        }
        return null;
    }

    private void mapNames2Indeces(List<String> list) {
        name2IndexMapping = new HashMap<>(list.size());
        int index = 0;
        for (String name : list) {
            name2IndexMapping.put(name, index++);
        }
    }


    @Override
    public Iterator iterator() {
        return new SelectQueryHolderIterator();
    }

    private class SelectQueryHolderIterator implements Iterator<Map<String, Object>> {

        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < result.size();
        }

        @Override
        public Map<String, Object> next() {
            Map<String, Object> resultSlice = new HashMap<>();
            Object[] values = result.get(currentIndex++);
            for (String name : name2IndexMapping.keySet()) {
                resultSlice.put(name, values[name2IndexMapping.get(name)]);
            }
            return resultSlice;
        }
    }
}
