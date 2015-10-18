package dd.protosas.presentation;

/**
 * Created by Sergey on 30.09.2015.
 */
public interface Identity {
    public abstract boolean equals(Object other);
    public abstract int hashCode();
}
