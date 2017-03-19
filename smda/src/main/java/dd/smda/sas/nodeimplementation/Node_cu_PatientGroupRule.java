package dd.smda.sas.nodeimplementation;

import dd.sas.Utils;
import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.smda.sas.objectproperty.BeGroupRuleBaseFor;
import dd.smda.sas.objectproperty.BePatientBaseFor;
import dd.smda.sas.worldentity.GroupRule;
import dd.smda.sas.worldentity.Patient;
import dd.smda.sas.worldentity.PatientGroupRule;
import dd.smda.sas.worldentity.PatientGroupRuleC;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Sergey on 26.02.2017.
 */
public class Node_cu_PatientGroupRule extends dd.smda.sas.computation.node.Node_cu_PatientGroupRule {
    private static final String PREFIX = "parametergraph";

    public Node_cu_PatientGroupRule(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getGroupRuleList() != null && getPatientList() != null) {
            if (!getGroupRuleList().isEmpty() && !getPatientList().isEmpty()) {

                for (Patient patient : getPatientList()) {
                    try {
                        GroupRule groupRule = getGroupRuleList().get(0);
                        String ruleString = groupRule.getHasRuleString();
                        String[] ruleProperties = ruleString.split(" ");

                        Method[] methods = new Method[ruleProperties.length];
                        for (int i = 0; i < methods.length; i++) {
                            methods[i] = Patient.class.getDeclaredMethod(ruleProperties[i]);
                        }

                        String[] values = new String[methods.length];

                        for (int i = 0; i < methods.length; i++) {
                            values[i] = (String) methods[i].invoke(patient);
                        }

                        StringBuilder query = new StringBuilder();
                        query.append("\t?p a " + PREFIX + ":Patient .\n");

                        for (int i = 0; i < ruleProperties.length; i++) {
                            String rdfProperty = Utils.makeFirsLetterDown(ruleProperties[i].replace("get", ""));
                            query.append("\t?p " + PREFIX + ":" + rdfProperty + " \"" + values[i] + "\"^^xsd:string .\n");
                        }

                        PatientGroupRule patientGroupRule = new PatientGroupRuleC();
                        patientGroupRule.setHasQueryString(query.toString());
                        getPatientGroupRuleList().add(patientGroupRule);

                        BePatientBaseFor bePatientBaseFor = new BePatientBaseFor();
                        bePatientBaseFor.setDomain(patient);
                        bePatientBaseFor.setRange(patientGroupRule);
                        getBePatientBaseForList().add(bePatientBaseFor);


                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }
}
