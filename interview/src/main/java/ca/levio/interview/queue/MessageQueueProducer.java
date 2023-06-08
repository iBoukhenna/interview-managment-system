package ca.levio.interview.queue;

public interface MessageQueueProducer {

    abstract void send(Object data);
}
