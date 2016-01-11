package common;

/**
 * Created by Sergey on 11.12.2015.
 */
public class Dependency implements Comparable {

    private Class classAsDependency;

    public Dependency(Class classAsDependency) {
        this.classAsDependency = classAsDependency;
    }

    @Override
    public boolean equals(Object dependency) {

        return this.classAsDependency.equals(((Dependency)dependency).classAsDependency);
    }

    @Override
    public int hashCode() {
        return classAsDependency.hashCode();
    }

    @Override
    public int compareTo(Object other) {
        return classAsDependency.getName().compareTo(((Dependency) other).classAsDependency.getName());
    }
}
