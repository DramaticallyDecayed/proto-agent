package dd.translator;

/**
 * Created by Sergey on 20.01.2016.
 */
public class CDLevelPair {
    private final String calculationDependency;
    private final int levelNumber;

    public CDLevelPair(String calculationDependency, int levelNumber) {
        this.calculationDependency = calculationDependency;
        this.levelNumber = levelNumber;
    }

    public String getCalculationDependency() {
        return calculationDependency;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
