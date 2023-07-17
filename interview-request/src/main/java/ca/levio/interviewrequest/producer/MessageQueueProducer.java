package ca.levio.interviewrequest.producer;

import ca.levio.interviewrequest.messageevent.MessageEvent;

public interface MessageQueueProducer {

    abstract void send(MessageEvent data);
}
