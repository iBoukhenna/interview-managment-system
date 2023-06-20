package ca.levio.interview.queue;

public interface MessageQueueProducer<T> {

    abstract void send(T data);
}
