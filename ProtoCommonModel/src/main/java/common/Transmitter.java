package common;

import commonmodel.ElementState;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sergey on 12.12.2015.
 */
public class Transmitter extends Publisher implements Subscriber {

    private Queue<Dependency> topicNotifies = new LinkedList<>();
    private Queue<Publisher> publishersNotifies = new LinkedList<>();

    private ElementState currentBase;
    private Dependency currentTopic;
    private Publisher currentPublisher;

    public boolean hasSomething(){
        return !publishersNotifies.isEmpty();
    }

    public ElementState poll(){
        currentTopic = topicNotifies.poll();
        currentPublisher = publishersNotifies.poll();
        currentBase = currentPublisher.getPost();
        return currentBase;
    }

    @Override
    public void inform(Publisher publisher, Dependency topic) {
        topicNotifies.add(topic);
        publishersNotifies.add(publisher);
    }

    public ElementState getCurrentBase(){
        return currentBase;
    }

    public Dependency getCurrentTopic(){
        return currentTopic;
    }

    public Publisher getCurrentPublisher(){
        return currentPublisher;
    }
}
