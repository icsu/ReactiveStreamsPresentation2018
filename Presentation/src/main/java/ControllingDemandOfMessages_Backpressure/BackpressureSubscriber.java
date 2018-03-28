package ControllingDemandOfMessages_Backpressure;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicInteger;

public class BackpressureSubscriber<T> implements Flow.Subscriber<T>
{

    private AtomicInteger howMuchMessagesConsume;
    private Flow.Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();

    public BackpressureSubscriber(Integer howMuchMessagesConsume) {
        this.howMuchMessagesConsume
                = new AtomicInteger(howMuchMessagesConsume);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        howMuchMessagesConsume.decrementAndGet();
        System.out.println("Got : " + item);
        consumedElements.add(item);
        if (howMuchMessagesConsume.get() > 0) {
            subscription.request(1);
        }
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }

}