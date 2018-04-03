package rx.operators;

import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class FlatMapBlockingSample
{
    public static void main(String[] args)
    {
        // TODO
        Observable.range(1, 10)
                .flatMap(v -> Observable.just(v).delay(11 - v, TimeUnit.SECONDS))
                .subscribe(System.out::println);
    }
}
