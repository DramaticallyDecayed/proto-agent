package common;

import commonmodel.ElementState;

/**
 * Created by Sergey on 11.12.2015.
 */
public interface Subscriber<Post extends ElementState> {
    void inform(Publisher publisher, Dependency topic);
}
