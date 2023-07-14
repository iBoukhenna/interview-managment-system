package ca.levio.messagequeue.producer;

import ca.levio.messagequeue.messageevent.MessageEvent;

public interface MessageQueueProducer {

    abstract void send(MessageEvent data);
}
