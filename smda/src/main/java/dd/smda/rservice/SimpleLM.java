package dd.smda.rservice;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 26.02.2017.
 */
public class SimpleLM {
    private static final String NUM_COL_NAME = "numericalColumn";
    private static final String CAT_COL_NAME = "categoricalColumn";
    private static final String FILE_NAME = "SimpleLM.R";
    private static final String PATH = RServer.getPathToFunction(FILE_NAME);
    private RConnection connection;

    public SimpleLM(RConnection connection) throws RserveException {
        this.connection = connection;
        connection.eval("source(\"" + PATH + "\")");
    }

    public void loadCategoricalParameterValues() throws RserveException {
        connection.eval("cat <- c(" + "'ж', 'ж', 'c')");
    }

    public void loadNumericalParameterValues() throws RserveException {
        connection.eval("num <- c(" + "1, 1, 3)");
    }

    public List<String> evalLM() throws RserveException, REXPMismatchException {
        REXP result = connection.eval("getCorrelation(num, cat)");
        return Arrays.asList(result.asStrings())
                .stream()
                .map(x->x.replace(CAT_COL_NAME, ""))
                .collect(Collectors.toList());
    }

    public void loadParameterValues(List<String>[] result) throws RserveException {
        connection.eval("cat <- c('"
                + result[0].stream().reduce((x1, x2) -> x1 + "','" + x2).get()
                + "')");
        connection.eval("num <- c("
                +result[1].stream().reduce((x1, x2) -> x1 + ","+ x2).get()
                + ")");
    }

    public static void main(String...args) throws RserveException, REXPMismatchException {
        RServer rServer = new RServer();
        rServer.start();
        SimpleLM simpleLM = new SimpleLM(rServer.getConnection());
        rServer.getConnection().eval("cat <- c('a','a','c')");
        rServer.getConnection().eval("num <- c(1, NA, 10)");
        simpleLM.evalLM();

    }
}
