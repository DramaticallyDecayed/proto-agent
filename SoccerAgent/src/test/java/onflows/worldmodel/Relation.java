package onflows.worldmodel;

/**
 * Created by Sergey on 13.09.2016.
 */
public class Relation<D, R> {
    private D domain;
    private R range;


    public D getDomain() {
        return domain;
    }

    public void setDomain(D domain) {
        this.domain = domain;
    }

    public R getRange() {
        return range;
    }

    public void setRange(R range) {
        this.range = range;
    }
}
