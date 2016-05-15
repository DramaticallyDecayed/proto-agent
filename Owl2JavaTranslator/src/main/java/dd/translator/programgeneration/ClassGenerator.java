package dd.translator.programgeneration;

import java.util.List;

/**
 * Created by Sergey on 13.05.2016.
 */
public class ClassGenerator extends ProgramElelementGenerator{

    private ProgramStructureGenerator generator;

    public ClassGenerator(ProgramStructureGenerator psg){
        super(psg);
    }

    @Override
    public void generate(List<String> classNames){
        System.out.println(classNames);
    }
}
