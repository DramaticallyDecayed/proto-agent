package dd.soccer.common;

import dd.protosas.presentation.ElementIdent;

/**
 * Created by Sergey on 11.12.2015.
 */
public interface Subscriber<Post extends ElementIdent> {
    void inform(Post post);
    void inform(Publisher publisher, Topic topic, Post post);
}
