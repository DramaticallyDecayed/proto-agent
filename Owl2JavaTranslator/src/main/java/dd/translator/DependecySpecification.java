package dd.translator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Sergey on 18.01.2016.
 */
public class DependecySpecification {

    private List<String> base = new ArrayList<>();
    private Set<String> derivative = new HashSet<>();

    public void addBase(String baseName){
        base.add(baseName);
    }

    public void addDerivative(String derivativeName){
        derivative.add(derivativeName);
    }

    public List<String> getBase(){
        return base;
    }

    public Set<String> getDerivative(){
        return derivative;
    }
}
