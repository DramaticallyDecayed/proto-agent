package dd.protosas.presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sergey on 25.09.2015.
 */
public class GroupElementIdent<EI extends ElementIdent> extends ElementIdent implements Iterable<EI> {

    private List<EI> idents = new ArrayList<EI>();

    public GroupElementIdent() {
    }


    public void addElementIdent(EI elementIdent) {
        idents.add(elementIdent);
    }

    public List<EI> getIdents() {
        return idents;
    }

    @Override
    public Iterator<EI> iterator() {
        return idents.iterator();
    }
}
