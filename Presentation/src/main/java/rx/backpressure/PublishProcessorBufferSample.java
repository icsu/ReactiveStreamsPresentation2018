package rx.backpressure;

import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

public class PublishProcessorBufferSample
{
    public static void main(String[] args)
    {
        PublishProcessor<Integer> source = PublishProcessor.create();

        source
                .buffer(1024)
                .observeOn(Schedulers.computation())
                .subscribe(list -> {
                    list.parallelStream().map(e -> e * e);
                    System.out.println(list.size());
                }, Throwable::printStackTrace);

        for (int i = 0; i < 1_000_000; i++) {
            source.onNext(i);
        }

    }
}
