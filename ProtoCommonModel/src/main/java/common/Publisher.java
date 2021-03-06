package common;

import commonmodel.ElementState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 11.12.2015.
 */
public class Publisher<Post extends ElementState> {

    private Post post;
    Map<Dependency, List<Subscriber>> register = new HashMap<>();

    public void subscribe(Dependency topic, Subscriber subscriber) {
        List<Subscriber> subscribers = getSubscribers(topic);
        subscribe(subscribers, subscriber);
    }

    public void unsubscribe(Dependency topic, Subscriber subscriber) {
        List<Subscriber> subscribers = getSubscribers(topic);
        unsubscribe(subscribers, subscriber);
    }

    private void unsubscribe(List<Subscriber> subscribers, Subscriber subscriber) {
        if(subscribers != null){
            subscribers.remove(subscriber);
        }
    }

    public void detailedPublish(Dependency topic) {
        List<Subscriber> subscribers = register.get(topic);
        if (subscribers != null && !subscribers.isEmpty()) {
            for (Subscriber subscriber : subscribers) {
                subscriber.inform(this, topic);
            }
        }
    }

    private List<Subscriber> getSubscribers(Dependency topic) {
        List<Subscriber> subscribers = register.get(topic);
        if (haveSomeSubscribers(subscribers)) {
            subscribers = createNewSubscribersList();
            register.put(topic, subscribers);
        }
        return subscribers;
    }

    private List<Subscriber> createNewSubscribersList() {
        List<Subscriber> subscribers;
        subscribers = new ArrayList<>();
        return subscribers;
    }

    private boolean haveSomeSubscribers(List<Subscriber> subscribers) {
        return subscribers == null;
    }

    private void subscribe(List<Subscriber> subscribers, Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
