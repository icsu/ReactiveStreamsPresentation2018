package rx.backpressure;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.Arrays;
import java.util.List;

public class BackpressureResourceSubscriberSample
{
    public static void main(String[] args)
    {
        //TODO

        ResourceSubscriber<String> eagerSubscriber = new ResourceSubscriber<>()
        {
            public void onStart()
            {
                request(1);
            }

            @Override public void onNext(String s)
            {
                System.out.println("Current item emitted: " + s);
                request(1);
            }
            @Override public void onError(Throwable throwable)
            {
                System.out.println(throwable.getStackTrace());
            }

            @Override public void onComplete()
            {
                System.out.println("Done");
            }
        };

        List<String> items = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine");

        Flowable<String> flowable = Flowable.create(emitter -> {
            for (String item : items)
            {
                System.out.println("Emitting: " + item);
                emitter.onNext(item);
                //Thread.sleep(100);
            }
            emitter.onComplete();
                }, BackpressureStrategy.DROP);

        flowable
                .observeOn(Schedulers.computation())
                .subscribe(eagerSubscriber);

    }
}
