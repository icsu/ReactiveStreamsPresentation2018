package rx.operators;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class DelaySample
{
    public static void main(String args[])
    {
        // TODO
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(5)
                .delay(1, TimeUnit.SECONDS)
                .timeInterval()
                .subscribe(System.out::println);
    }
}
