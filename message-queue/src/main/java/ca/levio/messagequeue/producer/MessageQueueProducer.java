package ca.levio.messagequeue.producer;

import ca.levio.commonbean.messageevent.MessageEvent;

public interface MessageQueueProducer {

    abstract void send(MessageEvent data, String topic);
}
