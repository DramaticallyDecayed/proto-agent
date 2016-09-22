package dd.sas.pipeline;

/**
 * Created by Sergey on 18.09.2016.
 */
@FunctionalInterface
public interface TripleFunction<F, M, L, C> {
    C apply(F first, M middle, L last);
}
