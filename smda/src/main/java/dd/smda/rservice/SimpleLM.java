package dd.smda.rservice;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.util.Arrays;

/**
 * Created by Sergey on 26.02.2017.
 */
public class SimpleLM {

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
        connection.eval("num <- c(" + "'1', '1', '3')");
    }

    public void evalLM() throws RserveException, REXPMismatchException {
        REXP result = connection.eval("getCorrelation(num, cat)");
        System.out.println(Arrays.asList(result.asStrings()));
    }
}
