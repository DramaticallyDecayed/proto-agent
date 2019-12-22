package dd.ontologyinterchanger;

import org.apache.jena.rdf.model.Model;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.progress.ProgressMonitor;

/**
 * Created by Sergey on 22.11.2015.
 */
public class BareInferenceRunner {

    public static Model run(Model ontModel, Model newTriples) {
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
        return newTriples;
    }

    static class MyMonitor implements ProgressMonitor {

        private String name;
        private int currentWork;
        private int totalWork;
        private String label;

        public MyMonitor(String name) {
            this.name = name;
        }

        public void beginTask(String label, int totalWork) {
            this.println("Beginning task " + label + " (" + totalWork + ")");
            this.totalWork = totalWork;
            this.currentWork = 0;
        }

        public void done() {
            this.println("Done");
        }

        public boolean isCanceled() {
            return false;
        }

        protected void println(String text) {
            System.out.println(this.name + ": " + text);
        }

        public void setCanceled(boolean value) {
        }

        public void setTaskName(String value) {
            this.println("Task name: " + value);
        }

        public void subTask(String label) {
            this.println("Subtask: " + label);
        }

        public void worked(int amount) {
            this.currentWork += amount;
            this.println("Worked " + this.currentWork + " / " + this.totalWork);
        }
    }
}
