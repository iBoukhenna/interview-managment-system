package ca.levio.interview.producer;

import ca.levio.interview.messageevent.MessageEvent;

public interface MessageQueueProducer {

    abstract void send(MessageEvent data);
}
