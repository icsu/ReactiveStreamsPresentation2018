package rx;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SimpleObservable
{
    public static void main(String args[])
    {
        Observable.just("Hello World").subscribeOn(Schedulers.computation()).subscribe(System.out::println);
    }
}
