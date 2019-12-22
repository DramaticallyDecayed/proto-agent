package dd.union.perception.notification.model.parsers;


import dd.union.perception.notification.model.types.IdentNumberType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slebedev on 23.06.2017.
 */
public class ReferenceParser implements Parsing<List<IdentNumberType>> {

    @Override
    public List<IdentNumberType> parse(String value) {
        List<IdentNumberType> numberTypes = new ArrayList<>();
        for(String str : new StringCollectionParcer().parse(value)){
            numberTypes.add(new IdentNumberType(null, str));
        }
        return numberTypes;
    }

}
