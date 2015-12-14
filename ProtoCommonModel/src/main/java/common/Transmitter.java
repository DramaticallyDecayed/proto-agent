package common;

import commonmodel.ElementState;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sergey on 12.12.2015.
 */
public class Transmitter extends Publisher implements Subscriber {

    private Queue<ElementState> baseNotifies = new LinkedList<>();
    private Queue<Dependency> topicNotifies = new LinkedList<>();
    private Queue<Publisher> publishersNotifies = new LinkedList<>();

    private ElementState currentBase;
    private Dependency currentTopic;
    private Publisher currentPublisher;

    public boolean hasSomthing(){
        return !baseNotifies.isEmpty();
    }

    public ElementState poll(){
        currentBase = baseNotifies.poll();
        currentTopic = topicNotifies.poll();
        currentPublisher = publishersNotifies.poll();
        return currentBase;
    }

    public Queue<ElementState> getBaseNotifies(){
        return baseNotifies;
    }

    @Override
    public void inform(ElementState elementState) {
        baseNotifies.add(elementState);
        topicNotifies.add(null);
        publishersNotifies.add(null);
    }

    @Override
    public void inform(Publisher publisher, Dependency topic, ElementState elementState) {
        baseNotifies.add(elementState);
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
