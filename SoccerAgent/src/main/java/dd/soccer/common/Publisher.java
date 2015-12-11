package dd.soccer.common;

import dd.protosas.presentation.ElementIdent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 11.12.2015.
 */
public class Publisher<Post extends ElementIdent> {

    private Post post;
    Map<Topic, List<Subscriber>> register = new HashMap<>();

    public void subscribe(Topic topic, Subscriber subscriber) {
        List<Subscriber> subscribers = getSubscribers(topic, subscriber);
        subscribe(topic, subscribers, subscriber);
    }

    public void publish(Topic topic) {
        List<Subscriber> subscribers = register.get(topic);
        if (subscribers != null && !subscribers.isEmpty()) {
            for (Subscriber subscriber : subscribers) {
                subscriber.inform(getPost());
            }
        }
    }

    public void detailedPublish(Topic topic) {
        List<Subscriber> subscribers = register.get(topic);
        if (subscribers != null && !subscribers.isEmpty()) {
            for (Subscriber subscriber : subscribers) {
                subscriber.inform(this, topic, getPost());
            }
        }
    }

    private List<Subscriber> getSubscribers(Topic topic, Subscriber subscriber) {
        List<Subscriber> subscribers = register.get(topic);
        if (haveSomeSubscribers(subscribers)) {
            subscribers = createNewSubscribersList(subscriber);
        }
        return subscribers;
    }

    private List<Subscriber> createNewSubscribersList(Subscriber subscriber) {
        List<Subscriber> subscribers;
        subscribers = new ArrayList<>();
        subscribers.add(subscriber);
        return subscribers;
    }

    private boolean haveSomeSubscribers(List<Subscriber> subscribers) {
        return subscribers == null;
    }

    private void subscribe(Topic topic, List<Subscriber> subscribers, Subscriber subscriber) {
        register.put(topic, subscribers);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
