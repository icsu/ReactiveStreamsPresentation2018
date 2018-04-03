package rx.backpressure;

import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class PublishProcessorTimeSample
{
    public static void main(String[] args)
    {
        PublishProcessor<Integer> source = PublishProcessor.create();

        source
                .sample(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation(), true, 1024)
                .subscribe(v -> System.out.println(v)
                , Throwable::printStackTrace);

        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }
    }
}
