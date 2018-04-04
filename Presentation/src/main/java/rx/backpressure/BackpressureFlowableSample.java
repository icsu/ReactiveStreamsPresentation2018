package rx.backpressure;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Executors;

public class BackpressureFlowableSample
{
    public static void main(String[] args)
    {
        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(10));

        Flowable.range(1, 10000)
                .onBackpressureDrop()
                .observeOn(scheduler)
                .subscribe(i -> { System.out.println(i); }, throwable ->
                    System.out.println("Thread: " + Thread.currentThread().getName() + ", Buffer overflow: "  +  throwable),
                        () -> System.out.println("Done")
                );

    }

}